package org.varioml.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.management.RuntimeErrorException;
import javax.naming.ldap.Rdn;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RNGMetadataAPI {

	final private Document doc;
	final private XPath xpath;
	final private String file;

	public static class MetaData {
		// no getters and setters any more..
		
		public static class NodeType {
			private String name ;
			public NodeType( String name ) {
				this.name = name; 
			}
			public String toString() {
				return name;
			}
		}
		public String name;
		public boolean isRootNode = false;
		public int min = 0;
		public int max = 1;
		final public static int UNDEFINED = -9999;
		final public static NodeType XML_ELEMENT = new NodeType("element_node");
		final public static NodeType XML_ATTRIBUTE = new NodeType("attribute_node");
		final public static NodeType XML_TEXT = new NodeType("text_node");
		final public static NodeType XML_UNKNOWN = new NodeType("UNKNOWN_node");
		public NodeType nodeType = XML_UNKNOWN;
		
		public String defaultValue = "";
		public List<String> choiceList = new ArrayList<String>();
		public List<String> patternList = new ArrayList<String>();
		public String dataType = "";

		public String documentation = "";
		public List<MetaData> properties = new ArrayList<RNGMetadataAPI.MetaData>();
		public boolean isEmpty = false; // text node todo: chekc implementation of text nodex (non empty element)

		
		public MetaData() {
			name = "-???-";
		}

		public MetaData(String name) {
			this.name = name;
			this.isRootNode = true;
		}

		public MetaData(String name, NodeType nodeType, int min, int max) {
			super();
			this.name = name;
			this.nodeType = nodeType;
			this.min = min;
			this.max = max;
		}

		public String getName() {
			return name;
		}

		public void addProperty(MetaData property) {
			properties.add(property);
		}

		public void addChoice(String choice) {
			choiceList.add(choice);
		}

		public void addPattern(String choice) {
			patternList.add(choice);
		}

		public String toString() {
			String patterns = "";
			for (String type : patternList) {
				patterns = patterns + " "+ type;
			}
			String choice = "";
			for (String c : choiceList) {
				choice = choice + " "+ c;
			}
			return name + " Type: " + nodeType+ " Multp: (" + min +"-" + max + ") Data type: " + dataType 
					+ " Values: " + choice + " Doc: "
					+ documentation + " Gramma patterns: " + patterns;
		}

	}

	private RNGMetadataAPI(Document doc, XPath xpath, String file) {
		this.doc = doc;
		this.xpath = xpath;
		this.file = file;
	}

	public String checkType(String type) {
		return type;
	}

	public static RNGMetadataAPI createInstance(String file) {

		XPathFactory xfac = XPathFactory.newInstance();
		XPath xpath = xfac.newXPath();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		File _file = Util.findFile(file);
		if (!_file.exists()) {
			Util.fatal(RNGMetadataAPI.class, "file " + file + " do not exist");
		}
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);

		} catch (Exception ex) {
			Util.fatal(RNGMetadataAPI.class, ex);
		}

		return new RNGMetadataAPI(doc, xpath, file);

	}

	public Node findXMLNode(String expression) {

		NodeList result = null;
		try {
			XPathExpression expr = xpath.compile(expression);
			result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			Util.fatal(RNGMetadataAPI.class, e);
		}

		if (result == null || result.getLength() == 0)
			Util.fatal(RNGMetadataAPI.class, "xml file " + file
					+ " don't have element " + expression);
		return result.item(0);
	}

	public NodeList findAllXMLNodes(String expression) {

		NodeList result = null;
		try {
			XPathExpression expr = xpath.compile(expression);
			result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			Util.fatal(RNGMetadataAPI.class, e);
		}

		if (result == null )
			Util.fatal(RNGMetadataAPI.class, "Error processing xml file " + file
					+ ". null returned " + expression);
		return result;
	}

	
	public MetaData parseAndCreateMetadata(String xpath) throws Exception {
		
		Node node = findXMLNode(xpath);
		return createMetaDataDeep(node);
	}


	protected void annotateMetaDataUsingNodeInfo(MetaData data, Node node) {
		NodeList nds = node.getChildNodes();

		for (int i = 0; i < nds.getLength(); i++) {
			Node nd = nds.item(i);
			if (nd.getNodeType() == Node.ELEMENT_NODE) {
				//System.err.println(nd.getNodeName());
				
				if (nd.getNodeName().endsWith(":documentation")) { 
					
					if ( nd.hasChildNodes() ) {
						Node txtNode  = getTheOnlyChildTextNode(nd);
						data.documentation = data.documentation + txtNode.getNodeValue();						
					}
					
				} else if (nd.getNodeName().endsWith("ref")) { 
					
					String name = nd.getAttributes().getNamedItem("name").getNodeValue();
					if ( name== null) Util.fatal(RNGMetadataAPI.class, "type name is null");
					data.addPattern( name);
					
				} else if ( nd.getNodeName().equals("data")) {

					String type = nd.getAttributes().getNamedItem("type").getNodeValue();
					if ( type == null) Util.fatal(RNGMetadataAPI.class, "type  is null");
					if ( type.length() == 0 ) Util.fatal(RNGMetadataAPI.class, "type is missing");
					data.dataType = type;

				} else if ( nd.getNodeName().equals("text")) {
					data.nodeType = MetaData.XML_TEXT;

				} else if ( nd.getNodeName().equals("value")) {

					Node txt = getTheOnlyChildTextNode(nd);
					if ( txt == null) Util.fatal(RNGMetadataAPI.class, "value do not have text node");
					String value = txt.getNodeValue();
					if ( value == null || value.length() == 0 ) Util.fatal(RNGMetadataAPI.class, "value is empty missing");

				} else if ( nd.getNodeName().equals("choice")) {

					NodeList nodes = nd.getChildNodes() ;
					for ( int j = 0 ; j < nodes.getLength() ; j++ ) {
						if ( nodes.item(j).getNodeType() == Node.ELEMENT_NODE &&  nodes.item(j).getNodeName().equals("value")) {
							Node txt = getTheOnlyChildTextNode(nodes.item(j));
							//System.err.println(txt);
							data.addChoice(txt.getNodeValue());
						} else {
							if (nodes.item(j).getNodeType() != Node.TEXT_NODE) 
								Util.fatal(RNGMetadataAPI.class, "Unknwon choice element "+nodes.item(j).getNodeName()+" "+nodes.item(j).getNodeValue()) ;							
						}
					}

				} else if ( nd.getNodeName().equals("empty")) {

					data.isEmpty  = true;
					
				} else {
					
					Util.fatal(RNGMetadataAPI.class, "Not implemented "+nd.getNodeName()) ;
				}

			}
		}
	}

	public MetaData createAttributeNode(Node node) {
		MetaData data = null;
		Node aName = node.getAttributes().getNamedItem("name");
		if (aName == null || aName.getNodeValue() == null)
			Util.fatal(RNGMetadataAPI.class, "attribute do not have name");
		data = new MetaData(aName.getNodeValue(), MetaData.XML_ATTRIBUTE, 0, 1);
		annotateMetaDataUsingNodeInfo(data, node);
		return data;
	}

	public MetaData createElementNode(Node node) {
		MetaData data = null;
		Node aName = node.getAttributes().getNamedItem("name");
		if (aName == null || aName.getNodeValue() == null)
			Util.fatal(RNGMetadataAPI.class, "element do not have name");
		data = new MetaData(aName.getNodeValue(), MetaData.XML_ELEMENT, 0, 1);
		annotateMetaDataUsingNodeInfo(data, node);
		return data;
	}

	/** use this when we that there should be only one child element**/
	public Node getTheOnlyChildElementNode(Node n) {
		NodeList l = n.getChildNodes();
		Node elem = null;
		String names = "";
		for (int i = 0; i < l.getLength(); i++) {
			if (l.item(i).getNodeType() == Node.ELEMENT_NODE) {
				if (elem != null) {
					Util.fatal(RNGMetadataAPI.class, "Node " + n.getNodeName()
							+ " should have only one sub element. Got also "
							+ names);
				}
				elem = l.item(i);
				names = elem.getNodeName();
			}
		}
		if (elem == null) {
			Util.fatal(RNGMetadataAPI.class, "Node " + n.getNodeName()
					+ " didn't have subelement");
		}
		return elem;
	}

	/** use this when we that there should be only one child text element**/
	public Node getTheOnlyChildTextNode(Node n) {
		//todo: fix copy paste code
		NodeList l = n.getChildNodes();
		Node elem = null;
		String names = "";
		for (int i = 0; i < l.getLength(); i++) {
			if (l.item(i).getNodeType() == Node.TEXT_NODE) {
				if (elem != null) {
					Util.fatal(RNGMetadataAPI.class, "Node " + n.getNodeName()
							+ " should have only one sub element. Got also "
							+ names);
				}
				elem = l.item(i);
				names = elem.getNodeName();
			}
		}
		if (elem == null) {
			Util.fatal(RNGMetadataAPI.class, "Node " + n.getNodeName()
					+ " didn't have subelement");
		}
		return elem;
	}

	public List<MetaData> getMetaDataFromChildNode(Node nd) {

		List<MetaData> list = null;
		list = new ArrayList<RNGMetadataAPI.MetaData>();

		if (nd.getNodeType() == Node.TEXT_NODE) {
			Util.fatal(RNGMetadataAPI.class, "text node not e");
			return list; // todo: check
		}
		if (nd.getNodeType() == Node.COMMENT_NODE) {
			// Util.log(RNGMetadataAPI.class,
			// "comment node: "+nd.getNodeValue());
			return list; // todo: check
		}

		if (nd.getNodeName().equals("ref")) {
			list = expandGrammaDefinition(nd);

		} else if (nd.getNodeName().equals("attribute")) {
			MetaData prop = createMetaData(nd);
			prop.min = 1;
			prop.max = 1;
			list.add(prop);

		} else if (nd.getNodeName().equals("element")) {
			MetaData prop = createMetaData(nd);
			list.add(prop);

		} else if (nd.getNodeName().equals("text")) {
			MetaData prop = createMetaData(nd); //todo implement text node
			list.add(prop);

		} else if (nd.getNodeName().equals("data")) {
			Util.fatal(RNGMetadataAPI.class,"Data not implemented. ") ;			
			
		} else if (nd.getNodeName().equals("choice")) {
			Util.log(RNGMetadataAPI.class,"Choice group not implemented. ") ;
			
		} else if (nd.getNodeName().endsWith(":documentation")) {
			// todo: implement annotation
			Util.fatal(RNGMetadataAPI.class,"documentation should be handled in a calling routine");

		} else if (nd.getNodeName().equals("optional")) {
			MetaData prop = createMetaData(getTheOnlyChildElementNode(nd));
			prop.min = 0;
			prop.max = 1;
			list.add(prop);

		} else if (nd.getNodeName().equals("zeroOrMore")) {

			MetaData prop = createMetaData(getTheOnlyChildElementNode(nd));
			prop.min = 0;
			prop.max = 1;
			list.add(prop);

		} else if (nd.getNodeName().equals("oneOrMore")) {
			MetaData prop = createMetaData(getTheOnlyChildElementNode(nd));
			prop.min = 1;
			prop.max = MetaData.UNDEFINED;
			list.add(prop);

		} else {

			Util.fatal(RNGMetadataAPI.class, "Unknown node " + nd.getNodeName());
		}

		return list;
	}

	public MetaData createMetaData(Node node) {
		MetaData meta = null;

		if (node.getNodeType() != Node.ELEMENT_NODE)
			Util.fatal(
					RNGMetadataAPI.class,
					"Root node has wrong type. Node name = "
							+ node.getNodeName());

		// System.out.println( node.getNodeName()+" "+node.getNodeValue());
		if (node.getNodeName().equals("attribute")) {
			meta = createAttributeNode(node);

		} else if (node.getNodeName().equals("choice")) {
			// Util.log(RNGMetadataAPI.class,
			// "Choice group os not implemented");
			meta = new MetaData(); // todo: check text node
			meta.name = "-CHOICE GROUP-";

		} else if (node.getNodeName().equals("text")) {
			
			meta = new MetaData(); // todo: check text node

		} else if (node.getNodeName().equals("ref")) {
			List<MetaData> data = expandGrammaDefinition(node);
			if (data.size() != 1) {
				Util.fatal(RNGMetadataAPI.class, "One element expected... got "
						+ data.size());
			}
			meta = data.get(0);

		} else if (node.getNodeName().equals("element")) {

			meta = createElementNode(node);

		} else {
			Util.fatal(RNGMetadataAPI.class,
					"Root element should be either attribute or element. Node is: "
							+ node.getNodeName() + " " + node.getNodeType());
		}

		return meta;
	}

	/** get the main element and  its properties (attributes and child elements/) **/
	public MetaData createMetaDataDeep(Node node) {

		MetaData meta = createMetaData(node);
		NodeList nds = node.getChildNodes();
		
		//System.err.println(meta.name);
		//System.err.flush();
		for (int i = 0; i < nds.getLength(); i++) {
			Node nd = nds.item(i);

			if (nd.getNodeType() == Node.ELEMENT_NODE) {

				//todo: fix the recursion
				if (nd.getNodeName().equals("data")) {
					String type = nd.getAttributes().getNamedItem("type").getNodeValue();
					if ( type == null) Util.fatal(RNGMetadataAPI.class, "type  is null");
					if ( type.length() == 0 ) Util.fatal(RNGMetadataAPI.class, "type is missing");
					meta.dataType = type;

				} else if ( nd.getNodeName().endsWith(":documentation")) {
					if ( nd.hasChildNodes() ) {
						Node txtNode  = getTheOnlyChildTextNode(nd);
						meta.documentation = meta.documentation + txtNode.getNodeValue();						
					}

				} else if ( nd.getNodeName().endsWith("empty")) {

						
				} else {

					List<MetaData> data = getMetaDataFromChildNode(nd);
					for (MetaData metaData : data) {
						meta.addProperty(metaData);
					}

				}
			}

		}

		return meta;
	}


	/**
	 * Expand named gramma pattern
	 * @param   The reference node (<ref name="nameOfPattern" />)
	 * @return  List of gramma elements as MetaData
	 */
	private List<MetaData> expandGrammaDefinition(Node nd) {
		if (!nd.getNodeName().equals("ref"))
			Util.fatal(RNGMetadataAPI.class,
					"Wrong XML element. Got '" + nd.getNodeName()
							+ "' but 'ref' was expected");
		Node name = nd.getAttributes().getNamedItem("name");
		if (name == null)
			Util.fatal(RNGMetadataAPI.class,
					"Wrong definition element. Name attribute is missing ");
		Node node = findXMLNode("grammar/define[@name='" + name.getNodeValue()
				+ "']");

		if (node == null)
			Util.fatal(RNGMetadataAPI.class,
					"Definition of " + name.getNodeValue() + " not found");

		List<MetaData> list = new ArrayList<RNGMetadataAPI.MetaData>();

		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);

			if (child.getNodeType() == Node.TEXT_NODE)
				continue;

			if (child.getNodeName().endsWith(":documentation")) { 
				
				//todo: implement annotation for named gramma patterns
				//Util.fatal(RNGMetadataAPI.class,"documentation for schema pattern should be handled in a calling routine");
				
			} else if (child.getNodeName().equals("ref")) {

				List<MetaData> data = expandGrammaDefinition(child);
				list.addAll(data);

			} else if (child.getNodeName().equals("data")) {
				//todo: fix recursion
				
			} else {
				
				List<MetaData> data = getMetaDataFromChildNode(child);
				list.addAll(data);
			}
		}
		return list;
	}

	public void printMetaData(MetaData data) {
		printMetaData("", data);
	}

	public void printMetaData(String indent, MetaData data) {
		System.out.println(indent + " " + data.toString());
		List<MetaData> props = data.properties;
		for (MetaData metaData : props) {
			if ( metaData.nodeType == MetaData.XML_ATTRIBUTE) printMetaData(indent + " ", metaData);
		}
		for (MetaData metaData : props) {
			if ( metaData.nodeType == MetaData.XML_ELEMENT ) printMetaData(indent + " ", metaData);
		}
	}

	public static void main(String[] args) throws Exception {

		RNGMetadataAPI app = createInstance("lsdb.rng");

		MetaData data = app.parseAndCreateMetadata("grammar/define/element[@name='panel']");
		app.printMetaData(data);
		NodeList nodes = app.findAllXMLNodes("grammar/define/element");
		for ( int i = 0; i < nodes.getLength() ; i++ ) {
			System.out.println("  ");
			app.printMetaData( app.createMetaDataDeep( nodes.item(i)));
			System.out.println("  ");
			System.out.println("  ");
		}
	}

}
