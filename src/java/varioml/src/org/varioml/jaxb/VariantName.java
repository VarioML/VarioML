package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="variant_name")
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_scheme","__string"})


public class VariantName /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/variant/name

	public VariantName(  ) {
	}
 
	// ===========-- scheme --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="scheme")
	private String _attr_scheme ;
	public void setScheme( String attr_scheme) { 
		this._attr_scheme = attr_scheme ;
	}
	public String getScheme() { 
		return this._attr_scheme;
	}
	// =========-- TEXT NODE --=========
	@com.fasterxml.jackson.annotation.JsonProperty("string")
   @javax.xml.bind.annotation.XmlValue
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
