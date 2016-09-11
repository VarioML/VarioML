package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="population")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_description","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_accession","_attr_source","_attr_term","_attr_type","_attr_uri","_description","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})


public class Population /**/implements VmlSimpleObservation /**/ {
	//xml-element used for code generation: //lsdb/individual/population

	public Population(  ) {
	}
 
	// ===========-- accession --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="accession")
	private String _attr_accession ;
	public void setAccession( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
	public String getAccession() { 
		return this._attr_accession;
	}
 
	// ===========-- source --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="source")
	private String _attr_source ;
	public void setSource( String attr_source) { 
		this._attr_source = attr_source ;
	}
	public String getSource() { 
		return this._attr_source;
	}
 
	// ===========-- term --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="term")
	private String _attr_term ;
	public void setTerm( String attr_term) { 
		this._attr_term = attr_term ;
	}
	public String getTerm() { 
		return this._attr_term;
	}
 
	// ===========-- type --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="type")
	private String _attr_type ;
	public void setType( String attr_type) { 
		this._attr_type = attr_type ;
	}
	public String getType() { 
		return this._attr_type;
	}
 
	// ===========-- uri --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="uri")
	private String _attr_uri ;
	public void setUri( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUri() { 
		return this._attr_uri;
	}
 
	// ===========-- description --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="description",type=String.class,namespace="http://varioml.org/xml/1.0")
	private String _description ;
	public void setDescription( String description) { 
		this._description = description ;
	}
	public String getDescription() {
		return this._description;
	}
 
	// ===========-- value --===========
   @com.fasterxml.jackson.annotation.JsonProperty("values")
   @javax.xml.bind.annotation.XmlElement(required=false,name="value",type=Value.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("evidence_codes")
   @javax.xml.bind.annotation.XmlElement(required=false,name="evidence_code",type=EvidenceCode.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("protocol_ids")
   @javax.xml.bind.annotation.XmlElement(required=false,name="protocol_id",type=ProtocolId.class,namespace="http://varioml.org/xml/1.0")
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
	@javax.xml.bind.annotation.XmlElement(required=false,name="observation_date",type=ObservationDate.class,namespace="http://varioml.org/xml/1.0")
	private ObservationDate _observationDate ;
	public void setObservationDate( ObservationDate observationDate) { 
		this._observationDate = observationDate ;
	}
	public ObservationDate getObservationDate() {
		return this._observationDate;
	}
 
	// ===========-- db_xref --===========
   @com.fasterxml.jackson.annotation.JsonProperty("db_xrefs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="db_xref",type=DbXref.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("comments")
   @javax.xml.bind.annotation.XmlElement(required=false,name="comment",type=Comment.class,namespace="http://varioml.org/xml/1.0")
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