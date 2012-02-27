package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="contact")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_name","_url","_address","_phone","_fax","_email","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_role","_name","_url","_address","_phone","_fax","_email","_dbXref","_comment"})


public class Contact /**/implements VmlAnnotatable /**/ {
	//xml-element used for code generation: //lsdb/source/contact

	public Contact(  ) {
	}
 
	// ===========-- role --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="role")
	private String _attr_role ;
	public void setRole( String attr_role) { 
		this._attr_role = attr_role ;
	}
	public String getRole() { 
		return this._attr_role;
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
 
	// ===========-- address --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="address",namespace="http://varioml.org/xml/1.0")
	private String _address ;
	public void setAddress( String address) { 
		this._address = address ;
	}
	public String getAddress() {
		return this._address;
	}
 
	// ===========-- phone --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="phone",namespace="http://varioml.org/xml/1.0")
	private String _phone ;
	public void setPhone( String phone) { 
		this._phone = phone ;
	}
	public String getPhone() {
		return this._phone;
	}
 
	// ===========-- fax --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="fax",namespace="http://varioml.org/xml/1.0")
	private String _fax ;
	public void setFax( String fax) { 
		this._fax = fax ;
	}
	public String getFax() {
		return this._fax;
	}
 
	// ===========-- email --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="email",namespace="http://varioml.org/xml/1.0")
	private String _email ;
	public void setEmail( String email) { 
		this._email = email ;
	}
	public String getEmail() {
		return this._email;
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