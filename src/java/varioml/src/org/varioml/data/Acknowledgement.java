package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"name","grant_number","db_xref","comment"})
public class Acknowledgement {
	//xml-element used for code generation: //lsdb/source/acknowledgement

	public Acknowledgement(  ) {
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
 
	// ===========-- grant_number --===========
	@org.simpleframework.xml.Element(required=false,name="grant_number") 
	private GrantNumber _grantNumber ;
	public void setGrantNumber( GrantNumber grantNumber) { 
		this._grantNumber = grantNumber ;
	}
	public GrantNumber getGrantNumber() {
		return this._grantNumber;
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
