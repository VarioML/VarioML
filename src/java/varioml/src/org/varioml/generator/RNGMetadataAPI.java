package org.varioml.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.varioml.generator.RNGMetadataAPI.MetaData.NodeType;
import org.varioml.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RNGMetadataAPI {

	final private List<Document> documents;
	final private XPath xpath;
	final private String[] files;

	private TreeMap<String, String> grammaPatternDocumentation = new TreeMap<String, String>();

	public static class MetaData {

		public static class NodeType {
			private String name;

			public NodeType(String name) {
				this.name = name;
			}

			public String toString() {
				return name;
			}
		}

		final public static int UNDEFINED = -9999;
		final public static NodeType XML_ELEMENT = new NodeType("ELEMENT");
		final public static NodeType XML_ATTRIBUTE = new NodeType("ATTRIBUTE");

		final public static NodeType XML_UNKNOWN = new NodeType("UNKNOWN_node");

		public String name;
		public int min = 0;
		public int max = 1;
		public boolean isRootNode = false;
		public boolean hasTextNode = false;
		public NodeType nodeType = XML_UNKNOWN;
		public List<String> choiceList = new ArrayList<String>();
		public List<String> choiceGroupList = new ArrayList<String>();
		public List<String> patternList = new ArrayList<String>();
		public String dataType = "string"; // fix: guess for a default
		public String documentation = "";
		public List<MetaData> properties = new ArrayList<RNGMetadataAPI.MetaData>();
		public boolean isEmpty = false; // text node todo: chekc implementation
										// of text nodex (non empty element)
		public boolean isChoiceGroup = false;
		public String pathName;

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
			if (property == null)
				Util.fatal(RNGMetadataAPI.class, "property is null");
			properties.add(property);
		}

		public void addChoice(String choice) {
			choiceList.add(choice);
		}

		public void addChoiceGroup(String choice) {
			choiceGroupList.add(choice);
		}

		public void addPattern(String choice) {
			patternList.add(choice);
		}

		public String toString() {
			String patterns = "";
			for (String type : patternList) {
				patterns = patterns + " " + type;
			}
			String choice = "";
			for (String c : choiceList) {
				choice = choice + " " + c;
			}
			return "Name: " + name + " Type: " + nodeType + " Multp: (" + min + "-" + max + ") Data type: " + dataType
					+ " Values: " + choice + " Has-text_node: " + hasTextNode + " Doc: " + documentation + " Gramma patterns: "
					+ patterns;
		}

		public void addAllProperty(List<MetaData> prop) {
			// TODO Auto-generated method stub
			properties.addAll(prop);
		}

	}

	private RNGMetadataAPI(List<Document> documents, XPath xpath, String files[]) {
		this.documents = documents;
		this.xpath = xpath;
		this.files = files;
	}

	public String checkType(String type) {
		return type;
	}

	public static RNGMetadataAPI createInstance(String file[]) {
		XPathFactory xfac = XPathFactory.newInstance();
		XPath xpath = xfac.newXPath();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<Document> docs = new ArrayList();
		// dbf.setNamespaceAware(true);
		for (String fileName : file) {
			//System.err.println(fileName);
			File _file = Util.findFile(fileName);
			if (!_file.exists()) {
				Util.fatal(RNGMetadataAPI.class, "file " + fileName + " do not exist");
			}
			try {

				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(_file);
				docs.add(doc);
				
			} catch (Exception ex) {
				Util.fatal(RNGMetadataAPI.class, ex);
			}

			
		}

		return new RNGMetadataAPI(docs, xpath, file);

	}

	public Node findXMLNode(String expression) {

		NodeList result = null;
		try {
			XPathExpression expr = xpath.compile(expression);
			for ( int ix= 0 ; ix < documents.size() && (result == null || result.getLength() == 0) ; ix++) {
				Document doc = documents.get( ix);
				result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			}
		} catch (XPathExpressionException e) {
			Util.fatal(RNGMetadataAPI.class, e);
		}

		if (result == null || result.getLength() == 0)
			Util.fatal(RNGMetadataAPI.class, " xml element " + expression+" not found");
		return result.item(0);
	}

	public List<Node> findAllXMLNodes(String expression) {

		List<Node> result = new ArrayList<Node>();
		try {
			XPathExpression expr = xpath.compile(expression);
			for( int ix = 0 ; ix < documents.size() ; ix ++) { 
				NodeList _r = (NodeList) expr.evaluate(documents.get(ix), XPathConstants.NODESET);
				for( int rx = 0 ;  rx < _r.getLength() ; rx++) {
					result.add( _r.item( rx));
				}
			}
		} catch (XPathExpressionException e) {
			Util.fatal(RNGMetadataAPI.class, e);
		}
		return result;
	}
	
	public List<Node> findAllXMLNodesOrDie(String expression) {

		List<Node> result = findAllXMLNodes(expression);
		if (result.size() == 0)
			Util.fatal(RNGMetadataAPI.class, "XML expression not found " + expression);
		return result;
	}

	
	public Node findDefinitionNode(Node nd) {

		if (!nd.getNodeName().equals("ref"))
			Util.fatal(RNGMetadataAPI.class, "Wrong XML element. Got '" + nd.getNodeName() + "' but 'ref' was expected");

		String defName = getAttrValue("name", nd);
		Node node = findXMLNode("grammar/define[@name='" + defName + "']");
		if (node == null)
			Util.fatal(RNGMetadataAPI.class, "Definition of " + defName + " not found");

		return node;
	}

	public List<Node> findAllReferencingNodes(Node nd) {

		if (!nd.getNodeName().equals("define"))
			Util.fatal(RNGMetadataAPI.class, "Wrong XML element. Got '" + nd.getNodeName() + "' but 'define' was expected");

		String defName = getAttrValue("name", nd);
		List<Node> nodes = findAllXMLNodesOrDie("//ref[@name='" + defName + "']");
		return nodes;
	}
	
	public NodeType findTypeOfParentNode( Node node) {
		//todo: clean the code
		if ( node.getParentNode().getNodeName().equals("element")) {
			return MetaData.XML_ELEMENT ;
		}
		if ( node.getParentNode().getNodeName().equals("attribute")) {
			return MetaData.XML_ATTRIBUTE ;
		}
		if  ( node.getParentNode().getNodeName().equals("define")){ 
			Node _n = findAllReferencingNodes( node.getParentNode()).get( 0);
			if ( _n.getParentNode().getNodeName().equals("element")) {
				return MetaData.XML_ELEMENT ;
			}
			if ( _n.getParentNode().getNodeName().equals("attribute")) {
				return MetaData.XML_ATTRIBUTE ;
			}
			return null;
		}
		return null; 
		
	}

	/**
	 * Get all child elements (non text/comment) with expanding also gramma
	 * patterns Note: text nodes should be empty (todo: add check)
	 * 
	 * @param node
	 *            node
	 * @return list of nodes
	 */

	public List<Node> getAllNonTextChildElements(final Node node) {
		List<Node> nodes = new ArrayList<Node>();
		final NodeList nodeList = node.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {

			final Node nod = nodeList.item(i);
			if (nod.getNodeType() != Node.TEXT_NODE && nod.getNodeType() != Node.COMMENT_NODE) {

				if (nod.getNodeName().equals("ref")) {
					// todo: we can have infinite loop
					List<Node> newNodes = getAllNonTextChildElements(findDefinitionNode(nod));
					nodes.add(nod); // note that we add reference node <ref
									// name='xxx'> also into the list
					nodes.addAll(newNodes);

				} else {

					// todo: add document handing here.. move gramma pattern
					// docs int a hash
					if (nod.getNodeName().endsWith(":documentation") && nod.getParentNode().getNodeName().equals("define")) {

						String name = getAttrValue("name", nod.getParentNode());
						String txt = getTextFromNode(nod);
						String doc = grammaPatternDocumentation.get(name);
						if (doc != null) {
							doc = doc + "  " + txt; // there can be more than
													// one documentation nodes
						} else {
							doc = txt;
						}
						grammaPatternDocumentation.put(name, doc);
						// we do not add these into node list
					} else {

						nodes.add(nod);

					}

				}
			}
		}
		return nodes;
	}

	public List<MetaData> createProperty(Node nd) {

		List<MetaData> propList = new ArrayList<RNGMetadataAPI.MetaData>();

		if (nd.getNodeName().equals("ref")) {
			// todo: check
			Node newNode = findDefinitionNode(nd);
			propList.add(createMetaDataObject(newNode, false));
			Util.log(RNGMetadataAPI.class, "New feature part of GROUP-thing. Node=" + nodeToString(nd));

		} else if (nd.getNodeName().equals("define")) {
			// todo: check
			Util.log(RNGMetadataAPI.class, "do not know how to create property from define... should not come here");

		} else if (nd.getNodeName().equals("attribute")) {

			MetaData prop = createMetaDataObject(nd, false);
			prop.min = 1;
			prop.max = 1;
			propList.add(prop);

		} else if (nd.getNodeName().equals("element")) {

			MetaData prop = createMetaDataObject(nd, false);
			prop.min = 1;
			prop.max = 1;
			propList.add(prop);
			// todo: check cardinality

			// } else if (nd.getNodeName().equals("data")) {
			// Util.fatal(RNGMetadataAPI2.class,"Data not implemented. ") ;
			//
		} else if (nd.getNodeName().equals("choice")) {

			MetaData prop = createMetaDataObject(nd, false);
			propList.add(prop);
			// Util.fatal(RNGMetadataAPI2.class,"Choice group not implemented. ")
			// ;

			// } else if (nd.getNodeName().endsWith(":documentation")) {
			//
			// // todo: implement annotation
			// Util.fatal(RNGMetadataAPI2.class,"documentation should be handled in a calling routine");

		} else if (nd.getNodeName().equals("optional")) {
			Node _node = getTheOnlyChildEACNode2(nd); // getTheOnlyChildNode(nd,
														// Node.ELEMENT_NODE);
			MetaData prop = createMetaDataObject(_node, false);
			prop.min = 0;
			prop.max = 1;
			propList.add(prop);

		} else if (nd.getNodeName().equals("zeroOrMore")) {

			Node _node = getTheOnlyChildEACNode2(nd); // getTheOnlyChildNode(nd,
														// Node.ELEMENT_NODE);
			MetaData prop = createMetaDataObject(_node, false);
			prop.min = 0;
			prop.max = MetaData.UNDEFINED;
			propList.add(prop);

		} else if (nd.getNodeName().equals("oneOrMore")) {

			Node _node = getTheOnlyChildEACNode2(nd); // getTheOnlyChildNode(nd,
														// Node.ELEMENT_NODE);
			MetaData prop = createMetaDataObject(_node, false);
			prop.min = 1;
			prop.max = MetaData.UNDEFINED;
			propList.add(prop);

		} else if (nd.getNodeName().equals("group")) {

			List<Node> kids = getAllNonTextChildElements(nd);
			for (Node kid : kids) {
				// todo: not tested

				List<MetaData> prop = createProperty(kid);
				for (MetaData md : prop) {
					propList.add(md);
				}

			}
		} else {

			Util.fatal(RNGMetadataAPI.class, "Unknown node " + nd.getNodeName());
		}

		return propList;
	}

	/**
	 * Create MetaData object instance
	 * 
	 * @param node
	 *            Node from which the MetaData object instance is created
	 * @param recurse
	 *            Recurse into children
	 * @return
	 */
	public MetaData createMetaDataObject(Node node, boolean recurse) {

		if (node.getNodeName().equals("ref")) {

			// node is a reference.. need to get actual node
			List<Node> newNodes = getAllNonTextChildElements(findDefinitionNode(node));
			if (newNodes.size() != 1) {
				String _str = "";
				for (Node tmp : newNodes) {
					_str = _str + " " + tmp.getNodeName(); // getAttrValue("name",
															// tmp);
				}
				Util.fatal(
						RNGMetadataAPI.class,
						"we should have got only one pattern element... for " + getAttrValue("name", node) + " got "
								+ newNodes.size() + " = " + _str);
			}
			return createMetaDataObject(newNodes.get(0), recurse);
		}

		MetaData.NodeType type = null;
		boolean isChoiceGroup = false;

		// todo: add choice group
		if (node.getNodeName().equals("attribute")) {

			type = MetaData.XML_ATTRIBUTE;

		} else if (node.getNodeName().equals("element")) {

			type = MetaData.XML_ELEMENT;

		} else if (node.getNodeName().equals("choice")) {

			type = MetaData.XML_ELEMENT;
			isChoiceGroup = true;
			// add this
			MetaData _meta = new MetaData("---- CHOICE -----"); // todo: fix
			_meta.nodeType = MetaData.XML_ELEMENT; //
			_meta.isChoiceGroup = true;
			List<Node> kids = getAllNonTextChildElements(node);

			for (Node k : kids) {
				//System.err.println(" ==== " + nodeToString(k));
				if (k.getNodeName().equals("element")) {
					List<MetaData> prop = createProperty(k);

					_meta.addAllProperty(prop);
				}

			}
			return _meta;
		} else {
			Util.fatal(RNGMetadataAPI.class, "cannot create node for " + node.getNodeName() + " node " + nodeToString(node)
					+ " parent=" + nodeToString(node.getParentNode()));
		}

		List<Node> kids = getAllNonTextChildElements(node);

		Node aName = node.getAttributes().getNamedItem("name");
		MetaData mdata;

		if (aName == null) {

			mdata = new MetaData("ANYNAME", type, 0, 1);// optional.. anonymous
														// choice

		} else {

			mdata = new MetaData(getAttrValue("name", node), type, 0, 1);
		}

		mdata.isChoiceGroup = isChoiceGroup;
		List<Node> propertyKids = assignDataDefinitionsAndFileterOutProperties(mdata, kids, node, type);

		if (recurse) {

			for (Node kid : propertyKids) {
				if (kid.getNodeType() != Node.COMMENT_NODE) {
					List<MetaData> prop = createProperty(kid);
					mdata.addAllProperty(prop);
				}
			}

		}
		return mdata;
	}

	/*
	 * 
	 */

	public String getAttrValue(String name, Node node) {
		
		Node aName = node.getAttributes().getNamedItem(name);
		if (aName == null || aName.getNodeValue() == null || aName.getNodeValue().length() == 0)
			Util.fatal(RNGMetadataAPI.class, "element " + node.getNodeName() + " do not have attribute " + name + " node="
					+ nodeToString(node) + " parentNode=" + nodeToString(node.getParentNode()));
		return aName.getNodeValue();
	}

	/*
	 * 
	 */
	public Node getTheOnlyChildTextNode(Node n) {
		return getTheOnlyChildNode(n, Node.TEXT_NODE);
	}

	public String nodeToString(Node node) {
		String str = "";
		NamedNodeMap attrs = node.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			String name = attrs.item(i).getNodeName();
			String value = attrs.item(i).getNodeValue();
			str = str + name + "='" + value + "'";
		}
		return "<" + node.getNodeName() + " " + str + "/>";
	}

	public Node getTheOnlyChildNode(Node n, short nodeType) {
		// todo: should expand references as well
		NodeList l = n.getChildNodes();
		Node elem = null;
		String names = "";
		for (int i = 0; i < l.getLength(); i++) {
			if (l.item(i).getNodeType() == nodeType) {
				names = names + " " + l.item(i).getNodeName();
				if (elem != null) {
					Util.fatal(RNGMetadataAPI.class, "Node " + n.getNodeName() + " should have only one sub element. Got also "
							+ names);
				}
				elem = l.item(i);

			}
		}
		if (elem == null) {
			Util.fatal(RNGMetadataAPI.class, "Node " + n.getNodeName() + " didn't have subelement");
		}
		return elem;
	}

	public String getTextFromNode(Node node) {
		// Node txtNode = getTheOnlyChildTextNode(node);
		NodeList l = node.getChildNodes();
		String text = "";
		for (int i = 0; i < l.getLength(); i++) {
			if (l.item(i).getNodeType() == Node.TEXT_NODE) {
				text = text + l.item(i).getNodeValue().trim();
			}
		}

		return text;
	}

	public Node getTheOnlyChildEACNode2(Node n) {
		List<Node> elems = getAllNonTextChildElements(n);
		List<Node> reduced = new ArrayList<Node>();
		String _tmp = "";
		for (Node node : elems) {
			_tmp = _tmp + " " + node.getNodeName();
			if (node.getNodeName().equals("attribute") || node.getNodeName().equals("element")
					|| node.getNodeName().equals("choice")) {
				reduced.add(node);
			}
		}
		if (reduced.size() != 1) {
			Util.fatal(RNGMetadataAPI.class,
					"Node " + n.getNodeName() + " count is not what was expected. count=" + reduced.size() + " all elems=" + _tmp);
		}
		return reduced.get(0);
	}

	public boolean hasNamedChild(String name, Node node) {
		List<Node> nods = getNamedChildNodes(name, node);
		return nods.size() > 0 && nods.get(0).getNodeName().equals(name);
	}

	public List<Node> getNamedChildNodes(String name, Node node) {
		List<Node> nodes = new ArrayList<Node>();
		NodeList nds = node.getChildNodes();
		if (nds.getLength() == 0) {
			Util.fatal(RNGMetadataAPI.class, "child nodes expected");
		}
		for (int i = 0; i < nds.getLength(); i++) {
			if (nds.item(i).getNodeName().equals(name)) {
				nodes.add(nds.item(i));
			}
		}
		return nodes;
	}

	/**
	 * Assign data definitions and filter out properties (elements and
	 * attributes)
	 * 
	 * @param kids
	 *            Property nodes
	 * @param node
	 *            Node from which MetaData instance is created
	 * @param type
	 *            Type of MetaData node (ie. attribute or element)
	 * @return
	 */
	private List<Node> assignDataDefinitionsAndFileterOutProperties(MetaData data, List<Node> kids, Node node,
			MetaData.NodeType type) {

		List<Node> propList = new ArrayList<Node>();

		for (Node nd : kids) {

			if (nd.getNodeType() == Node.ELEMENT_NODE) {
				// System.err.println(nd.getNodeName());

				if (nd.getNodeName().endsWith(":documentation")) {

					if (nd.hasChildNodes()) {
						Node txtNode = getTheOnlyChildTextNode(nd);
						data.documentation = data.documentation + txtNode.getNodeValue();
					}

				} else if (nd.getNodeName().equals("ref")) {

					String name = getAttrValue("name", nd);
					data.addPattern(name);

				} else if (nd.getNodeName().equals("data")) {

					String datType = getAttrValue("type", nd);
					data.dataType = datType;
					if ( findTypeOfParentNode(nd)== MetaData.XML_ELEMENT) { 
						System.err.println(">> TEST "+nodeToString( nd));
						data.hasTextNode = true; //todo: check this and change name
					}

				} else if (nd.getNodeName().equals("text")) {
					data.hasTextNode = true;

				} else if (nd.getNodeName().equals("value")) {

					Node txt = getTheOnlyChildTextNode(nd);
					if (txt == null)
						Util.fatal(RNGMetadataAPI.class, "value do not have text node");
					String value = txt.getNodeValue();
					if (value == null || value.length() == 0)
						Util.fatal(RNGMetadataAPI.class, "value is empty missing");
					// data.value = value; // todo: check
					data.addChoice(value); //

				} else if (nd.getNodeName().equals("choice") && hasNamedChild("value", nd)) {

					List<Node> nodes = getAllNonTextChildElements(nd);
					if (nodes.size() == 0) {
						Util.fatal(RNGMetadataAPI.class, "choice list is empty ");
					}

					for (Node kid : nodes) {

						if (kid.getNodeName().equals("value")) {
							Node txt = getTheOnlyChildTextNode(kid);
							data.addChoice(txt.getNodeValue());
						} else {
							// we may get documentation
							Util.fatal(RNGMetadataAPI.class, "unknown child element. Name =" + kid.getNodeName());
						}

					}

				} else if (nd.getNodeName().equals("empty")) {

					data.isEmpty = true;
					data.hasTextNode = false; // todo: check this

				} else {

					// other should be properties (ie. attributes and elements)
					propList.add(nd);

				}

			}

		}
		return propList;
	}

	public MetaData parseAndCreateMetadata(String xpath) throws Exception {

		Node node = findXMLNode(xpath);
		return createMetaDataObject(node, true);

	}

	public void printMetaData(MetaData data) {
		printMetaData("", data);
	}

	public void printMetaData(String indent, MetaData data) {
		System.out.println(indent + " " + data.toString());
		List<MetaData> props = data.properties;
		for (MetaData metaData : props) {
			if (metaData.nodeType == MetaData.XML_ATTRIBUTE)
				printMetaData(indent + " ", metaData);
		}
		for (MetaData metaData : props) {
			if (metaData.nodeType == MetaData.XML_ELEMENT)
				printMetaData(indent + " ", metaData);
		}

	}

	public void printDocumentationGrammaPatterns() {

		Set<String> keys = grammaPatternDocumentation.keySet();
		for (String k : keys) {
			String doc = grammaPatternDocumentation.get(k);
			System.out.println("" + k + " " + doc);
		}

	}

	public static String listToString(List<String> lst) {
		String s = "";
		if (lst.size() > 0) {
			s = lst.get(0);
			for (int i = 1; i < lst.size(); i++) {
				s = s + " | " + lst.get(i);
			}
		}
		return s;
	}

	public static String cardinToString(int min, int max) {
		if (min == 0 && max == 1) {
			return "optional";
		} else if (min == 1 && max == 1) {
			return "mandatory";
		} else if (min == 0 && max == MetaData.UNDEFINED) {
			return "zero or many";
		} else if (min == 1 && max == MetaData.UNDEFINED) {
			return "one or many";
		} else {
			Util.fatal(RNGMetadataAPI.class, "unknown min/max" + min + "/" + max);
			return null;
		}
	}

	public static void main(String[] args) throws Exception {

		RNGMetadataAPI app = createInstance( new String[]{"lsdb.rng"});

		MetaData data = app.parseAndCreateMetadata("grammar/define/element[@name='frequency']");
		app.printMetaData(data);
		List<Node> nodes = app.findAllXMLNodesOrDie("grammar/define/element");
		for (int i = 0; i < nodes.size(); i++) {
			System.out.println("  ");
			app.printMetaData(app.createMetaDataObject(nodes.get(i), true));
			System.out.println("  ");
			System.out.println("  ");
		}

		app.printDocumentationGrammaPatterns();
	}

	public static String propertiesToString(MetaData d) {

		List<MetaData> lst = d.properties;
		String s = "";
		if (lst.size() > 0) {
			s = lst.get(0).name;
			for (int i = 1; i < lst.size(); i++) {
				s = s + "|" + lst.get(i).name;
			}
		}
		return s;

	}

}
