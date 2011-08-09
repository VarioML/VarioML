package org.varioml.data;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

@Element(name="db_xref")
public class DbXref {

	@Attribute(required=true)
	private String accession ;
	@Attribute(required=false)
	private String source ;
	@Attribute(required=false)
	private String uri;
	@Attribute(required=false)
	private String name;
	
	@ElementList(inline=true,required=false,name="db_xref")
	private List<DbXref> dbXref;
	
	@ElementList(inline=true,required=false)
	private List<Comment> comment;

	public String getAccession() {
		return accession;
	}

	public void setAccession(String accession) {
		this.accession = accession;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DbXref> getDbXref() {
		return dbXref;
	}

	public void setDbXref(List<DbXref> dbXref) {
		this.dbXref = dbXref;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	
}
