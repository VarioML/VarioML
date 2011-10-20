package org.varioml.util;

import java.io.File;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.simpleframework.xml.stream.CamelCaseStyle;
import org.simpleframework.xml.stream.Style;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
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
public class GenerateSimpleXMLCode {

	boolean overwrite = false;
	
	final private Document doc;
	final private XPath xpath;
	final private String file;
	final private Style style = new UnderscoreStyle();
	final static String TEXT_NODE = "_textnode_" ;
	final static Class ELEMENTLIST = org.simpleframework.xml.ElementList.class;
	final static Class ELEMENT = org.simpleframework.xml.Element.class;
	final static Class ATTRIBUTE = org.simpleframework.xml.Attribute.class;
	

	private GenerateSimpleXMLCode(Document doc, XPath xpath, String file) {
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
	public void printRootHeader ( PrintStream out) {
		out.println("import org.simpleframework.xml.Namespace;");
		out.println("import org.simpleframework.xml.NamespaceList;");
		out.println("import org.simpleframework.xml.Root;");
		out.println("");
		out.println("@Root(strict=true)") ;
		out.println("@NamespaceList({"); 
		out.println("@Namespace(reference=\"http://varioml.org/xml/1.0\"),");
		out.println("@Namespace(reference=\"http://www.w3.org/2001/XMLSchema-instance\",prefix=\"xsi\")");
		out.println("})");			
	}
	
	public void printOtherRootHeader ( PrintStream out) {
		out.println("import org.simpleframework.xml.Root;");
		out.println("");
		out.println("@Root(strict=true)") ;		
	}

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

	/**
	 * generate some code for the generator itself
	 * @param mappings
	 */
	public void printExampleElements (HashMap mappings) {
	
		HashMap<String,Counter>  map = new HashMap<String, Counter>();
		NodeList nodes = doc.getChildNodes();
		for ( int ix = 0 ; ix< nodes.getLength() ; ix++ ) {
			fillTheMap( nodes.item(ix), map) ;
		}
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
			System.out.println("xu.generateCode(\"org.varioml.data."+className+"\",\""+path+"\",x(typeMap,new HashMap<String,String>(){ ");
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

	public static GenerateSimpleXMLCode createInstance(String file) {

		XPathFactory xfac = XPathFactory.newInstance();
		XPath xpath = xfac.newXPath();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		File _file = Util.findFile(file);
		if (!_file.exists()) {
			Util.fatal(GenerateSimpleXMLCode.class, "file " + file + " do not exist");
		}
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);

		} catch (Exception ex) {
			Util.fatal(GenerateSimpleXMLCode.class, ex);
		}

		return new GenerateSimpleXMLCode(doc, xpath, file);

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
	public boolean generateCode(String file, String xpath, Map<String,String> typeMap, boolean isInterface) throws IOException {
		boolean res = false;
		
		if ( isInterface ) {
			throw new RuntimeException("Interface generation is not supported") ; 
		}
		String filePath[] = file.split("\\.");
		String className = filePath[filePath.length-1];

		Node nod = getNode(xpath);
		
		NamedNodeMap att = nod.getAttributes();

		File fileOut = new File("src/org/varioml/data/"+className+".java");
		if ( fileOut.exists())  {
			if ( overwrite ) {
				fileOut.delete();
			} else {
				Util.log(GenerateSimpleXMLCode.class, "File "+fileOut.getPath()+" not over witten");
			}
		} 
		fileOut.createNewFile();
		PrintStream out = new PrintStream(fileOut);

		
		String pkgName = ""; String DOT="";
		for (int i = 0; i < filePath.length - 1 ; i++) {
			pkgName = pkgName+DOT+filePath[ i] ;
			DOT = ".";
		} 
		
		
		out.println("package "+pkgName+";");
		out.println("import java.util.ArrayList;");
		out.println("import java.util.List;");
		Style camelStyle = new CamelCaseStyle();

		
		if ( nod.getParentNode().getNodeType() == Node.DOCUMENT_NODE) {
			printRootHeader(out) ;
		} else {
			printOtherRootHeader(out) ;
			
		}
		String elems[] = getElementNames(nod);
		out.println("");
		if ( isInterface ) {

			out.println("public interface "+className+" {") ;
			out.println("	//xml-element used for code generation: "+xpath);
			out.println();

		} else {

			String orderLine = "@org.simpleframework.xml.Order(elements={";
			for (int i = 0; i < elems.length; i++) {
				//String name = camelStyle.getElement( elems[i]);
				//orderLine = orderLine+"\""+name+"\"";
				orderLine = orderLine+"\""+elems[i]+"\"";
				if ( i  < elems.length - 1) {
					orderLine = orderLine+",";
				}
			}

			out.println( orderLine+"})");
			out.println("public class "+className+" {") ;
			out.println("	//xml-element used for code generation: "+xpath);
			out.println();

			out.println("	public "+className+"(  ) {");
			out.println("	}");
		}
		

		boolean elements = false; 
		for (int i = 0; i < att.getLength(); i++) {

			String elName = att.item(i).getNodeName();
			String  typeName = camelStyle.getElement(elName);
			char c = Character.toLowerCase(typeName.charAt(0)) ;
			String paramName = ""+c;

			for (int j = 1; j < typeName.length(); j++) {
				paramName = paramName + typeName.charAt(j);
			}			
			//todo: bit ugly hack..
			String realType = typeName;
			if ( typeMap.get("@"+elName) != null ) {
				realType= typeMap.get("@"+elName);
			}

			paramName = "attr_"+ paramName;
			out.println(" ");
			
			if ( isInterface ) {

				out.println("	// ===========-- "+elName+" --===========");
				out.println("	public void set"+typeName+"Attr( "+realType+" "+paramName+") ;");
				out.println("	public "+realType+" get"+typeName+"Attr() ; ");

			} else {

				out.println("	// ===========-- "+elName+" --===========");
				out.println("	@org.simpleframework.xml.Attribute(required=false,name=\""+elName+"\")");
				out.println("	private "+realType+" _" + paramName+" ;");
				out.println("	public void set"+typeName+"Attr( "+realType+" "+paramName+") { ");
				out.println("		this._"+paramName+" = "+paramName+" ;");
				out.println("	}");
				out.println("	public "+realType+" get"+typeName+"Attr() { ");
				out.println("		return this._"+paramName+";");
				out.println("	}");
				
			}
			
		}

		NodeList elem = nod.getChildNodes();		
		
		
		String lastElement="";
		for (int i = 0; i < elem.getLength(); i++) {

			//hasTextNode = elem.item(i).getNodeType() == Node.TEXT_NODE || hasTextNode   ;
			
			if (elem.item(i).getNodeType() == Node.TEXT_NODE)
				continue;
			if (elem.item(i).getNodeName().equals(lastElement))
				continue;

			String elName = elem.item(i).getNodeName();
			lastElement = elName;

			String typeName = camelStyle.getElement(elName);
			char c = Character.toLowerCase(typeName.charAt(0)) ;
			String paramName = ""+c;

			for (int j = 1; j < typeName.length(); j++) {
				paramName = paramName + typeName.charAt(j);
			}

			//todo: bit ugly hack..
			String realType = typeName;
			if ( typeMap.get(elName) != null ) {
				realType= typeMap.get(elName);
			}

			if (XMLUtil.getNextNonTextSiblingNode(elem,i) != null && XMLUtil.getNextNonTextSiblingNode(elem,i).getNodeName().equals(elName)) {
				elements = true; 

				out.println(" ");

				if ( isInterface ) {

					out.println("	// ===========-- "+elName+" --===========");
					out.println("	public void set"+typeName+"List( List<"+realType+"> "+paramName+") ; ");
					out.println("	public List<"+realType+"> get"+typeName+"List()  ; ");
					out.println("	public void add"+typeName+"("+realType+" item ) ; ");
					
				} else {

					out.println("	// ===========-- "+elName+" --===========");
					//out.println("	@ElementList(required=false,inline=true,name=\""+elName+"\") ");
					out.println("	@org.simpleframework.xml.ElementList(required=false,inline=true,entry=\""+elName+"\") ");
					out.println("	private List<"+ realType+"> _" + paramName + " ;");
					out.println("	public void set"+typeName+"List( List<"+realType+"> "+paramName+") { ");
					out.println("		this._"+paramName+" = "+paramName+" ;");
					out.println("	}");
					out.println("	public List<"+realType+"> get"+typeName+"List()  { ");
					out.println("		return this._"+paramName+";");
					out.println("	}");
					out.println("	public void add"+typeName+"("+realType+" item ) { ");
					out.println("		if ( this._"+paramName+" == null ) { ");
					out.println("			this._"+paramName+" = new ArrayList<"+realType+">();");
					out.println("		}");
					out.println("		this._"+paramName+".add( item);");
					out.println("	}");
				}
				
			} else {
				
				elements = true; 
				out.println(" ");

				if ( isInterface ) {
					
					out.println("	// ===========-- "+elName+" --===========");
					out.println("	public void set"+typeName+"( "+realType+" "+paramName+") ; ");
					out.println("	public "+realType+" get"+typeName+"() ;");
					
				} else {
					out.println("	// ===========-- "+elName+" --===========");
					out.println("	@org.simpleframework.xml.Element(required=false,name=\""+elName+"\") ");
					out.println("	private "+realType+" _" + paramName + " ;");
					out.println("	public void set"+typeName+"( "+realType+" "+paramName+") { ");
					out.println("		this._"+paramName+" = "+paramName+" ;");
					out.println("	}");
					out.println("	public "+realType+" get"+typeName+"() {");
					out.println("		return this._"+paramName+";");
					out.println("	}");
				}
			}
			
		}

		String value = null;
		boolean hasTextNode = false;

		for (int i = 0; i < elem.getLength() && ! hasTextNode; i++) {
			if (elem.item(i).getNodeType() == Node.TEXT_NODE ) {
				value = elem.item(i).getNodeValue();
				if ( value != null ) {
					value = value.replaceAll("\\s", "");
					if ( value.length() > 0 ) { 
						hasTextNode = true;
					}					
				}
				
			}
		}
		
		if ( hasTextNode || typeMap.get(TEXT_NODE) != null ) { 

			if ( elements) {
				System.err.println("Mixed type element "+className+" value >"+value+"<");
			}
			String typeName =  typeMap.get(TEXT_NODE);
			if ( typeName ==  null) {
				typeName = "String";
			}

			String fullTypeName=typeName; //todo: fix to other places as well
			if ( typeName.contains(".")) {
				String pks[] = typeName.split("\\.");
				typeName = pks[pks.length -1 ];
			}
			String paramName = "__"+typeName.toLowerCase();
			
			out.println("	// =========-- TEXT NODE --=========");
			out.println("	@org.simpleframework.xml.Text(required=false)");
			out.println("	private "+fullTypeName+" "+paramName + " ;");
			out.println("	public "+className+"( "+fullTypeName+" v ) {");
			out.println("		this."+paramName+"= v ;");
			out.println("	}");
			out.println("	public void set"+typeName+"( "+fullTypeName+" v ) { ");
			out.println("		this."+paramName+"= v ;");
			out.println("	}");
			out.println("	public "+fullTypeName+" get"+typeName+"() {");
			out.println("		return this."+paramName+";");
			out.println("	}");
			//hasTextNode = false; //todo: hack
			
		}

		out.println("}");

		return res;
	}

	public static HashMap<String, String> x( HashMap<String, String> map, HashMap<String, String> aMap) {
		HashMap<String, String> nw = (HashMap<String, String>) map.clone();
		nw.putAll(aMap);
		return nw;
	}
	public static void main(String[] args) throws Exception{

		//assign the basic types
		HashMap<String, String> typeMap = new HashMap<String, String>(){
			{	
				//attributes
				put("@name","String"); 
				put("@uri","String"); 
				put("@id","String");
				put("@term","String"); 
				put("@accession","String"); 
				put("@content_type","String"); 
				put("@encoding","String"); 
				put("@lang","String"); 
				put("@is_undefined","boolean");
				put("@date","org.varioml.util.VarioDate");
				put("@version","String"); 
				put("@role","String"); 
				put("@code","int");
				put("@orientation","String");
				put("@samples","int");
				put("@type","String"); // chk duplicates
				put("@scope","String"); // chk duplicates
				put("@copy_count","double");
				put("@genotypic","boolean");
				put("@subcellular_part","String");
				put("@technique","String");
				put("@template","String");
				put("@transcript_ref","String");
				put("@schema_version","float");
				put("@submissionid_type","String");
				put("@xmlns","String");
				put("@xmlns:xsi","String");
				put("@xsi:schemaLocation","String");
				put("@panel_ref","String");
				put("@size","int");
				put("@scheme","String");
				put("@source","String");
				put("@allele","int");
				//elements
				put("date","org.varioml.util.VarioDate");
				put("source","String");
				put("text","String");  
				put("value","double");
				put("address","String");
				put("phone","String"); 
				put("fax","String");
				put("email","String"); 
				put("address","String");
				put("description","String"); 
				put("dob","org.varioml.util.VarioDate"); 
				put("creation_date","org.varioml.util.VarioDate");
				put("modification_date","org.varioml.util.VarioDate"); 
				put("call","String");
				put("reference","String");
				put("chr","String");
				put("start","long");
				put("end","long");				
				put("url","String");
				put("freq","double");
				put("counts","int");
				put("name","String"); 
				put("embargo_end_date","org.varioml.util.VarioDate");
				put("created","org.varioml.util.VarioDateTime");
				
			 } 
		};  

		GenerateSimpleXMLCode xu = GenerateSimpleXMLCode.createInstance("new_variant.xml");
//		xu.printExampleElements(typeMap);
		
//		xu.generateCode("org.varioml.data.Variant","//variant_group/variant",x(typeMap,new HashMap<String,String>(){ 
//			{put("name","VariantName");};
//			{put("source","Source");};
//		})); 
//		xu.generateCode("org.varioml.data.VariantEvent","//variant_group/variant/haplotype/variant",x(typeMap,new HashMap<String,String>(){ 
//			{put("name","VariantName");};
//			{put("source","Source");};
//		})); 
//
//		xu.generateCode("org.varioml.data.VariantGroup","//variant_group",x(typeMap,new HashMap<String,String>(){ 
//			{put("_","_");};
//		})); 
//
		xu.generateCode("org.varioml.data.Haplotype","//variant_group/variant/haplotype",x(typeMap,new HashMap<String,String>(){ 
			{put("name","VariantName");};
			{put("source","Source");};
		})); 


//		xu = GenerateSimpleXMLCode.createInstance("lsdb_test_all.xml");
////		xu.printExampleElements(typeMap);
//
//		xu.generateCode("org.varioml.data.Lsdb","//lsdb",x(typeMap,new HashMap<String,String>(){ 
//			{put("source","Source");};
//		})); // size=25 2011-06-20 21:40:02		
//
//		xu.generateCode("org.varioml.data.Frequency","//lsdb/individual/variant/frequency",x(typeMap,new HashMap<String,String>(){ 
//			{put("category","FreqCategory");};
//		})); // size=25 2011-06-20 21:40:02
//		xu.generateCode("org.varioml.data.FreqCategory","//lsdb/individual/variant/frequency/category",x(typeMap,new HashMap<String,String>(){ 
//			{put("_","_");};
//		})); // size=25 2011-06-20 21:40:02

		
		if ( true) System.exit(1);

		xu = GenerateSimpleXMLCode.createInstance("cafe_variome_all.xml");
		xu.printExampleElements(typeMap);
		xu.generateCode("org.varioml.data.CafeVariome","//cafe_variome",x(typeMap,new HashMap<String,String>(){ 
			{put("source","Source");};
		})); // size=8 2011-08-29 20:37:47


		if ( true ) return;

		
		
		xu = GenerateSimpleXMLCode.createInstance("lsdb_test_all.xml");
		xu.printExampleElements(typeMap);

		xu.generateCode("org.varioml.data.Lsdb","//lsdb",x(typeMap,new HashMap<String,String>(){ 
			{put("source","Source");};
		})); // size=25 2011-06-20 21:40:02		
		xu.generateCode("org.varioml.data.Comment","//lsdb/comment",x(typeMap,new HashMap<String,String>(){ 
			{put("text","CommentText");};
		})); // size=25 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.EvidenceCode","//lsdb/comment/evidence_code",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=19 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Score","//lsdb/comment/evidence_code/score",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=17 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.ProtocolId","//lsdb/comment/protocol_id",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.CommentText","//lsdb/comment/text",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE,"String");};
		})); // size=4 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.DbXref","//lsdb/db_xref",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Individual","//lsdb/individual",x(typeMap,new HashMap<String,String>(){ 
			{put("role","Role");};
			{put("source","Source");};
		})); // size=53 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Cultivar","//lsdb/individual/cultivar",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Gender","//lsdb/individual/gender",x(typeMap,new HashMap<String,String>(){ 
			{put("description","GenderDescription");};
		})); // size=12 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.GenderDescription","//lsdb/individual/gender/description",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Organism","//lsdb/individual/organism",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.OriginalId","//lsdb/individual/original_id",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Phenotype","//lsdb/individual/phenotype",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=25 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.InheritancePattern","//lsdb/individual/phenotype/inheritance_pattern",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Population","//lsdb/individual/population",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=24 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Relationship","//lsdb/individual/relationship",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Role","//lsdb/individual/role",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.SharingPolicy","//lsdb/individual/sharing_policy",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=14 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.EmbargoEndDate","//lsdb/individual/sharing_policy/embargo_end_date",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE,"org.varioml.util.VarioDate");};
		})); // size=2 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.UsePermission","//lsdb/individual/sharing_policy/use_permission",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Strain","//lsdb/individual/strain",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
