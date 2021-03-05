package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="variant_detection")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_protocolId","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_technique","_attr_template","_protocolId","_dbXref","_comment"})


public class VariantDetection /**/implements VmlAnnotatable /**/ {
	//xml-element used for code generation: //lsdb/individual/variant/variant_detection

	public VariantDetection(  ) {
	}
 
	// ===========-- technique --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="technique")
	private String _attr_technique ;
	public void setTechnique( String attr_technique) { 
		this._attr_technique = attr_technique ;
	}
	public String getTechnique() { 
		return this._attr_technique;
	}
 
	// ===========-- template --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="template")
	private String _attr_template ;
	public void setTemplate( String attr_template) { 
		this._attr_template = attr_template ;
	}
	public String getTemplate() { 
		return this._attr_template;
	}
 
	// ===========-- protocol_id --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="protocol_id",type=ProtocolId.class,namespace="http://varioml.org/xml/1.0")
	private ProtocolId _protocolId ;
	public void setProtocolId( ProtocolId protocolId) { 
		this._protocolId = protocolId ;
	}
	public ProtocolId getProtocolId() {
		return this._protocolId;
	}
 
	// ===========-- db_xref --===========
   @com.fasterxml.jackson.annotation.JsonProperty("db_xrefs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="db_xref",type=DbXref.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("comments")
   @javax.xml.bind.annotation.XmlElement(required=false,name="comment",type=Comment.class,namespace="http://varioml.org/xml/1.0")
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