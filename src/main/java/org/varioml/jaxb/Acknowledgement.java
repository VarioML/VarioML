package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonAutoDetect( fieldVisibility =  JsonAutoDetect.Visibility.NONE, getterVisibility= JsonAutoDetect.Visibility.NONE,setterVisibility= JsonAutoDetect.Visibility.NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="acknowledgement")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_name","_grantNumber","_dbXref","_comment"})
@JsonPropertyOrder(value={  "_name","_grantNumber","_dbXref","_comment"})


public class Acknowledgement /**/implements VmlAnnotatable /**/ {
	//xml-element used for code generation: //lsdb/source/acknowledgement

	public Acknowledgement(  ) {
	}
 
	// ===========-- name --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="name",type=String.class,namespace="http://varioml.org/xml/1.0")
	private String _name ;
	public void setName( String name) { 
		this._name = name ;
	}
	public String getName() {
		return this._name;
	}
 
	// ===========-- grant_number --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="grant_number",type=GrantNumber.class,namespace="http://varioml.org/xml/1.0")
	private GrantNumber _grantNumber ;
	public void setGrantNumber( GrantNumber grantNumber) { 
		this._grantNumber = grantNumber ;
	}
	public GrantNumber getGrantNumber() {
		return this._grantNumber;
	}
 
	// ===========-- db_xref --===========
   @JsonProperty("db_xrefs")
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
   @JsonProperty("comments")
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