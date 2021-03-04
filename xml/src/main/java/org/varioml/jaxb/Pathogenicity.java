package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="pathogenicity")
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(propOrder = {  "_phenotype","_factor","_description","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_accession","_attr_panelRef","_attr_scope","_attr_source","_attr_term","_attr_uri","_attr_xmlns","_attr_xmlnsXsi","_attr_xsiSchemaLocation","_phenotype","_factor","_description","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})


public class Pathogenicity /**/implements VmlSimpleObservation /**/ {
	//xml-element used for code generation: //pathogenicity

	public Pathogenicity(  ) {
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
 
	// ===========-- panel_ref --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="panel_ref")
	private String _attr_panelRef ;
	public void setPanelRef( String attr_panelRef) { 
		this._attr_panelRef = attr_panelRef ;
	}
	public String getPanelRef() { 
		return this._attr_panelRef;
	}
 
	// ===========-- scope --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="scope")
	private String _attr_scope ;
	public void setScope( String attr_scope) { 
		this._attr_scope = attr_scope ;
	}
	public String getScope() { 
		return this._attr_scope;
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
 
	// ===========-- uri --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="uri")
	private String _attr_uri ;
	public void setUri( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUri() { 
		return this._attr_uri;
	}
 
	// ===========-- xmlns --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xmlns")
	private String _attr_xmlns ;
	public void setXmlns( String attr_xmlns) { 
		this._attr_xmlns = attr_xmlns ;
	}
	public String getXmlns() { 
		return this._attr_xmlns;
	}
 
	// ===========-- xmlns:xsi --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xmlns:xsi")
	private String _attr_xmlnsXsi ;
	public void setXmlnsXsi( String attr_xmlnsXsi) { 
		this._attr_xmlnsXsi = attr_xmlnsXsi ;
	}
	public String getXmlnsXsi() { 
		return this._attr_xmlnsXsi;
	}
 
	// ===========-- xsi:schemaLocation --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="xsi:schemaLocation")
	private String _attr_xsiSchemaLocation ;
	public void setXsiSchemaLocation( String attr_xsiSchemaLocation) { 
		this._attr_xsiSchemaLocation = attr_xsiSchemaLocation ;
	}
	public String getXsiSchemaLocation() { 
		return this._attr_xsiSchemaLocation;
	}
 
	// ===========-- phenotype --===========
   @com.fasterxml.jackson.annotation.JsonProperty("phenotypes")
   @javax.xml.bind.annotation.XmlElement(required=false,name="phenotype",type=Phenotype.class,namespace="http://varioml.org/xml/1.0")
	private List<Phenotype> _phenotype ;
	public void setPhenotypeList( List<Phenotype> phenotype) { 
		this._phenotype = phenotype ;
	}
	public List<Phenotype> getPhenotypeList()  { 
		return this._phenotype;
	}
	public void addPhenotype(Phenotype item ) { 
		if ( this._phenotype == null ) { 
			this._phenotype = new ArrayList<Phenotype>();
		}
		this._phenotype.add( item);
	}
 
	// ===========-- factor --===========
   @com.fasterxml.jackson.annotation.JsonProperty("factors")
   @javax.xml.bind.annotation.XmlElement(required=false,name="factor",type=Factor.class,namespace="http://varioml.org/xml/1.0")
	private List<Factor> _factor ;
	public void setFactorList( List<Factor> factor) { 
		this._factor = factor ;
	}
	public List<Factor> getFactorList()  { 
		return this._factor;
	}
	public void addFactor(Factor item ) { 
		if ( this._factor == null ) { 
			this._factor = new ArrayList<Factor>();
		}
		this._factor.add( item);
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