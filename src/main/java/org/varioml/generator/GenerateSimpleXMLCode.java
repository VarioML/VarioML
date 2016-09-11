package org.varioml.generator;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.simpleframework.xml.stream.CamelCaseStyle;
import org.simpleframework.xml.stream.Style;
import org.varioml.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * Quick and dirty code generator for XML data based on XML instances.
 * 
 * App generates code for SimpleXML ((http://simple.sourceforge.net/) from XML instances 
 *
 */

@SuppressWarnings("rawtypes")
public abstract class GenerateSimpleXMLCode {

	protected  boolean overwrite = false;
	
	final private Document doc;
	final private XPath xpath;
	final private String file;
	final private Style style = new UnderscoreStyle();
	final static String TEXT_NODE = "_textnode_" ;
	final static Class ELEMENTLIST = org.simpleframework.xml.ElementList.class;
	final static Class ELEMENT = org.simpleframework.xml.Element.class;
	final static Class ATTRIBUTE = org.simpleframework.xml.Attribute.class;
	

	protected GenerateSimpleXMLCode(Document doc, XPath xpath, String file) {
		this.doc = doc;
		this.xpath = xpath;
		this.file = file;		
	}

	public static class Counter{ 
		private String name;
		private int count;
		public Counter(String name, int count) {
			super();
			this.name = name;
			this.count = count;
		}
		public String getName() {
			return name;
		}
		public int getCount() {
			return count;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
	}
	abstract public void printRootHeader ( PrintStream out) ;
	
	abstract public void printOtherRootHeader ( PrintStream out) ;

	public static class CField {
		private Annotation annotation;
		private Field field;

		public CField(Annotation annotation, Field field) {
			super();
			this.annotation = annotation;
			this.field = field;
		}

		public Annotation getAnnotation() {
			return annotation;
		}

		public void setAnnotation(Annotation annotation) {
			this.annotation = annotation;
		}

		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

	}

	
	protected String fullNamePath( Node node) {
		Node p = node.getParentNode() ;
		if ( p.getNodeType() == Node.DOCUMENT_NODE) {
			return "//"+node.getNodeName();
		} else {
			return fullNamePath(p)+"/"+node.getNodeName();
		}
	}
	
	public int countElements( Node nod)  {
		if ( nod == null ) { 
			return 0;
		}
		final int _len = 
			(nod.hasChildNodes() ? nod.getChildNodes().getLength() : 0)  +
			(nod.hasAttributes() ? nod.getAttributes().getLength() : 0);
		
		return _len;
	}

	protected void fillTheMap( Node node, HashMap<String, Counter> map) {
		if ( node.getNodeType() == Node.TEXT_NODE) return;
		final String name = node.getNodeName() ;
		final String full  = fullNamePath(node);
		final int len = countElements(node);
		Counter i = map.get(name);
		if ( i == null ) {
			i = new Counter( full, len);
			map.put(name, i);
		} else {
			if ( i.count < len) { 
				i.setCount(len);
				i.setName(full);
			} else if ( i.count == len) {
				if ( i.getName().length() > full.length()) {
					i.setCount(len);
					i.setName(full);					
				}
			}
		}

		NodeList nodes = node.getChildNodes();
		for ( int ix = 0 ; ix< nodes.getLength() ; ix++ ) {
			fillTheMap( nodes.item(ix), map) ;
		}

	}
	
	public String className ( String name) {
		//return name.substring(0,1).toUpperCase() + name.substring(1);
		CamelCaseStyle c = new CamelCaseStyle();
		return c.getElement(name);
	}

	public HashMap<String, Counter> getElementCountMap() {

		HashMap<String,Counter>  map = new HashMap<String, Counter>();
		NodeList nodes = doc.getChildNodes();
		for ( int ix = 0 ; ix< nodes.getLength() ; ix++ ) {
			//try to find elements which have most of the sub elements
			fillTheMap( nodes.item(ix), map) ;
		}
		return map;
	}
	/**
	 * generate some code for the generator itself
	 * @param mappings
	 */
	public void printExampleElements (HashMap mappings) {
	
		HashMap<String,Counter>  map = getElementCountMap();
		
		
		//
		ArrayList<String> paths = new ArrayList<String>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Counter c = map.get(key);
			paths.add(c.getName());
		}
		
		Collections.sort(paths);
		for (Iterator iterator = paths.iterator(); iterator.hasNext();) {
			String path = (String) iterator.next();
			//Counter c = map.get(key);
			String elems[] = path.split("\\/");
			String key = elems[elems.length-1];
			Counter c = map.get(key);
			if ( c == null) Util.fatal(this.getClass()," no element "+key);
			if ( mappings.get(key) != null && c.getCount() == 1 ) {
				continue; // no primitives
			}
			String className = className(key);
			System.out.println("xu.generateCode(\"org.varioml.simplexml."+className+"\",\""+path+"\",x(typeMap,new HashMap<String,String>(){ ");
			if ( c.getCount() == 1 ) {
				System.out.println("	{put(\"TEXT_NODE\",\"Text\");};");								
			} else {
				System.out.println("	{put(\"_\",\"_\");};");		//just a place holder for overloading type definitions		
			}
			System.out.println("})); // size="+c.getCount()+" "+DateUtils.now());
			//System.err.println(path);			
		}
		
	}

	public HashMap<String, CField> getElementFields(Class cls) {

		LinkedHashMap<String, CField> set = new LinkedHashMap<String, CField>();

		for (Field field : cls.getDeclaredFields()) {

			String name = field.getName();
			Annotation[] a = field.getDeclaredAnnotations();
			for (int i = 0; i < a.length; i++) {
				Annotation annotation = a[i];
				CField cf = new CField(annotation, field);
				if (annotation.annotationType() == ELEMENTLIST) {
					set.put(style.getElement(name), cf);
				} else if (annotation.annotationType() == ELEMENT) {
					set.put(style.getElement(name), cf);
				} else if (annotation.annotationType() == ATTRIBUTE) {
					// set.put( style.getAttribute(name),cf) ;
				}
			}
		}

		return set;
	}

	@SuppressWarnings("rawtypes")
	public HashMap<String, CField> getAttributeFields(Class cls) {

		LinkedHashMap<String, CField> set = new LinkedHashMap<String, CField>();

		final Class ELEMENTLIST = org.simpleframework.xml.ElementList.class;
		final Class ELEMENT = org.simpleframework.xml.Element.class;
		final Class ATTRIBUTE = org.simpleframework.xml.Attribute.class;

		for (Field field : cls.getDeclaredFields()) {

			String name = field.getName();
			Annotation[] a = field.getDeclaredAnnotations();
			for (int i = 0; i < a.length; i++) {
				Annotation annotation = a[i];
				if (annotation.annotationType() == ELEMENTLIST) {
					// set.put( style.getElement(name),ELEMENTLIST) ;
				} else if (annotation.annotationType() == ELEMENT) {
					// set.put( style.getElement(name),ELEMENT) ;
				} else if (annotation.annotationType() == ATTRIBUTE) {
					CField cf = new CField(annotation, field);
					set.put(style.getAttribute(name), cf);
				}
			}
		}

		return set;
	}

	public Node getNode(String expression) {

		NodeList result = null;
		try {
			XPathExpression expr = xpath.compile(expression);
			result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			Util.fatal(GenerateSimpleXMLCode.class, e);
		}

		if (result == null || result.getLength() == 0)
			Util.fatal(GenerateSimpleXMLCode.class, "xml file " + file
					+ " don't have element " + expression);
		return result.item(0);
	}
	

	public String[] getElementNames ( Node nod) {
		
		NodeList elem = nod.getChildNodes();
		String lastElement="";
		ArrayList<String> arr = new ArrayList<String>();
		
		for (int i = 0; i < elem.getLength(); i++) {

			if (elem.item(i).getNodeType() == Node.TEXT_NODE)
				continue;
			if (elem.item(i).getNodeName().equals(lastElement))
				continue;
			lastElement = elem.item(i).getNodeName();
			arr.add(lastElement);			
		}
		return arr.toArray(new String[ arr.size()]);
	}
	
	public boolean generateCode(String file, String xpath ) throws IOException {
		return generateCode(file, xpath,new HashMap<String, String>());
	}

	public boolean generateCode(String file, String xpath, Map<String,String> typeMap ) throws IOException {
		return generateCode(file, xpath, typeMap, false);
	}
	
	
	//Todo: refactor
	abstract public boolean generateCode(String file, String xpath, Map<String,String> typeMap, boolean isInterface) throws IOException ;



	public static HashMap<String, String> x( HashMap<String, String> map, HashMap<String, String> aMap) {
		HashMap<String, String> nw = (HashMap<String, String>) map.clone();
		nw.putAll(aMap);
		return nw;
	}
	public static void main(String[] args) throws Exception{

	

	}

}