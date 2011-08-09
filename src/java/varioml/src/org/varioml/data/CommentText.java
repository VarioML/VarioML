package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={})
public class CommentText {
	//xml-element used for code generation: //lsdb/comment/text

	public CommentText(  ) {
	}
 
	// ===========-- content_type --===========
	@org.simpleframework.xml.Attribute(required=false,name="content_type")
	private String _attr_contentType ;
	public void setContentTypeAttr( String attr_contentType) { 
		this._attr_contentType = attr_contentType ;
	}
	public String getContentTypeAttr() { 
		return this._attr_contentType;
	}
 
	// ===========-- encoding --===========
	@org.simpleframework.xml.Attribute(required=false,name="encoding")
	private String _attr_encoding ;
	public void setEncodingAttr( String attr_encoding) { 
		this._attr_encoding = attr_encoding ;
	}
	public String getEncodingAttr() { 
		return this._attr_encoding;
	}
 
	// ===========-- lang --===========
	@org.simpleframework.xml.Attribute(required=false,name="lang")
	private String _attr_lang ;
	public void setLangAttr( String attr_lang) { 
		this._attr_lang = attr_lang ;
	}
	public String getLangAttr() { 
		return this._attr_lang;
	}
	// =========-- TEXT NODE --=========
	@org.simpleframework.xml.Text(required=false)
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
