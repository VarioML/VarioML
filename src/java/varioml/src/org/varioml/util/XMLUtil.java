package org.varioml.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {
	
	
	public static<T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  java.util.Collections.sort(list);
	  return list;
	}

	public static Node getNextNonTextSiblingNode( NodeList nodeList, int i)  {
		Node _node = nodeList.item( i + 1 );
		while ( _node != null && _node.getNodeType() == Node.TEXT_NODE ) {
			_node = nodeList.item( ++i);
		}
		return _node;
	}
	
	//todo: not used
	public String getInterfaceSignature(Node node, Map<String,String> typeMap) {

		//todo: fix cut and paste code
		
		ArrayList<String> list = new ArrayList<String>();
		NamedNodeMap att = node.getAttributes();
		for (int i = 0; i < att.getLength(); i++) {

			String elName = att.item(i).getNodeName();
			String  typeName = elName;
			String realType = typeName;
			if ( typeMap.get("@"+elName) != null ) {
				realType= typeMap.get("@"+elName);
			}
			list.add(elName+":"+realType) ;
		}

		NodeList elem = node.getChildNodes();		
		String lastElement = "";
		for (int i = 0; i < elem.getLength(); i++) {

			//hasTextNode = elem.item(i).getNodeType() == Node.TEXT_NODE || hasTextNode   ;
			
			if (elem.item(i).getNodeType() == Node.TEXT_NODE)
				continue;
			if (elem.item(i).getNodeName().equals(lastElement))
				continue;

			String elName = elem.item(i).getNodeName();
			lastElement = elName;

			String typeName = elName;
			//todo: bit ugly hack..
			String realType = typeName;
			if ( typeMap.get(elName) != null ) {
				realType= typeMap.get(elName);
			}

			if (getNextNonTextSiblingNode(elem,i) != null && getNextNonTextSiblingNode(elem,i).getNodeName().equals(elName)) {
			} else {
				realType = realType+":List";
			}
			list.add(elName+":"+realType);
		}

		return null;
	}


}
