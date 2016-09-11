package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="cafe_variome")
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(propOrder = {  "_created","_source","_variant","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_id","_attr_schemaVersion","_attr_submissionidType","_attr_uri","_attr_xmlns","_attr_xmlnsXsi","_attr_xsiSchemaLocation","_created","_source","_variant","_dbXref","_comment"})


public class CafeVariome /**/implements VmlAnnotatable /**/ {
	//xml-element used for code generation: //cafe_variome

	public CafeVariome(  ) {
	}
 
	// ===========-- id --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="id")
	private String _attr_id ;
	public void setId( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getId() { 
		return this._attr_id;
	}
 
	// ===========-- schema_version --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="schema_version")
	private Float _attr_schemaVersion ;
	public void setSchemaVersion( Float attr_schemaVersion) { 
		this._attr_schemaVersion = attr_schemaVersion ;
	}
	public Float getSchemaVersion() { 
		return this._attr_schemaVersion;
	}
 
	// ===========-- submissionid_type --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="submissionid_type")
	private String _attr_submissionidType ;
	public void setSubmissionidType( String attr_submissionidType) { 
		this._attr_submissionidType = attr_submissionidType ;
	}
	public String getSubmissionidType() { 
		return this._attr_submissionidType;
	}
 
	// ===========-- uri --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="uri")
	private String _attr_uri ;
	public void setUri( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUri() { 
		return this._attr_uri;
	}
 
	// ===========-- xmlns --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xmlns")
	private String _attr_xmlns ;
	public void setXmlns( String attr_xmlns) { 
		this._attr_xmlns = attr_xmlns ;
	}
	public String getXmlns() { 
		return this._attr_xmlns;
	}
 
	// ===========-- xmlns:xsi --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xmlns:xsi")
	private String _attr_xmlnsXsi ;
	public void setXmlnsXsi( String attr_xmlnsXsi) { 
		this._attr_xmlnsXsi = attr_xmlnsXsi ;
	}
	public String getXmlnsXsi() { 
		return this._attr_xmlnsXsi;
	}
 
	// ===========-- xsi:schemaLocation --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xsi:schemaLocation")
	private String _attr_xsiSchemaLocation ;
	public void setXsiSchemaLocation( String attr_xsiSchemaLocation) { 
		this._attr_xsiSchemaLocation = attr_xsiSchemaLocation ;
	}
	public String getXsiSchemaLocation() { 
		return this._attr_xsiSchemaLocation;
	}
 
	// ===========-- created --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="created",type=org.varioml.util.VMLDateTime.class,namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.VMLDateTime _created ;
	public void setCreated( org.varioml.util.VMLDateTime created) { 
		this._created = created ;
	}
	public org.varioml.util.VMLDateTime getCreated() {
		return this._created;
	}
 
	// ===========-- source --===========
   @com.fasterxml.jackson.annotation.JsonProperty("sources")
   @javax.xml.bind.annotation.XmlElement(required=false,name="source",type=Source.class,namespace="http://varioml.org/xml/1.0")
	private List<Source> _source ;
	public void setSourceList( List<Source> source) { 
		this._source = source ;
	}
	public List<Source> getSourceList()  { 
		return this._source;
	}
	public void addSource(Source item ) { 
		if ( this._source == null ) { 
			this._source = new ArrayList<Source>();
		}
		this._source.add( item);
	}
 
	// ===========-- variant --===========
   @com.fasterxml.jackson.annotation.JsonProperty("variants")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant",type=Variant.class,namespace="http://varioml.org/xml/1.0")
	private List<Variant> _variant ;
	public void setVariantList( List<Variant> variant) { 
		this._variant = variant ;
	}
	public List<Variant> getVariantList()  { 
		return this._variant;
	}
	public void addVariant(Variant item ) { 
		if ( this._variant == null ) { 
			this._variant = new ArrayList<Variant>();
		}
		this._variant.add( item);
	}
 
	// ===========-- db_xref --===========
   @com.fasterxml.jackson.annotation.JsonProperty("db_xrefs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="db_xref",type=DbXref.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("comments")
   @javax.xml.bind.annotation.XmlElement(required=false,name="comment",type=Comment.class,namespace="http://varioml.org/xml/1.0")
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