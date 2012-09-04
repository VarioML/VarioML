package test;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.varioml.basictypes.*;

@Order(elements{"Name","Url","Contact","Acknowledgement","DbXref","Comment"})
public class Source {
 
	// ===========-- date --===========
	@Attribute(required=false) //date
	private Date date ;
	public void setDate( Date date) { 
		this.date=date ;
	}
	public Date getDate()  
		return this.date;
	}
 
	// ===========-- id --===========
	@Attribute(required=false) //id
	private String id ;
	public void setId( String id) { 
		this.id=id ;
	}
	public String getId()  
		return this.id;
	}
 
	// ===========-- uri --===========
	@Attribute(required=false) //uri
	private String uri ;
	public void setUri( String uri) { 
		this.uri=uri ;
	}
	public String getUri()  
		return this.uri;
	}
 
	// ===========-- version --===========
	@Attribute(required=false) //version
	private Version version ;
	public void setVersion( Version version) { 
		this.version=version ;
	}
	public Version getVersion()  
		return this.version;
	}
 
	// ===========-- name --===========
	@Element(required=false) //name
	private String name ;
	public void setName( String name) { 
		this.name=name ;
	}
	public String getName()  
		return this.name;
	}
 
	// ===========-- url --===========
	@ElementList(required=false) //url
	private List<Url> url ;
	public void setUrlList( List<Url> url) { 
		this.url=url ;
	}
	public List<Url> getUrlList()  { 
		return this.url;
	}
	public void addUrl(Url item ) { 
		if ( this.url == null ) { 
			this.url = new ArrayList<Url>();
		}
		this.url.add( item);
	}
 
	// ===========-- contact --===========
	@ElementList(required=false) //contact
	private List<Contact> contact ;
	public void setContactList( List<Contact> contact) { 
		this.contact=contact ;
	}
	public List<Contact> getContactList()  { 
		return this.contact;
	}
	public void addContact(Contact item ) { 
		if ( this.contact == null ) { 
			this.contact = new ArrayList<Contact>();
		}
		this.contact.add( item);
	}
 
	// ===========-- acknowledgement --===========
	@ElementList(required=false) //acknowledgement
	private List<Acknowledgement> acknowledgement ;
	public void setAcknowledgementList( List<Acknowledgement> acknowledgement) { 
		this.acknowledgement=acknowledgement ;
	}
	public List<Acknowledgement> getAcknowledgementList()  { 
		return this.acknowledgement;
	}
	public void addAcknowledgement(Acknowledgement item ) { 
		if ( this.acknowledgement == null ) { 
			this.acknowledgement = new ArrayList<Acknowledgement>();
		}
		this.acknowledgement.add( item);
	}
 
	// ===========-- db_xref --===========
	@ElementList(required=false) //db_xref
	private List<DbXref> dbXref ;
	public void setDbXrefList( List<DbXref> dbXref) { 
		this.dbXref=dbXref ;
	}
	public List<DbXref> getDbXrefList()  { 
		return this.dbXref;
	}
	public void addDbXref(DbXref item ) { 
		if ( this.dbXref == null ) { 
			this.dbXref = new ArrayList<DbXref>();
		}
		this.dbXref.add( item);
	}
 
	// ===========-- comment --===========
	@ElementList(required=false) //comment
	private List<Comment> comment ;
	public void setCommentList( List<Comment> comment) { 
		this.comment=comment ;
	}
	public List<Comment> getCommentList()  { 
		return this.comment;
	}
	public void addComment(Comment item ) { 
		if ( this.comment == null ) { 
			this.comment = new ArrayList<Comment>();
		}
		this.comment.add( item);
	}
