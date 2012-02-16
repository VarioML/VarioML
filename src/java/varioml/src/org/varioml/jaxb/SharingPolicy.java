package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="sharing_policy")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_embargoEndDate","_usePermission","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_type","_embargoEndDate","_usePermission","_dbXref","_comment"})


public class SharingPolicy /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/sharing_policy

	public SharingPolicy(  ) {
	}
 
	// ===========-- type --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="type")
	private String _attr_type ;
@Deprecated
	public void setTypeAttr( String attr_type) { 
		this._attr_type = attr_type ;
	}
@Deprecated
	public String getTypeAttr() { 
		return this._attr_type;
	}
	public void setType( String attr_type) { 
		this._attr_type = attr_type ;
	}
	public String getType() { 
		return this._attr_type;
	}
 
	// ===========-- embargo_end_date --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="embargo_end_date",namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.JAXBVarioDate _embargoEndDate ;
	public void setEmbargoEndDate( org.varioml.util.JAXBVarioDate embargoEndDate) { 
		this._embargoEndDate = embargoEndDate ;
	}
	public org.varioml.util.JAXBVarioDate getEmbargoEndDate() {
		return this._embargoEndDate;
	}
 
	// ===========-- use_permission --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="use_permission",namespace="http://varioml.org/xml/1.0")
	private UsePermission _usePermission ;
	public void setUsePermission( UsePermission usePermission) { 
		this._usePermission = usePermission ;
	}
	public UsePermission getUsePermission() {
		return this._usePermission;
	}
 
	// ===========-- db_xref --===========
   @org.codehaus.jackson.annotate.JsonProperty("db_xrefs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="db_xref",namespace="http://varioml.org/xml/1.0")
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
   @org.codehaus.jackson.annotate.JsonProperty("comments")
   @javax.xml.bind.annotation.XmlElement(required=false,name="comment",namespace="http://varioml.org/xml/1.0")
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
