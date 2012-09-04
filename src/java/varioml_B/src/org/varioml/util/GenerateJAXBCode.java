package org.varioml.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;


import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.simpleframework.xml.stream.CamelCaseStyle;
import org.varioml.util.GenerateSimpleXMLCode.Counter;
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
public class GenerateJAXBCode  extends GenerateSimpleXMLCode {
	
	protected HashMap<String,Counter> elementCounts;
	protected HashMap<String,String> fileGuard = new HashMap<String, String>(); 
	
	private GenerateJAXBCode(Document doc, XPath xpath, String file) {
		super(doc,xpath,file) ;
		HashMap<String,Counter> map = getElementCountMap() ;
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Counter c = map.get(key);
			//System.err.println(key+" "+c.getCount()+" "+c.getName());
		}
		elementCounts  = map;
	}

	public void printRootHeader ( PrintStream out) {
		
		//out.println("import javax.xml.bind.annotation.*;");
//		out.println("");
//		out.println("@NamespaceList({"); 
//		out.println("@Namespace(reference=\"http://varioml.org/xml/1.0\"),");
//		out.println("@Namespace(reference=\"http://www.w3.org/2001/XMLSchema-instance\",prefix=\"xsi\")");
//		out.println("})");			
	}
	
	public void printOtherRootHeader ( PrintStream out) {
//		out.println("import org.simpleframework.xml.Root;");
//		out.println("");
		
    	out.println("@javax.xml.bind.annotation.@XmlRootElement(namespace=\"http://varioml.org/xml/1.0\"") ;		
		//out.println("import javax.xml.bind.annotation.*;");
		//sout.println("");
	}

	
	public String className ( String name) {
		//return name.substring(0,1).toUpperCase() + name.substring(1);
		CamelCaseStyle c = new CamelCaseStyle();
		return c.getElement(name);
	}

	public static GenerateJAXBCode createInstance(String file) {

		XPathFactory xfac = XPathFactory.newInstance();
		XPath xpath = xfac.newXPath();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		File _file = Util.findFile(file);
		if (!_file.exists()) {
			Util.fatal(GenerateJAXBCode.class, "file " + file + " do not exist");
		}
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);

		} catch (Exception ex) {
			Util.fatal(GenerateJAXBCode.class, ex);
		}


		return new GenerateJAXBCode(doc, xpath, file);

	}

	
	public String paramName ( Map<String,String> typeMap) {
		String typeName =  typeMap.get(TEXT_NODE);
		if ( typeName ==  null) {
			typeName = "String";
		}

		if ( typeName.contains(".")) {
			String pks[] = typeName.split("\\.");
			typeName = pks[pks.length -1 ];
		}
		if ( typeName.equals("VMLDate")) { //hack
			System.err.println("VMLDate changed to Date");
			typeName = "Date";
		}
		return typeName;
	}
	
	//Todo: refactor
	public boolean generateCode(String file, String xpath, Map<String,String> typeMap, boolean isInterface) throws IOException {
		boolean res = false;

		if ( fileGuard.containsKey(file)) {
			Util.fatal(GenerateJAXBCode.class, "File "+file+" already generated in this session");
		}
		fileGuard.put(file, "K");
		
		overwrite = true;
		boolean valueName = false; 
		
		CamelCaseStyle camelStyle = new CamelCaseStyle();
		UnderscoreStyle underStyle = new UnderscoreStyle();
		
		if ( isInterface ) {
			throw new RuntimeException("Interface generation is not supported") ; 
		}
		String filePath[] = file.split("\\.");
		String className = filePath[filePath.length-1];

		Node nod = getNode(xpath);
		Counter cnt = elementCounts.get(nod.getNodeName());
		if ( countElements(nod) < cnt.getCount())  {
			Util.log(GenerateJAXBCode.class, "Note: "+cnt.getName()+
					" is better representative than "+xpath+" for "+file+" counts: " +countElements(nod)+"<"+cnt.getCount());
		}
		
		
		NamedNodeMap att = nod.getAttributes();

		File fileOut = new File("src/org/varioml/jaxb/"+className+".java");
		if ( fileOut.exists())  {
			if ( overwrite ) {
				fileOut.delete();
			} else {
				Util.log(GenerateJAXBCode.class, "File "+fileOut.getPath()+" not over witten");
				return false;
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
		out.println();
		//Style camelStyle = new CamelCaseStyle();

		
		if ( nod.getParentNode().getNodeType() == Node.DOCUMENT_NODE) {
			//printRootHeader(out) ;

			out.println("@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)");
			out.println("@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)");
	    	out.println("@javax.xml.bind.annotation.XmlRootElement(namespace=\"http://varioml.org/xml/1.0\",name=\""+underStyle.getElement(className)+ "\")") ;
	    	out.println("@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)");

		} else {
			
			out.println("@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)");
			out.println("@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)");
	    	out.println("@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)");
	    	out.println("@javax.xml.bind.annotation.XmlRootElement(namespace=\"http://varioml.org/xml/1.0\",name=\""+underStyle.getElement(className)+ "\")") ;		
			
		}
		String elems[] = getElementNames(nod);

		if ( isInterface ) {

			out.println("public interface "+className+" {") ;
			out.println("	//xml-element used for code generation: "+xpath);
			out.println();

		} else {

			//out.println("@javax.xml.bind.annotation.XmlElement(name=\""+underStyle.getElement(className)+"\")");
			String orderLine = "";
			for (int i = 0; i < elems.length; i++) {
				//String name = camelStyle.getElement( elems[i]);
				//orderLine = orderLine+"\""+name+"\"";
				
				//TODO: to subroutine... used elsewhere 
				String typeName = camelStyle.getElement(elems[i]);
				char c = Character.toLowerCase(typeName.charAt(0)) ;
				String paramName = ""+c;

				for (int j = 1; j < typeName.length(); j++) {
					paramName = paramName + typeName.charAt(j);
				}
				if ( className.equals("Frequency") && (paramName.equals("freq") || paramName.equals("counts") || paramName.equals("category")) ) {
					paramName = "counts\",\"_category\",\"_freq";
				}

				orderLine = orderLine+"\"_"+paramName+"\""; // with JAXB we use Java field names not XML names
				if ( i  < elems.length - 1) {
					orderLine = orderLine+",";
				}
			}

			String attribOrderLine = "";
			for (int i = 0; i < att.getLength(); i++) {
				//String name = camelStyle.getElement( elems[i]);
				//orderLine = orderLine+"\""+name+"\"";
				
				//TODO: to subroutine... used elsewhere 
				String typeName = camelStyle.getElement(att.item(i).getNodeName());
				char c = Character.toLowerCase(typeName.charAt(0)) ;
				String paramName = ""+c;

				for (int j = 1; j < typeName.length(); j++) {
					paramName = paramName + typeName.charAt(j);
				}

				attribOrderLine = attribOrderLine+"\"_attr_"+paramName+"\""; // with JAXB we use Java field names not XML names
				if ( i  < att.getLength() - 1) {
					attribOrderLine = attribOrderLine+",";
				}
			}

			if ( orderLine.length() > 0)
				out.println(  "@javax.xml.bind.annotation.XmlType(propOrder = {  "+orderLine+"})");

			if (  hasTextNode(nod.getChildNodes()) || typeMap.get(TEXT_NODE) != null ) {
				if  (orderLine.length() > 0) {
					Util.fatal(GenerateJAXBCode.class, "Cannot have mixed nodes");
				}
				String paramName = "__"+paramName(typeMap).toLowerCase();
				orderLine = "\""+paramName+"\"";
				System.err.println("Check JSON: "+ orderLine);
				
			}
			if ( attribOrderLine.length() > 0 && orderLine.length() > 0) {
				attribOrderLine = attribOrderLine +",";
			}			
			if ( orderLine.length() > 0 || attribOrderLine.length()>0)
				out.println(  "@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "+attribOrderLine+ orderLine+"})");
			
			out.println("");
			out.println(""); /**/ /**/
			out.println("public class "+className+" /**/ /**/ {") ;
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
			
			if ( typeName.equals("value") ) {
				valueName = true;
			}
			//todo: bit ugly hack..
			String realType = typeName;
			if ( typeMap.get("@"+elName) != null ) {
				realType= typeMap.get("@"+elName);
			}

			paramName = "attr_"+ paramName;
			out.println(" ");
			
			if ( isInterface ) {

//				out.println("	// ===========-- "+elName+" --===========");
//				out.println("	@Deprecated");
//				out.println("	public void set"+typeName+"Attr( "+realType+" "+paramName+") ;");
//				out.println("	public "+realType+" get"+typeName+"Attr() ; ");

			} else {

				out.println("	// ===========-- "+elName+" --===========");
				out.println("	@javax.xml.bind.annotation.XmlAttribute(required=false,name=\""+elName+"\")");
				out.println("	private "+realType+" _" + paramName+" ;");
				
//				out.println("	@Deprecated");
//				out.println("	public void set"+typeName+"Attr( "+realType+" "+paramName+") { ");
//				out.println("		this._"+paramName+" = "+paramName+" ;");
//				out.println("	}");
//				out.println(	"@Deprecated");
//				out.println("	public "+realType+" get"+typeName+"Attr() { ");
//				out.println("		return this._"+paramName+";");
//				out.println("	}");
				
				//support this
				out.println("	public void set"+typeName+"( "+realType+" "+paramName+") { ");
				out.println("		this._"+paramName+" = "+paramName+" ;");
				out.println("	}");
				
				if ( typeName.startsWith("Coded")) {
					typeName = " isCoded";
					System.err.println("Method name chnaged to: " + typeName);
				} else if ( typeName.startsWith("Genotypic")) {
						typeName = " isGenotypic";
						System.err.println("Method name chnaged to: " + typeName);
				} else if ( typeName.startsWith("IsUndefined")) {
					typeName = " isUndefined";
					System.err.println("Method name chnaged to: " + typeName);
				} else {
					typeName =" get"+typeName ;
				}
				out.println("	public "+realType+typeName+"() { ");
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


				out.println("	// ===========-- "+elName+" --===========");
				//Json lists are in plural
				if ( elName.endsWith("y")) { 
					String x= elName.replaceAll("y$", "ie")+"s";
					out.println("   @org.codehaus.jackson.annotate.JsonProperty(\""+x+"\")"); 												
					System.out.println("CHECK Plural: " + x+" ");
				} else {
					out.println("   @org.codehaus.jackson.annotate.JsonProperty(\""+elName+"s\")"); 						
				}
				
				out.println("   @javax.xml.bind.annotation.XmlElement(required=false,name=\""+elName+
						"\",type="+realType+".class" +
						",namespace=\"http://varioml.org/xml/1.0\")" );
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
				
			} else {
				
				elements = true; 
				out.println(" ");
				
				//todo: fix hacking
				String usedName = typeName ;
				if ( className.equals("GeneticOrigin") && typeName.equals("Source")) {
					usedName = "GeneticSource";
				}
				
				if ( className.equals("Frequency") && (elName.equals("freq") || elName.equals("category") || elName.equals("counts"))) {
					//choice group

					out.println("	// ===========-- counts --===========");
					out.println("   @javax.xml.bind.annotation.XmlElement(required=false,name=\"counts\",type=Integer.class,namespace=\"http://varioml.org/xml/1.0\")");
					out.println("	private Integer _counts ;");
					out.println("	public void setCounts( Integer count) { ");
					out.println("		this._counts = count ; if ( _category != null || _freq != null ) org.varioml.util.Util.fatal(Frequency.class,\" frequency choice group support only one of following: freq,counts and category \");");
					out.println("	}");
					out.println("	public Integer getCounts() {");
					out.println("		return this._counts;");
					out.println("	}");

					out.println("	// ===========-- category --===========");
					out.println("   @javax.xml.bind.annotation.XmlElement(required=false,name=\"category\",type=FreqCaregory.class,namespace=\"http://varioml.org/xml/1.0\")");
					out.println("	private FreqCategory _category ;");
					out.println("	public void setCategory( FreqCategory category) { ");
					out.println("		this._category = category ; if ( _counts != null || _freq != null ) org.varioml.util.Util.fatal(Frequency.class,\" frequency choice group support only one of following: freq,counts and category \");");
					out.println("	}");
					out.println("	public FreqCategory getCategory() {");
					out.println("		return this._category;");
					out.println("	}");

					out.println("	// ===========-- freq --===========");
					out.println("   @javax.xml.bind.annotation.XmlElement(required=false,name=\"freq\",type=Double.class,namespace=\"http://varioml.org/xml/1.0\")");
					out.println("	private Double _freq ;");
					out.println("	public void setFreq( Double freq) { ");
					out.println("		this._freq = freq ; if ( _category != null || _counts != null ) org.varioml.util.Util.fatal(Frequency.class,\" frequency choice group support only one of following: freq,counts and category \");");
					out.println("	}");
					out.println("	public Double  getFreq() {");
					out.println("		return this._freq;");
					out.println("	}");

					System.out.println("Frequency done");
					
				} else {

					out.println("	// ===========-- "+elName+" --===========");
					out.println("	@javax.xml.bind.annotation.XmlElement(required=false,name=\""+elName+
							"\",type="+realType+".class" +
							",namespace=\"http://varioml.org/xml/1.0\")");
					if ( ! usedName.equals(typeName)) {
						out.println("	@org.codehaus.jackson.annotate.JsonProperty(\""+underStyle.getElement(usedName)+"\")");	
					}
					out.println("	private "+realType+" _" + paramName + " ;");
					out.println("	public void set"+usedName+"( "+realType+" "+paramName+") { ");
					out.println("		this._"+paramName+" = "+paramName+" ;");
					out.println("	}");
					out.println("	public "+realType+" get"+usedName+"() {");
					out.println("		return this._"+paramName+";");
					out.println("	}");

				}
		
			}
			
		}

		boolean hasTextNode = hasTextNode(elem);


		if ( hasTextNode || typeMap.get(TEXT_NODE) != null ) { 

//			if (valueName) {
//				Util.fatal(GenerateJAXBCode.class, "Cannot use value attribute. It is reserved");
//			}

			if ( elements) {
				System.err.println("Check Schema. Mixed type element "+className+" value. ");
			}
			String fullTypeName =  typeMap.get(TEXT_NODE);
			String typeName  = paramName(typeMap);
			if ( typeName ==  null) {
				typeName = "String";
			}

			String paramName = "__"+typeName.toLowerCase();
			
			out.println("	// =========-- TEXT NODE --=========");
			out.println("	@org.codehaus.jackson.annotate.JsonProperty(\""+typeName.toLowerCase()+"\")");
			out.println("   @javax.xml.bind.annotation.XmlValue") ;
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

	private boolean hasTextNode(NodeList elem) {
		// TODO Auto-generated method stub
		boolean hasTextNode = false;
		String value = null;
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
		
		return hasTextNode;
	}

	public static void main(String[] args) throws Exception{

		
		//assign the basic types
		HashMap<String, String> typeMap = new HashMap<String, String>(){
			{	
				//attributes
				put("@name","String"); 
				put("@obs","String"); 
				put("@uri","String"); 
				put("@id","String");
				put("@term","String"); 
				put("@accession","String"); 
				put("@content_type","String"); 
				put("@encoding","String"); 
				put("@lang","String"); 
				put("@is_undefined","Boolean");
				put("@coded","Boolean");
				put("@date","org.varioml.util.VMLDate");
				put("@version","String"); 
				put("@role","String"); 
				put("@code","Integer");
				put("@orientation","String");
				put("@samples","Integer");
				put("@type","String"); // chk duplicates
				put("@scope","String"); // chk duplicates
				put("@copy_count","Double");
				put("@genotypic","Boolean");
				put("@subcellular_part","String");
				put("@technique","String");
				put("@template","String");
				put("@transcript_ref","String");
				put("@schema_version","Float");
				put("@submissionid_type","String");
				put("@xmlns","String");
				put("@xmlns:xsi","String");
				put("@xsi:schemaLocation","String");
				put("@panel_ref","String");
				put("@size","Integer");
				put("@scheme","String");
				put("@val","String");
				put("@nval","Double");
				put("@source","String");
				put("@unit","String");
				put("@allele","Integer");
				//elements
				put("date","org.varioml.util.VMLDate");
				put("source","String");
				put("text","String");  
				put("address","String");
				put("phone","String"); 
				put("fax","String");
				put("email","String"); 
				put("address","String");
				put("description","String"); 
				put("dob","org.varioml.util.VMLDate"); 
				put("creation_date","org.varioml.util.VMLDate");
				put("modification_date","org.varioml.util.VMLDate"); 
				put("call","String");
				put("reference","String");
				put("chr","String");
				put("start","Long"); 
				put("end","Long");				
				put("url","String");
				put("freq","Double");
				put("counts","Integer");
				put("name","String"); 
				put("embargo_end_date","org.varioml.util.VMLDate");
				put("created","org.varioml.util.VMLDateTime");
				
			 } 
		};  

		if ( true) {
			GenerateJAXBCode xu = GenerateJAXBCode.createInstance("templates/lsdb1.xml");
			xu.generateCode("org.varioml.jaxb.Variant","//lsdb/variant",x(typeMap,new HashMap<String,String>(){ 
				{put("name","VariantName");};
				{put("source","Source");};
			})); 
			return;
		}

		
		if ( false ) { // these are removed from the code below
			System.err.println("Generating");
			GenerateJAXBCode xu0 = GenerateJAXBCode.createInstance("templates/pathogenicity1.xml");

			xu0.generateCode("org.varioml.jaxb.Value","//pathogenicity/factor/value",x(typeMap,new HashMap<String,String>(){ 
				{put("_","_");};
			})); // size=33 2011-06-20 21:40:02

			xu0.generateCode("org.varioml.jaxb.Factor","//pathogenicity/factor",x(typeMap,new HashMap<String,String>(){ 
				{put("_","_");};
			})); // size=33 2011-06-20 21:40:02

			xu0.generateCode("org.varioml.jaxb.Pathogenicity","//pathogenicity",x(typeMap,new HashMap<String,String>(){ 
				{put("_","_");};
			})); // size=33 2011-06-20 21:40:02
			
//			GenerateJAXBCode xu0 = GenerateJAXBCode.createInstance("templates/seq_region1.xml");
//			xu0.generateCode("org.varioml.jaxb.SeqRegion","//seq_region",x(typeMap,new HashMap<String,String>(){ 
//		    })); 
//			xu0.generateCode("org.varioml.jaxb.Panel","//lsdb/panel",x(typeMap,new HashMap<String,String>(){ 
//				{put("source","Source");};
//			})); // size=55 2011-06-20 21:40:02

			return;
			
		}
		
		if ( false) {

		
		GenerateJAXBCode xu2 = GenerateJAXBCode.createInstance("templates/cafe_variome1.xml");
		//xu.printExampleElements(typeMap);
		xu2.generateCode("org.varioml.jaxb.CafeVariome","//cafe_variome",x(typeMap,new HashMap<String,String>(){ 
			{put("source","Source");};
		})); // size=8 2011-08-29 20:37:47

		xu2.generateCode("org.varioml.jaxb.FreqCategory","//cafe_variome/variant/frequency/category",x(typeMap,new HashMap<String,String>(){
	        {put("_","_");};
		})); // size=15 2011-12-11 19:51:02

		}

		GenerateJAXBCode xu = GenerateJAXBCode.createInstance("templates/lsdb1.xml");

		if ( true ) {

		xu.generateCode("org.varioml.jaxb.Age","//lsdb/individual/age",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE ,"Float");};
		})); // 


		xu.generateCode("org.varioml.jaxb.ObservationDate","//lsdb/variant/observation_date",
				x(typeMap,new HashMap<String,String>(){ 
					{put("@age","Float");};
	    })); 

		return ; 
		}
		xu.generateCode("org.varioml.jaxb.EmbargoEndDate","//lsdb/individual/sharing_policy/embargo_end_date",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE,"org.varioml.util.VMLDate");};
		})); // size=2 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.jaxb.ObservationDate","//lsdb/variant/observation_date",
				x(typeMap,new HashMap<String,String>(){ 
					{put("age","Float");};
	    })); 

		
		xu.generateCode("org.varioml.jaxb.Value","//lsdb/variant/value",x(typeMap,new HashMap<String,String>(){ 
	    })); 

