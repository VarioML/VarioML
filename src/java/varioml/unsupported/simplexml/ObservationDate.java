package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"db_xref","comment"})
public class ObservationDate {
	//xml-element used for code generation: //lsdb/variant/observation_date

	public ObservationDate(  ) {
	}
 
	// ===========-- coded --===========
	@org.simpleframework.xml.Attribute(required=false,name="coded")
	private Boolean _attr_coded ;
	public void setCodedAttr( Boolean attr_coded) { 
		this._attr_coded = attr_coded ;
	}
	public Boolean getCodedAttr() { 
		return this._attr_coded;
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
