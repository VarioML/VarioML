package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"db_xref","comment"})
public class GrantNumber {
	//xml-element used for code generation: //lsdb/source/acknowledgement/grant_number

	public GrantNumber(  ) {
	}
 
	// ===========-- accession --===========
	@org.simpleframework.xml.Attribute(required=false,name="accession")
	private String _attr_accession ;
	public void setAccessionAttr( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
	public String getAccessionAttr() { 
		return this._attr_accession;
	}
 
	// ===========-- name --===========
	@org.simpleframework.xml.Attribute(required=false,name="name")
	private String _attr_name ;
	public void setNameAttr( String attr_name) { 
		this._attr_name = attr_name ;
	}
	public String getNameAttr() { 
		return this._attr_name;
	}
 
	// ===========-- source --===========
	@org.simpleframework.xml.Attribute(required=false,name="source")
	private String _attr_source ;
	public void setSourceAttr( String attr_source) { 
		this._attr_source = attr_source ;
	}
	public String getSourceAttr() { 
		return this._attr_source;
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
