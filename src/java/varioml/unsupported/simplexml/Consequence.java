package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"description","value","evidence_code","protocol_id","observation_date","db_xref","comment"})
public class Consequence {
	//xml-element used for code generation: //lsdb/individual/variant/consequence

	public Consequence(  ) {
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
 
	// ===========-- term --===========
	@org.simpleframework.xml.Attribute(required=false,name="term")
	private String _attr_term ;
	public void setTermAttr( String attr_term) { 
		this._attr_term = attr_term ;
	}
	public String getTermAttr() { 
		return this._attr_term;
	}
 
	// ===========-- uri --===========
	@org.simpleframework.xml.Attribute(required=false,name="uri")
	private String _attr_uri ;
	public void setUriAttr( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUriAttr() { 
		return this._attr_uri;
	}
 
	// ===========-- description --===========
	@org.simpleframework.xml.Element(required=false,name="description") 
	private String _description ;
	public void setDescription( String description) { 
		this._description = description ;
	}
	public String getDescription() {
		return this._description;
	}
 
	// ===========-- value --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="value") 
	private List<Value> _value ;
	public void setValueList( List<Value> value) { 
		this._value = value ;
	}
	public List<Value> getValueList()  { 
		return this._value;
	}
	public void addValue(Value item ) { 
		if ( this._value == null ) { 
			this._value = new ArrayList<Value>();
		}
		this._value.add( item);
	}
 
	// ===========-- evidence_code --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="evidence_code") 
	private List<EvidenceCode> _evidenceCode ;
	public void setEvidenceCodeList( List<EvidenceCode> evidenceCode) { 
		this._evidenceCode = evidenceCode ;
	}
	public List<EvidenceCode> getEvidenceCodeList()  { 
		return this._evidenceCode;
	}
	public void addEvidenceCode(EvidenceCode item ) { 
		if ( this._evidenceCode == null ) { 
			this._evidenceCode = new ArrayList<EvidenceCode>();
		}
		this._evidenceCode.add( item);
	}
 
	// ===========-- protocol_id --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="protocol_id") 
	private List<ProtocolId> _protocolId ;
	public void setProtocolIdList( List<ProtocolId> protocolId) { 
		this._protocolId = protocolId ;
	}
	public List<ProtocolId> getProtocolIdList()  { 
		return this._protocolId;
	}
	public void addProtocolId(ProtocolId item ) { 
		if ( this._protocolId == null ) { 
			this._protocolId = new ArrayList<ProtocolId>();
		}
		this._protocolId.add( item);
	}
 
	// ===========-- observation_date --===========
	@org.simpleframework.xml.Element(required=false,name="observation_date") 
	private ObservationDate _observationDate ;
	public void setObservationDate( ObservationDate observationDate) { 
		this._observationDate = observationDate ;
	}
	public ObservationDate getObservationDate() {
		return this._observationDate;
	}
 
	// ===========-- db_xref --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="db_xref") 
	private List<DbXref> _dbXref ;
	public void setDbXrefList( List<DbXref> dbXref) { 
		this._dbXref = dbXref ;
	}
	public List<DbXref> getDbXrefList()  { 
		return this._dbXref;
	}
	public void addDbXref(DbXref item ) { 
		if ( this._dbXref == null ) { 
			this._dbXref = new ArrayList<DbXref>();
		}
		this._dbXref.add( item);
	}
 
	// ===========-- comment --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="comment") 
	private List<Comment> _comment ;
	public void setCommentList( List<Comment> comment) { 
		this._comment = comment ;
	}
	public List<Comment> getCommentList()  { 
		return this._comment;
	}
	public void addComment(Comment item ) { 
		if ( this._comment == null ) { 
			this._comment = new ArrayList<Comment>();
		}
		this._comment.add( item);
	}
}
