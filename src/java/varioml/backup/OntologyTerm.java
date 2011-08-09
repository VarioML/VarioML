package org.varioml.data;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;

public class OntologyTerm  extends AnnotatedElement {
	
	
	@Attribute(required=false)
	private String term ;

	@Attribute(required=false)
	private String source ;

	@Attribute(required=false)
	private String uri ;

	@Attribute(required=false)
	private String accession ;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getAccession() {
		return accession;
	}

	public void setAccession(String accession) {
		this.accession = accession;
	}

	

}
