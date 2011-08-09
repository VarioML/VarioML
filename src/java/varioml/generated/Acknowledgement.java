package test;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.varioml.basictypes.*;

@Order(elements{"Name","GrantNumber","DbXref","Comment"})
public class Acknowledgement {
 
	// ===========-- name --===========
	@Element(required=false) //name
	private String name ;
	public void setName( String name) { 
		this.name=name ;
	}
	public String getName()  
		return this.name;
	}
 
	// ===========-- grant_number --===========
	@Element(required=false) //grant_number
	private GrantNumber grantNumber ;
	public void setGrantNumber( GrantNumber grantNumber) { 
		this.grantNumber=grantNumber ;
	}
	public GrantNumber getGrantNumber()  
		return this.grantNumber;
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
