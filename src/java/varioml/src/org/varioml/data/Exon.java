package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={})
public class Exon {
	//xml-element used for code generation: //lsdb/individual/variant/exon

	public Exon(  ) {
	}
 
	// ===========-- accession --===========
	@org.simpleframework.xml.Attribute(required=false,name="accession")
	private String _attr_accession ;
	public void setAccessionAttr( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
	public String getAccessionAttr() { 
		return this._attr_accession;
	}
 
	// ===========-- source --===========
	@org.simpleframework.xml.Attribute(required=false,name="source")
	private String _attr_source ;
	public void setSourceAttr( String attr_source) { 
		this._attr_source = attr_source ;
	}
	public String getSourceAttr() { 
		return this._attr_source;
	}
 
	// ===========-- transcript_ref --===========
	@org.simpleframework.xml.Attribute(required=false,name="transcript_ref")
	private String _attr_transcriptRef ;
	public void setTranscriptRefAttr( String attr_transcriptRef) { 
		this._attr_transcriptRef = attr_transcriptRef ;
	}
	public String getTranscriptRefAttr() { 
		return this._attr_transcriptRef;
	}
}
