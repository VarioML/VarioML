package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"description","db_xref","comment"})
public class Gender {
	//xml-element used for code generation: //lsdb/individual/gender

	public Gender(  ) {
	}
 
	// ===========-- code --===========
	@org.simpleframework.xml.Attribute(required=false,name="code")
	private Integer _attr_code ;
	public void setCodeAttr( Integer attr_code) { 
		this._attr_code = attr_code ;
	}
	public Integer getCodeAttr() { 
		return this._attr_code;
	}
 
	// ===========-- description --===========
	@org.simpleframework.xml.Element(required=false,name="description") 
	private GenderDescription _description ;
	public void setDescription( GenderDescription description) { 
		this._description = description ;
	}
	public GenderDescription getDescription() {
		return this._description;
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
