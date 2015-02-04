package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="haplotype")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_name","_variant","_sequence","_consequence","_pathogenicity","_frequency","_seqChanges","_aliases","_source","_location","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_allele","_attr_id","_attr_uri","_name","_variant","_sequence","_consequence","_pathogenicity","_frequency","_seqChanges","_aliases","_source","_location","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})


public class Haplotype /**/implements VmlVariantObservation,VmlFrequency/**/ {
	//xml-element used for code generation: //variant_group/variant/haplotype

	public Haplotype(  ) {
	}
 
	// ===========-- allele --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="allele")
	private Integer _attr_allele ;
	public void setAllele( Integer attr_allele) { 
		this._attr_allele = attr_allele ;
	}
	public Integer getAllele() { 
		return this._attr_allele;
	}
 
	// ===========-- id --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="id")
	private String _attr_id ;
	public void setId( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getId() { 
		return this._attr_id;
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
 
	// ===========-- name --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="name",type=VariantName.class,namespace="http://varioml.org/xml/1.0")
	private VariantName _name ;
	public void setName( VariantName name) { 
		this._name = name ;
	}
	public VariantName getName() {
		return this._name;
	}
 
	// ===========-- variant --===========
   @com.fasterxml.jackson.annotation.JsonProperty("variants")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant",type=VariantEvent.class,namespace="http://varioml.org/xml/1.0")
	private List<VmlVariantEvent> _variant ;
	public void setVariantList( List<VmlVariantEvent> variant) { 
		this._variant = variant ;
	}
	public List<VmlVariantEvent> getVariantList()  { 
		return this._variant;
	}
	public void addVariant(VmlVariantEvent item ) { 
		if ( this._variant == null ) { 
			this._variant = new ArrayList<VmlVariantEvent>();
		}
		this._variant.add( item);
	}
 
	// ===========-- sequence --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="sequence",type=Sequence.class,namespace="http://varioml.org/xml/1.0")
	private Sequence _sequence ;
	public void setSequence( Sequence sequence) { 
		this._sequence = sequence ;
	}
	public Sequence getSequence() {
		return this._sequence;
	}
 
	// ===========-- consequence --===========
   @com.fasterxml.jackson.annotation.JsonProperty("consequences")
   @javax.xml.bind.annotation.XmlElement(required=false,name="consequence",type=Consequence.class,namespace="http://varioml.org/xml/1.0")
	private List<Consequence> _consequence ;
	public void setConsequenceList( List<Consequence> consequence) { 
		this._consequence = consequence ;
	}
	public List<Consequence> getConsequenceList()  { 
		return this._consequence;
	}
	public void addConsequence(Consequence item ) { 
		if ( this._consequence == null ) { 
			this._consequence = new ArrayList<Consequence>();
		}
		this._consequence.add( item);
	}
 
	// ===========-- pathogenicity --===========
   @com.fasterxml.jackson.annotation.JsonProperty("pathogenicities")
   @javax.xml.bind.annotation.XmlElement(required=false,name="pathogenicity",type=Pathogenicity.class,namespace="http://varioml.org/xml/1.0")
	private List<Pathogenicity> _pathogenicity ;
	public void setPathogenicityList( List<Pathogenicity> pathogenicity) { 
		this._pathogenicity = pathogenicity ;
	}
	public List<Pathogenicity> getPathogenicityList()  { 
		return this._pathogenicity;
	}
	public void addPathogenicity(Pathogenicity item ) { 
		if ( this._pathogenicity == null ) { 
			this._pathogenicity = new ArrayList<Pathogenicity>();
		}
		this._pathogenicity.add( item);
	}
 
	// ===========-- frequency --===========
   @com.fasterxml.jackson.annotation.JsonProperty("frequencies")
   @javax.xml.bind.annotation.XmlElement(required=false,name="frequency",type=Frequency.class,namespace="http://varioml.org/xml/1.0")
	private List<Frequency> _frequency ;
	public void setFrequencyList( List<Frequency> frequency) { 
		this._frequency = frequency ;
	}
	public List<Frequency> getFrequencyList()  { 
		return this._frequency;
	}
	public void addFrequency(Frequency item ) { 
		if ( this._frequency == null ) { 
			this._frequency = new ArrayList<Frequency>();
		}
		this._frequency.add( item);
	}
 
	// ===========-- seq_changes --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="seq_changes",type=SeqChanges.class,namespace="http://varioml.org/xml/1.0")
	private SeqChanges _seqChanges ;
	public void setSeqChanges( SeqChanges seqChanges) { 
		this._seqChanges = seqChanges ;
	}
	public SeqChanges getSeqChanges() {
		return this._seqChanges;
	}
 
	// ===========-- aliases --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="aliases",type=Aliases.class,namespace="http://varioml.org/xml/1.0")
	private Aliases _aliases ;
	public void setAliases( Aliases aliases) { 
		this._aliases = aliases ;
	}
	public Aliases getAliases() {
		return this._aliases;
	}
 
	// ===========-- source --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="source",type=Source.class,namespace="http://varioml.org/xml/1.0")
	private Source _source ;
	public void setSource( Source source) { 
		this._source = source ;
	}
	public Source getSource() {
		return this._source;
	}
 
	// ===========-- location --===========
   @com.fasterxml.jackson.annotation.JsonProperty("locations")
   @javax.xml.bind.annotation.XmlElement(required=false,name="location",type=Location.class,namespace="http://varioml.org/xml/1.0")
	private List<Location> _location ;
	public void setLocationList( List<Location> location) { 
		this._location = location ;
	}
	public List<Location> getLocationList()  { 
		return this._location;
	}
	public void addLocation(Location item ) { 
		if ( this._location == null ) { 
			this._location = new ArrayList<Location>();
		}
		this._location.add( item);
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