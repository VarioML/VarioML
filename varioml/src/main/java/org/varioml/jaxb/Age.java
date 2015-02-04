package org.varioml.jaxb;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="age")
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_coded","__float"})


public class Age /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/age

	public Age(  ) {
	}
 
	// ===========-- coded --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="coded")
	private Boolean _attr_coded ;
	public void setCoded( Boolean attr_coded) { 
		this._attr_coded = attr_coded ;
	}
	public Boolean isCoded() { 
		return this._attr_coded;
	}
	// =========-- TEXT NODE --=========
	@com.fasterxml.jackson.annotation.JsonProperty("float")
   @javax.xml.bind.annotation.XmlValue
	private Float __float ;
	public Age( Float v ) {
		this.__float= v ;
	}
	public void setFloat( Float v ) { 
		this.__float= v ;
	}
	public Float getFloat() {
		return this.__float;
	}
}
