package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={})
public class VariantName {
	//xml-element used for code generation: //lsdb/individual/variant/name

	public VariantName(  ) {
	}
 
	// ===========-- scheme --===========
	@org.simpleframework.xml.Attribute(required=false,name="scheme")
	private String _attr_scheme ;
	public void setSchemeAttr( String attr_scheme) { 
		this._attr_scheme = attr_scheme ;
	}
	public String getSchemeAttr() { 
		return this._attr_scheme;
	}
	// =========-- TEXT NODE --=========
	@org.simpleframework.xml.Text(required=false)
	private String __string ;
	public VariantName( String v ) {
		this.__string= v ;
	}
	public void setString( String v ) { 
		this.__string= v ;
	}
	public String getString() {
		return this.__string;
	}
}
