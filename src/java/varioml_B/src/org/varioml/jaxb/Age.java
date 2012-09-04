package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="age")
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_coded","__float"})


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
	@org.codehaus.jackson.annotate.JsonProperty("float")
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