//		xu.generateCode("org.varioml.jaxb.SeqRegion","//lsdb/variant/seq_region",x(typeMap,new HashMap<String,String>(){ 
//	    })); 
		
		xu.generateCode("org.varioml.jaxb.Observation","//lsdb/individual/observation",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // 

		xu.generateCode("org.varioml.jaxb.Age","//lsdb/individual/age",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE ,"Float");};
		})); // 
		
		xu.generateCode("org.varioml.jaxb.Variant","//lsdb/variant",x(typeMap,new HashMap<String,String>(){ 
			{put("name","VariantName");};
			{put("source","Source");};
		})); 
		xu.generateCode("org.varioml.jaxb.VariantEvent","//lsdb/variant/haplotype/variant",x(typeMap,new HashMap<String,String>(){ 
			{put("name","VariantName");};
			{put("source","Source");};
		})); 

		xu.generateCode("org.varioml.jaxb.VariantGroup","//variant_group",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); 

		xu.generateCode("org.varioml.jaxb.Haplotype","//variant_group/variant/haplotype",x(typeMap,new HashMap<String,String>(){ 
			{put("name","VariantName");};
			{put("variant","VariantEvent");};
			{put("source","Source");};
		})); 


		xu.generateCode("org.varioml.jaxb.Lsdb","//lsdb",x(typeMap,new HashMap<String,String>(){ 
			{put("source","Source");};
		})); // size=25 2011-06-20 21:40:02		


