package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"ref_seq","chr","start","end"})
public class Location {
	//xml-element used for code generation: //lsdb/individual/variant/location

	public Location(  ) {
	}
 
	// ===========-- ref_seq --===========
	@org.simpleframework.xml.Element(required=false,name="ref_seq") 
	private RefSeq _refSeq ;
	public void setRefSeq( RefSeq refSeq) { 
		this._refSeq = refSeq ;
	}
	public RefSeq getRefSeq() {
		return this._refSeq;
	}
 
	// ===========-- chr --===========
	@org.simpleframework.xml.Element(required=false,name="chr") 
	private String _chr ;
	public void setChr( String chr) { 
		this._chr = chr ;
	}
	public String getChr() {
		return this._chr;
	}
 
	// ===========-- start --===========
	@org.simpleframework.xml.Element(required=false,name="start") 
	private Long _start ;
	public void setStart( Long start) { 
		this._start = start ;
	}
	public Long getStart() {
		return this._start;
	}
 
	// ===========-- end --===========
	@org.simpleframework.xml.Element(required=false,name="end") 
	private Long _end ;
	public void setEnd( Long end) { 
		this._end = end ;
	}
	public Long getEnd() {
		return this._end;
	}
}
