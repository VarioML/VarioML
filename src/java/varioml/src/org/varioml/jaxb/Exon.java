package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="exon")
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_accession","_attr_source","_attr_transcriptRef",})


public class Exon {
	//xml-element used for code generation: //lsdb/individual/variant/exon

	public Exon(  ) {
	}
 
	// ===========-- accession --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="accession")
	private String _attr_accession ;
@Deprecated
	public void setAccessionAttr( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
@Deprecated
	public String getAccessionAttr() { 
		return this._attr_accession;
	}
	public void setAccession( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
	public String getAccession() { 
		return this._attr_accession;
	}
 
	// ===========-- source --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="source")
	private String _attr_source ;
@Deprecated
	public void setSourceAttr( String attr_source) { 
		this._attr_source = attr_source ;
	}
@Deprecated
	public String getSourceAttr() { 
		return this._attr_source;
	}
	public void setSource( String attr_source) { 
		this._attr_source = attr_source ;
	}
	public String getSource() { 
		return this._attr_source;
	}
 
	// ===========-- transcript_ref --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="transcript_ref")
	private String _attr_transcriptRef ;
@Deprecated
	public void setTranscriptRefAttr( String attr_transcriptRef) { 
		this._attr_transcriptRef = attr_transcriptRef ;
	}
@Deprecated
	public String getTranscriptRefAttr() { 
		return this._attr_transcriptRef;
	}
	public void setTranscriptRef( String attr_transcriptRef) { 
		this._attr_transcriptRef = attr_transcriptRef ;
	}
	public String getTranscriptRef() { 
		return this._attr_transcriptRef;
	}
}
