package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="embargo_end_date")
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_isUndefined",})


public class EmbargoEndDate /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/sharing_policy/embargo_end_date

	public EmbargoEndDate(  ) {
	}
 
	// ===========-- is_undefined --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="is_undefined")
	private Boolean _attr_isUndefined ;
@Deprecated
	public void setIsUndefinedAttr( Boolean attr_isUndefined) { 
		this._attr_isUndefined = attr_isUndefined ;
	}
@Deprecated
	public Boolean getIsUndefinedAttr() { 
		return this._attr_isUndefined;
	}
	public void setIsUndefined( Boolean attr_isUndefined) { 
		this._attr_isUndefined = attr_isUndefined ;
	}
	public Boolean getIsUndefined() { 
		return this._attr_isUndefined;
	}
	// =========-- TEXT NODE --=========
   @org.codehaus.jackson.annotate.JsonProperty("variodate")
	@javax.xml.bind.annotation.XmlValue
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
