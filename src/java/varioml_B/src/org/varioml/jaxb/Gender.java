package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="gender")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_description","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_code","_description","_dbXref","_comment"})


public class Gender /**/implements VmlAnnotatable /**/ {
	//xml-element used for code generation: //lsdb/individual/gender

	public Gender(  ) {
	}
 
	// ===========-- code --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="code")
	private Integer _attr_code ;
	public void setCode( Integer attr_code) { 
		this._attr_code = attr_code ;
	}
	public Integer getCode() { 
		return this._attr_code;
	}
 
	// ===========-- description --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="description",type=GenderDescription.class,namespace="http://varioml.org/xml/1.0")
	private GenderDescription _description ;
	public void setDescription( GenderDescription description) { 
		this._description = description ;
	}
	public GenderDescription getDescription() {
		return this._description;
	}
 
	// ===========-- db_xref --===========
   @org.codehaus.jackson.annotate.JsonProperty("db_xrefs")
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
   @org.codehaus.jackson.annotate.JsonProperty("comments")
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