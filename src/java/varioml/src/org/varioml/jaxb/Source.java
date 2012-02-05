package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="source")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_name","_url","_contact","_acknowledgement","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_date","_attr_id","_attr_uri","_attr_version","_name","_url","_contact","_acknowledgement","_dbXref","_comment"})


public class Source {
	//xml-element used for code generation: //lsdb/source

	public Source(  ) {
	}
 
	// ===========-- date --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="date")
	private org.varioml.util.JAXBVarioDate _attr_date ;
@Deprecated
	public void setDateAttr( org.varioml.util.JAXBVarioDate attr_date) { 
		this._attr_date = attr_date ;
	}
@Deprecated
	public org.varioml.util.JAXBVarioDate getDateAttr() { 
		return this._attr_date;
	}
	public void setDate( org.varioml.util.JAXBVarioDate attr_date) { 
		this._attr_date = attr_date ;
	}
	public org.varioml.util.JAXBVarioDate getDate() { 
		return this._attr_date;
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
 
	// ===========-- version --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="version")
	private String _attr_version ;
@Deprecated
	public void setVersionAttr( String attr_version) { 
		this._attr_version = attr_version ;
	}
@Deprecated
	public String getVersionAttr() { 
		return this._attr_version;
	}
	public void setVersion( String attr_version) { 
		this._attr_version = attr_version ;
	}
	public String getVersion() { 
		return this._attr_version;
	}
 
	// ===========-- name --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="name",namespace="http://varioml.org/xml/1.0")
	private String _name ;
	public void setName( String name) { 
		this._name = name ;
	}
	public String getName() {
		return this._name;
	}
 
	// ===========-- url --===========
   @org.codehaus.jackson.annotate.JsonProperty("urls")
   @javax.xml.bind.annotation.XmlElement(required=false,name="url",namespace="http://varioml.org/xml/1.0")
	private List<String> _url ;
	public void setUrlList( List<String> url) { 
		this._url = url ;
	}
	public List<String> getUrlList()  { 
		return this._url;
	}
	public void addUrl(String item ) { 
		if ( this._url == null ) { 
			this._url = new ArrayList<String>();
		}
		this._url.add( item);
	}
 
	// ===========-- contact --===========
   @org.codehaus.jackson.annotate.JsonProperty("contacts")
   @javax.xml.bind.annotation.XmlElement(required=false,name="contact",namespace="http://varioml.org/xml/1.0")
	private List<Contact> _contact ;
	public void setContactList( List<Contact> contact) { 
		this._contact = contact ;
	}
	public List<Contact> getContactList()  { 
		return this._contact;
	}
	public void addContact(Contact item ) { 
		if ( this._contact == null ) { 
			this._contact = new ArrayList<Contact>();
		}
		this._contact.add( item);
	}
 
	// ===========-- acknowledgement --===========
   @org.codehaus.jackson.annotate.JsonProperty("acknowledgements")
   @javax.xml.bind.annotation.XmlElement(required=false,name="acknowledgement",namespace="http://varioml.org/xml/1.0")
	private List<Acknowledgement> _acknowledgement ;
	public void setAcknowledgementList( List<Acknowledgement> acknowledgement) { 
		this._acknowledgement = acknowledgement ;
	}
	public List<Acknowledgement> getAcknowledgementList()  { 
		return this._acknowledgement;
	}
	public void addAcknowledgement(Acknowledgement item ) { 
		if ( this._acknowledgement == null ) { 
			this._acknowledgement = new ArrayList<Acknowledgement>();
		}
		this._acknowledgement.add( item);
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
