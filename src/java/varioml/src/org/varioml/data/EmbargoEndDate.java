package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={})
public class EmbargoEndDate {
	//xml-element used for code generation: //lsdb/individual/sharing_policy/embargo_end_date

	public EmbargoEndDate(  ) {
	}
 
	// ===========-- is_undefined --===========
	@org.simpleframework.xml.Attribute(required=false,name="is_undefined")
	private boolean _attr_isUndefined ;
	public void setIsUndefinedAttr( boolean attr_isUndefined) { 
		this._attr_isUndefined = attr_isUndefined ;
	}
	public boolean getIsUndefinedAttr() { 
		return this._attr_isUndefined;
	}
	// =========-- TEXT NODE --=========
	@org.simpleframework.xml.Text(required=false)
	private org.varioml.util.VarioDate __variodate ;
	public EmbargoEndDate( org.varioml.util.VarioDate v ) {
		this.__variodate= v ;
	}
	public void setVarioDate( org.varioml.util.VarioDate v ) { 
		this.__variodate= v ;
	}
	public org.varioml.util.VarioDate getVarioDate() {
		return this.__variodate;
	}
}
