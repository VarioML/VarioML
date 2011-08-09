package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={})
public class Name {
	//xml-element used for code generation: //lsdb/individual/variant/name

	public Name(  ) {
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
}
