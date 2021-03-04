package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="comment_text")
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_contentType","_attr_encoding","_attr_lang","__string"})


public class CommentText /**/ /**/ {
	//xml-element used for code generation: //lsdb/comment/text

	public CommentText(  ) {
	}
 
	// ===========-- content_type --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="content_type")
	private String _attr_contentType ;
	public void setContentType( String attr_contentType) { 
		this._attr_contentType = attr_contentType ;
	}
	public String getContentType() { 
		return this._attr_contentType;
	}
 
	// ===========-- encoding --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="encoding")
	private String _attr_encoding ;
	public void setEncoding( String attr_encoding) { 
		this._attr_encoding = attr_encoding ;
	}
	public String getEncoding() { 
		return this._attr_encoding;
	}
 
	// ===========-- lang --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="lang")
	private String _attr_lang ;
	public void setLang( String attr_lang) { 
		this._attr_lang = attr_lang ;
	}
	public String getLang() { 
		return this._attr_lang;
	}
	// =========-- TEXT NODE --=========
	@com.fasterxml.jackson.annotation.JsonProperty("string")
   @javax.xml.bind.annotation.XmlValue
	private String __string ;
	public CommentText( String v ) {
		this.__string= v ;
	}
	public void setString( String v ) { 
		this.__string= v ;
	}
	public String getString() {
		return this.__string;
	}
}
