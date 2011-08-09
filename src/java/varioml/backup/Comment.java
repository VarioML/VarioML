package org.varioml.data;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;


public class Comment extends OntologyTerm {
	  	
	@Element(required=true)
	private String text;
	
	

}