//		xu.generateCode("org.varioml.data.Variant","//lsdb/individual/variant",x(typeMap,new HashMap<String,String>(){ 
//			{put("name","VariantName");};
//			{put("source","Source");};
//		})); // size=93 2011-06-20 21:40:02

		xu.generateCode("org.varioml.data.VariantName","//lsdb/individual/variant/name",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE,"String");};
		})); // size=93 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.data.Aliases","//lsdb/individual/variant/aliases",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=21 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Consequence","//lsdb/individual/variant/consequence",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Exon","//lsdb/individual/variant/exon",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=4 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Frequency","//lsdb/individual/variant/frequency",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=25 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Gene","//lsdb/individual/variant/gene",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.GeneticOrigin","//lsdb/individual/variant/genetic_origin",x(typeMap,new HashMap<String,String>(){ 
			{put("source","GeneticSource");};
		})); // size=25 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.data.GeneticSource","//lsdb/individual/variant/genetic_origin/source",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // manual

		xu.generateCode("org.varioml.data.Genotype","//lsdb/individual/variant/genotype",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=11 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Location","//lsdb/individual/variant/location",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=9 2011-06-20 21:40:02

		xu.generateCode("org.varioml.data.Panel","//lsdb/individual/variant/panel",x(typeMap,new HashMap<String,String>(){ 
			{put("source","Source");};
		})); // size=55 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Pathogenicity","//lsdb/individual/variant/pathogenicity",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=33 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.RefSeq","//lsdb/individual/variant/ref_seq",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.RestrictionSite","//lsdb/individual/variant/restriction_site",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Sample","//lsdb/individual/variant/sample",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.data.SeqChanges","//lsdb/individual/variant/seq_changes",x(typeMap,new HashMap<String,String>(){ 
			{put("variant","ConsVariant");};
		})); // size=21 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.ConsVariant","//lsdb/individual/variant/seq_changes/variant",x(typeMap,new HashMap<String,String>(){ 
			{put("name","VariantName");};
			{put("source","Source");};
		})); // size=21 2011-06-20 21:40:02

		
		xu.generateCode("org.varioml.data.Sequence","//lsdb/individual/variant/sequence",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Tissue","//lsdb/individual/variant/tissue",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.TissueDistribution","//lsdb/individual/variant/tissue_distribution",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.VariantClass","//lsdb/individual/variant/variant_class",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.VariantDetection","//lsdb/individual/variant/variant_detection",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.VariantType","//lsdb/individual/variant/variant_type",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02

		xu.generateCode("org.varioml.data.GroupType","//lsdb/individual/variant_group/group_type",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Source","//lsdb/source",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=27 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Acknowledgement","//lsdb/source/acknowledgement",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.GrantNumber","//lsdb/source/acknowledgement/grant_number",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.data.Contact","//lsdb/source/contact",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=24 2011-06-20 21:40:02

	}

}