//Do not generate Frequency. It is modified by hand
//		xu.generateCode("org.varioml.jaxb.Frequency","//lsdb/individual/variant/frequency",x(typeMap,new HashMap<String,String>(){ 
//			{put("_","_");};
//		}));
		
		//if ( true) System.exit(1);


		//if ( false ) return;

		
		

		xu.generateCode("org.varioml.jaxb.Comment","//lsdb/comment",x(typeMap,new HashMap<String,String>(){ 
			{put("text","CommentText");};
		})); // size=25 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.EvidenceCode","//lsdb/variant/evidence_code",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=19 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Score","//lsdb/variant/evidence_code/score",x(typeMap,new HashMap<String,String>(){ 
			{put("value","Double");};
		})); // size=17 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.ProtocolId","//lsdb/variant/protocol_id",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.CommentText","//lsdb/comment/text",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE,"String");};
		})); // size=4 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.DbXref","//lsdb/db_xref",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Individual","//lsdb/individual",x(typeMap,new HashMap<String,String>(){ 
			{put("role","Role");};
			{put("source","Source");};
		})); // size=53 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Cultivar","//lsdb/individual/cultivar",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Gender","//lsdb/individual/gender",x(typeMap,new HashMap<String,String>(){ 
			{put("description","GenderDescription");};
		})); // size=12 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.GenderDescription","//lsdb/individual/gender/description",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Organism","//lsdb/individual/organism",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.OriginalId","//lsdb/individual/original_id",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Phenotype","//lsdb/individual/phenotype",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=25 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.InheritancePattern","//lsdb/individual/phenotype/inheritance_pattern",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Population","//lsdb/individual/population",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=24 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Relationship","//lsdb/individual/relationship",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Role","//lsdb/individual/role",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.SharingPolicy","//lsdb/individual/sharing_policy",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=14 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.UsePermission","//lsdb/individual/sharing_policy/use_permission",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Strain","//lsdb/individual/strain",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
