package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"protocol_id","db_xref","comment"})
public class VariantDetection {
	//xml-element used for code generation: //lsdb/individual/variant/variant_detection

	public VariantDetection(  ) {
	}
 
	// ===========-- technique --===========
	@org.simpleframework.xml.Attribute(required=false,name="technique")
	private String _attr_technique ;
	public void setTechniqueAttr( String attr_technique) { 
		this._attr_technique = attr_technique ;
	}
	public String getTechniqueAttr() { 
		return this._attr_technique;
	}
 
	// ===========-- template --===========
	@org.simpleframework.xml.Attribute(required=false,name="template")
	private String _attr_template ;
	public void setTemplateAttr( String attr_template) { 
		this._attr_template = attr_template ;
	}
	public String getTemplateAttr() { 
		return this._attr_template;
	}
 
	// ===========-- protocol_id --===========
	@org.simpleframework.xml.Element(required=false,name="protocol_id") 
	private ProtocolId _protocolId ;
	public void setProtocolId( ProtocolId protocolId) { 
		this._protocolId = protocolId ;
	}
	public ProtocolId getProtocolId() {
		return this._protocolId;
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
