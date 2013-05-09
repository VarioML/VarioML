package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="location")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_refSeq","_chr","_start","_end"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_unit","_refSeq","_chr","_start","_end"})


public class Location /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/variant/location

	public Location(  ) {
	}
 
	// ===========-- unit --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="unit")
	private String _attr_unit ;
	public void setUnit( String attr_unit) { 
		this._attr_unit = attr_unit ;
	}
	public String getUnit() { 
		return this._attr_unit;
	}
 
	// ===========-- ref_seq --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="ref_seq",type=RefSeq.class,namespace="http://varioml.org/xml/1.0")
	private RefSeq _refSeq ;
	public void setRefSeq( RefSeq refSeq) { 
		this._refSeq = refSeq ;
	}
	public RefSeq getRefSeq() {
		return this._refSeq;
	}
 
	// ===========-- chr --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="chr",type=String.class,namespace="http://varioml.org/xml/1.0")
	private String _chr ;
	public void setChr( String chr) { 
		this._chr = chr ;
	}
	public String getChr() {
		return this._chr;
	}
 
	// ===========-- start --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="start",type=Long.class,namespace="http://varioml.org/xml/1.0")
	private Long _start ;
	public void setStart( Long start) { 
		this._start = start ;
	}
	public Long getStart() {
		return this._start;
	}
 
	// ===========-- end --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="end",type=Long.class,namespace="http://varioml.org/xml/1.0")
	private Long _end ;
	public void setEnd( Long end) { 
		this._end = end ;
	}
	public Long getEnd() {
		return this._end;
	}
}
