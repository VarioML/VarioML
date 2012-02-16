package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="variant_type")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_description","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_accession","_attr_source","_attr_term","_attr_uri","_description","_dbXref","_comment"})


public class VariantType /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/variant/variant_type

	public VariantType(  ) {
	}
 
	// ===========-- accession --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="accession")
	private String _attr_accession ;
@Deprecated
	public void setAccessionAttr( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
@Deprecated
	public String getAccessionAttr() { 
		return this._attr_accession;
	}
	public void setAccession( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
	public String getAccession() { 
		return this._attr_accession;
	}
 
	// ===========-- source --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="source")
	private String _attr_source ;
@Deprecated
	public void setSourceAttr( String attr_source) { 
		this._attr_source = attr_source ;
	}
@Deprecated
	public String getSourceAttr() { 
		return this._attr_source;
	}
	public void setSource( String attr_source) { 
		this._attr_source = attr_source ;
	}
	public String getSource() { 
		return this._attr_source;
	}
 
	// ===========-- term --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="term")
	private String _attr_term ;
@Deprecated
	public void setTermAttr( String attr_term) { 
		this._attr_term = attr_term ;
	}
@Deprecated
	public String getTermAttr() { 
		return this._attr_term;
	}
	public void setTerm( String attr_term) { 
		this._attr_term = attr_term ;
	}
	public String getTerm() { 
		return this._attr_term;
	}
 
	// ===========-- uri --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="uri")
	private String _attr_uri ;
@Deprecated
	public void setUriAttr( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
@Deprecated
	public String getUriAttr() { 
		return this._attr_uri;
	}
	public void setUri( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUri() { 
		return this._attr_uri;
	}
 
	// ===========-- description --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="description",namespace="http://varioml.org/xml/1.0")
	private String _description ;
	public void setDescription( String description) { 
		this._description = description ;
	}
	public String getDescription() {
		return this._description;
	}
 
	// ===========-- db_xref --===========
   @org.codehaus.jackson.annotate.JsonProperty("db_xrefs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="db_xref",namespace="http://varioml.org/xml/1.0")
	private List<DbXref> _dbXref ;
	public void setDbXrefList( List<DbXref> dbXref) { 
		this._dbXref = dbXref ;
	}
	public List<DbXref> getDbXrefList()  { 
		return this._dbXref;
	}
	public void addDbXref(DbXref item ) { 
		if ( this._dbXref == null ) { 
			this._dbXref = new ArrayList<DbXref>();
		}
		this._dbXref.add( item);
	}
 
	// ===========-- comment --===========
   @org.codehaus.jackson.annotate.JsonProperty("comments")
   @javax.xml.bind.annotation.XmlElement(required=false,name="comment",namespace="http://varioml.org/xml/1.0")
	private List<Comment> _comment ;
	public void setCommentList( List<Comment> comment) { 
		this._comment = comment ;
	}
	public List<Comment> getCommentList()  { 
		return this._comment;
	}
	public void addComment(Comment item ) { 
		if ( this._comment == null ) { 
			this._comment = new ArrayList<Comment>();
		}
		this._comment.add( item);
	}
}
