package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"embargo_end_date","use_permission","db_xref","comment"})
public class SharingPolicy {
	//xml-element used for code generation: //lsdb/individual/sharing_policy

	public SharingPolicy(  ) {
	}
 
	// ===========-- type --===========
	@org.simpleframework.xml.Attribute(required=false,name="type")
	private String _attr_type ;
	public void setTypeAttr( String attr_type) { 
		this._attr_type = attr_type ;
	}
	public String getTypeAttr() { 
		return this._attr_type;
	}
 
	// ===========-- embargo_end_date --===========
	@org.simpleframework.xml.Element(required=false,name="embargo_end_date") 
	private org.varioml.util.VarioDate _embargoEndDate ;
	public void setEmbargoEndDate( org.varioml.util.VarioDate embargoEndDate) { 
		this._embargoEndDate = embargoEndDate ;
	}
	public org.varioml.util.VarioDate getEmbargoEndDate() {
		return this._embargoEndDate;
	}
 
	// ===========-- use_permission --===========
	@org.simpleframework.xml.Element(required=false,name="use_permission") 
	private UsePermission _usePermission ;
	public void setUsePermission( UsePermission usePermission) { 
		this._usePermission = usePermission ;
	}
	public UsePermission getUsePermission() {
		return this._usePermission;
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
