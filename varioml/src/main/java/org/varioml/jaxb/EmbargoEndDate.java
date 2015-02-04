package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="embargo_end_date")
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_isUndefined","__date"})


public class EmbargoEndDate /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/sharing_policy/embargo_end_date

	public EmbargoEndDate(  ) {
	}
 
	// ===========-- is_undefined --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="is_undefined")
	private Boolean _attr_isUndefined ;
	public void setIsUndefined( Boolean attr_isUndefined) { 
		this._attr_isUndefined = attr_isUndefined ;
	}
	public Boolean isUndefined() { 
		return this._attr_isUndefined;
	}
	// =========-- TEXT NODE --=========
	@com.fasterxml.jackson.annotation.JsonProperty("date")
   @javax.xml.bind.annotation.XmlValue
	private org.varioml.util.VMLDate __date ;
	public EmbargoEndDate( org.varioml.util.VMLDate v ) {
		this.__date= v ;
	}
	public void setDate( org.varioml.util.VMLDate v ) { 
		this.__date= v ;
	}
	public org.varioml.util.VMLDate getDate() {
		return this.__date;
	}
}
