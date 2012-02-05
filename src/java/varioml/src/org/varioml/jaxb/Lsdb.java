package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="lsdb")
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(propOrder = {  "_created","_source","_individual","_variant","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_id","_attr_schemaVersion","_attr_submissionidType","_attr_uri","_attr_xmlns","_attr_xmlnsXsi","_created","_source","_individual","_variant","_dbXref","_comment"})


public class Lsdb {
	//xml-element used for code generation: //lsdb

	public Lsdb(  ) {
	}
 
	// ===========-- id --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="id")
	private String _attr_id ;
@Deprecated
	public void setIdAttr( String attr_id) { 
		this._attr_id = attr_id ;
	}
@Deprecated
	public String getIdAttr() { 
		return this._attr_id;
	}
	public void setId( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getId() { 
		return this._attr_id;
	}
 
	// ===========-- schema_version --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="schema_version")
	private Float _attr_schemaVersion ;
@Deprecated
	public void setSchemaVersionAttr( Float attr_schemaVersion) { 
		this._attr_schemaVersion = attr_schemaVersion ;
	}
@Deprecated
	public Float getSchemaVersionAttr() { 
		return this._attr_schemaVersion;
	}
	public void setSchemaVersion( Float attr_schemaVersion) { 
		this._attr_schemaVersion = attr_schemaVersion ;
	}
	public Float getSchemaVersion() { 
		return this._attr_schemaVersion;
	}
 
	// ===========-- submissionid_type --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="submissionid_type")
	private String _attr_submissionidType ;
@Deprecated
	public void setSubmissionidTypeAttr( String attr_submissionidType) { 
		this._attr_submissionidType = attr_submissionidType ;
	}
@Deprecated
	public String getSubmissionidTypeAttr() { 
		return this._attr_submissionidType;
	}
	public void setSubmissionidType( String attr_submissionidType) { 
		this._attr_submissionidType = attr_submissionidType ;
	}
	public String getSubmissionidType() { 
		return this._attr_submissionidType;
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
 
	// ===========-- xmlns --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xmlns")
	private String _attr_xmlns ;
@Deprecated
	public void setXmlnsAttr( String attr_xmlns) { 
		this._attr_xmlns = attr_xmlns ;
	}
@Deprecated
	public String getXmlnsAttr() { 
		return this._attr_xmlns;
	}
	public void setXmlns( String attr_xmlns) { 
		this._attr_xmlns = attr_xmlns ;
	}
	public String getXmlns() { 
		return this._attr_xmlns;
	}
 
	// ===========-- xmlns:xsi --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xmlns:xsi")
	private String _attr_xmlnsXsi ;
@Deprecated
	public void setXmlnsXsiAttr( String attr_xmlnsXsi) { 
		this._attr_xmlnsXsi = attr_xmlnsXsi ;
	}
@Deprecated
	public String getXmlnsXsiAttr() { 
		return this._attr_xmlnsXsi;
	}
	public void setXmlnsXsi( String attr_xmlnsXsi) { 
		this._attr_xmlnsXsi = attr_xmlnsXsi ;
	}
	public String getXmlnsXsi() { 
		return this._attr_xmlnsXsi;
	}
 
	// ===========-- created --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="created",namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.JAXBVarioDateTime _created ;
	public void setCreated( org.varioml.util.JAXBVarioDateTime created) { 
		this._created = created ;
	}
	public org.varioml.util.JAXBVarioDateTime getCreated() {
		return this._created;
	}
 
	// ===========-- source --===========
   @org.codehaus.jackson.annotate.JsonProperty("sources")
   @javax.xml.bind.annotation.XmlElement(required=false,name="source",namespace="http://varioml.org/xml/1.0")
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
 
	// ===========-- individual --===========
   @org.codehaus.jackson.annotate.JsonProperty("individuals")
   @javax.xml.bind.annotation.XmlElement(required=false,name="individual",namespace="http://varioml.org/xml/1.0")
	private List<Individual> _individual ;
	public void setIndividualList( List<Individual> individual) { 
		this._individual = individual ;
	}
	public List<Individual> getIndividualList()  { 
		return this._individual;
	}
	public void addIndividual(Individual item ) { 
		if ( this._individual == null ) { 
			this._individual = new ArrayList<Individual>();
		}
		this._individual.add( item);
	}
 
	// ===========-- variant --===========
   @org.codehaus.jackson.annotate.JsonProperty("variants")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant",namespace="http://varioml.org/xml/1.0")
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
