package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="variant_name")
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_scheme","__string"})


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
	@org.codehaus.jackson.annotate.JsonProperty("string")
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