//		xu.generateCode("org.varioml.jaxb.Variant","//lsdb/individual/variant",x(typeMap,new HashMap<String,String>(){ 
//			{put("name","VariantName");};
//			{put("source","Source");};
//		})); // size=93 2011-06-20 21:40:02

		xu.generateCode("org.varioml.jaxb.VariantName","//lsdb/individual/variant/name",x(typeMap,new HashMap<String,String>(){ 
			{put(TEXT_NODE,"String");};
		})); // size=93 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.jaxb.Aliases","//lsdb/individual/variant/aliases",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=21 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Consequence","//lsdb/individual/variant/consequence",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=23 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Exon","//lsdb/individual/variant/exon",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=4 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.jaxb.Gene","//lsdb/individual/variant/gene",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.GeneticOrigin","//lsdb/individual/variant/genetic_origin",x(typeMap,new HashMap<String,String>(){ 
			{put("source","GeneticSource");};
		})); // size=25 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.jaxb.GeneticSource","//lsdb/individual/variant/genetic_origin/source",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // manual

		xu.generateCode("org.varioml.jaxb.Genotype","//lsdb/individual/variant/genotype",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=11 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Location","//lsdb/individual/variant/location",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=9 2011-06-20 21:40:02

//		xu.generateCode("org.varioml.jaxb.Panel","//lsdb/panel",x(typeMap,new HashMap<String,String>(){ 
//			{put("source","Source");};
//		})); // size=55 2011-06-20 21:40:02

		xu.generateCode("org.varioml.jaxb.Factor","//lsdb/variant/pathogenicity/factor",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=33 2011-06-20 21:40:02

		xu.generateCode("org.varioml.jaxb.Pathogenicity","//lsdb/variant/pathogenicity",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=33 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.RefSeq","//lsdb/individual/variant/ref_seq",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.RestrictionSite","//lsdb/individual/variant/restriction_site",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Sample","//lsdb/individual/variant/sample",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		
		xu.generateCode("org.varioml.jaxb.SeqChanges","//lsdb/individual/variant/seq_changes",x(typeMap,new HashMap<String,String>(){ 
			{put("variant","ConsVariant");};
		})); // size=21 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.ConsVariant","//lsdb/variant/seq_changes/variant",x(typeMap,new HashMap<String,String>(){ 
			{put("name","VariantName");};
			{put("source","Source");};
		})); // size=21 2011-06-20 21:40:02

		
		xu.generateCode("org.varioml.jaxb.Sequence","//lsdb/individual/variant/sequence",x(typeMap,new HashMap<String,String>(){ 
			{put("variant","String");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Tissue","//lsdb/individual/variant/tissue",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.TissueDistribution","//lsdb/individual/variant/tissue_distribution",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.VariantClass","//lsdb/individual/variant/variant_class",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.VariantDetection","//lsdb/individual/variant/variant_detection",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.VariantType","//lsdb/individual/variant/variant_type",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02

		xu.generateCode("org.varioml.jaxb.GroupType","//lsdb/individual/variant_group/group_type",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=15 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Source","//lsdb/source",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=27 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Acknowledgement","//lsdb/source/acknowledgement",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.GrantNumber","//lsdb/source/acknowledgement/grant_number",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=13 2011-06-20 21:40:02
		xu.generateCode("org.varioml.jaxb.Contact","//lsdb/source/contact",x(typeMap,new HashMap<String,String>(){ 
			{put("_","_");};
		})); // size=24 2011-06-20 21:40:02

	}

}