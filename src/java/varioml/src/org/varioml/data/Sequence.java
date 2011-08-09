package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"reference","variant","db_xref","comment"})
public class Sequence {
	//xml-element used for code generation: //lsdb/individual/variant/sequence

	public Sequence(  ) {
	}
 
	// ===========-- reference --===========
	@org.simpleframework.xml.Element(required=false,name="reference") 
	private String _reference ;
	public void setReference( String reference) { 
		this._reference = reference ;
	}
	public String getReference() {
		return this._reference;
	}
 
	// ===========-- variant --===========
	@org.simpleframework.xml.Element(required=false,name="variant") 
	private Variant _variant ;
	public void setVariant( Variant variant) { 
		this._variant = variant ;
	}
	public Variant getVariant() {
		return this._variant;
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
