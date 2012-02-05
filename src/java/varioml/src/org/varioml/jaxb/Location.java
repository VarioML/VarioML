package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="location")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_refSeq","_chr","_start","_end"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_refSeq","_chr","_start","_end"})


public class Location {
	//xml-element used for code generation: //lsdb/individual/variant/location

	public Location(  ) {
	}
 
	// ===========-- ref_seq --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="ref_seq",namespace="http://varioml.org/xml/1.0")
	private RefSeq _refSeq ;
	public void setRefSeq( RefSeq refSeq) { 
		this._refSeq = refSeq ;
	}
	public RefSeq getRefSeq() {
		return this._refSeq;
	}
 
	// ===========-- chr --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="chr",namespace="http://varioml.org/xml/1.0")
	private String _chr ;
	public void setChr( String chr) { 
		this._chr = chr ;
	}
	public String getChr() {
		return this._chr;
	}
 
	// ===========-- start --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="start",namespace="http://varioml.org/xml/1.0")
	private Long _start ;
	public void setStart( Long start) { 
		this._start = start ;
	}
	public Long getStart() {
		return this._start;
	}
 
	// ===========-- end --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="end",namespace="http://varioml.org/xml/1.0")
	private Long _end ;
	public void setEnd( Long end) { 
		this._end = end ;
	}
	public Long getEnd() {
		return this._end;
	}
}
