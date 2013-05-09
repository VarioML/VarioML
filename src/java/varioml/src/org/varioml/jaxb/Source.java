package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="source")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_name","_url","_contact","_acknowledgement","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_date","_attr_id","_attr_uri","_attr_version","_name","_url","_contact","_acknowledgement","_dbXref","_comment"})


public class Source /**/implements VmlAnnotatable /**/ {
	//xml-element used for code generation: //lsdb/source

	public Source(  ) {
	}
 
	// ===========-- date --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="date")
	private org.varioml.util.VMLDate _attr_date ;
	public void setDate( org.varioml.util.VMLDate attr_date) { 
		this._attr_date = attr_date ;
	}
	public org.varioml.util.VMLDate getDate() { 
		return this._attr_date;
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
 
	// ===========-- uri --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="uri")
	private String _attr_uri ;
	public void setUri( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUri() { 
		return this._attr_uri;
	}
 
	// ===========-- version --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="version")
	private String _attr_version ;
	public void setVersion( String attr_version) { 
		this._attr_version = attr_version ;
	}
	public String getVersion() { 
		return this._attr_version;
	}
 
	// ===========-- name --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="name",type=String.class,namespace="http://varioml.org/xml/1.0")
	private String _name ;
	public void setName( String name) { 
		this._name = name ;
	}
	public String getName() {
		return this._name;
	}
 
	// ===========-- url --===========
   @com.fasterxml.jackson.annotation.JsonProperty("urls")
   @javax.xml.bind.annotation.XmlElement(required=false,name="url",type=String.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("contacts")
   @javax.xml.bind.annotation.XmlElement(required=false,name="contact",type=Contact.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("acknowledgements")
   @javax.xml.bind.annotation.XmlElement(required=false,name="acknowledgement",type=Acknowledgement.class,namespace="http://varioml.org/xml/1.0")
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