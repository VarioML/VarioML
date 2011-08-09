package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"name","url","contact","acknowledgement","db_xref","comment"})
public class Source {
	//xml-element used for code generation: //lsdb/source

	public Source(  ) {
	}
 
	// ===========-- date --===========
	@org.simpleframework.xml.Attribute(required=false,name="date")
	private org.varioml.util.VarioDate _attr_date ;
	public void setDateAttr( org.varioml.util.VarioDate attr_date) { 
		this._attr_date = attr_date ;
	}
	public org.varioml.util.VarioDate getDateAttr() { 
		return this._attr_date;
	}
 
	// ===========-- id --===========
	@org.simpleframework.xml.Attribute(required=false,name="id")
	private String _attr_id ;
	public void setIdAttr( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getIdAttr() { 
		return this._attr_id;
	}
 
	// ===========-- uri --===========
	@org.simpleframework.xml.Attribute(required=false,name="uri")
	private String _attr_uri ;
	public void setUriAttr( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUriAttr() { 
		return this._attr_uri;
	}
 
	// ===========-- version --===========
	@org.simpleframework.xml.Attribute(required=false,name="version")
	private String _attr_version ;
	public void setVersionAttr( String attr_version) { 
		this._attr_version = attr_version ;
	}
	public String getVersionAttr() { 
		return this._attr_version;
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
 
	// ===========-- contact --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="contact") 
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
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="acknowledgement") 
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
