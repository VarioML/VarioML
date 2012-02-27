package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"name","url","address","phone","fax","email","db_xref","comment"})
public class Contact {
	//xml-element used for code generation: //lsdb/source/contact

	public Contact(  ) {
	}
 
	// ===========-- role --===========
	@org.simpleframework.xml.Attribute(required=false,name="role")
	private String _attr_role ;
	public void setRoleAttr( String attr_role) { 
		this._attr_role = attr_role ;
	}
	public String getRoleAttr() { 
		return this._attr_role;
	}
 
	// ===========-- name --===========
	@org.simpleframework.xml.Element(required=false,name="name") 
	private String _name ;
	public void setName( String name) { 
		this._name = name ;
	}
	public String getName() {
		return this._name;
	}
 
	// ===========-- url --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="url") 
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
	@org.simpleframework.xml.Element(required=false,name="address") 
	private String _address ;
	public void setAddress( String address) { 
		this._address = address ;
	}
	public String getAddress() {
		return this._address;
	}
 
	// ===========-- phone --===========
	@org.simpleframework.xml.Element(required=false,name="phone") 
	private String _phone ;
	public void setPhone( String phone) { 
		this._phone = phone ;
	}
	public String getPhone() {
		return this._phone;
	}
 
	// ===========-- fax --===========
	@org.simpleframework.xml.Element(required=false,name="fax") 
	private String _fax ;
	public void setFax( String fax) { 
		this._fax = fax ;
	}
	public String getFax() {
		return this._fax;
	}
 
	// ===========-- email --===========
	@org.simpleframework.xml.Element(required=false,name="email") 
	private String _email ;
	public void setEmail( String email) { 
		this._email = email ;
	}
	public String getEmail() {
		return this._email;
	}
 
	// ===========-- db_xref --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="db_xref") 
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
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="comment") 
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
