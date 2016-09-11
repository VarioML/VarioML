package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="frequency")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_population","_counts","_category","_freq","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_samples","_attr_type","_population","_counts","_category","_freq","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})


public class Frequency /**/implements VmlAnnotatable /**/ {
	//xml-element used for code generation: //lsdb/individual/variant/frequency

	public Frequency(  ) {
	}
 
	// ===========-- samples --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="samples")
	private Integer _attr_samples ;
	public void setSamples( Integer attr_samples) { 
		this._attr_samples = attr_samples ;
	}
	public Integer getSamples() { 
		return this._attr_samples;
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
 
	// ===========-- population --===========
   @com.fasterxml.jackson.annotation.JsonProperty("populations")
   @javax.xml.bind.annotation.XmlElement(required=false,name="population",type=Population.class,namespace="http://varioml.org/xml/1.0")
	private Population _population ;
	public void setPopulation( Population population) { 
		this._population = population ;
	}
	public Population getPopulation()  { 
		return this._population;
	}
	@Deprecated
	public List<Population> getPopulationList()  { 
		List<Population> p = new ArrayList<Population>( );
		p.add(this._population);
		return p;
	}

	@Deprecated
	public void addPopulation(Population item ) { 
		if ( this._population != null ) { 
			throw new RuntimeException("Frequency can have only one population element. This has changed in version 2.1");
		}
		this._population = item;
	}
	@Deprecated
	public void setPopulationList(List<Population> item ) { 
		if ( item.size() != 1) { 
			throw new RuntimeException("Frequency can have only one population element (list was zero or more than one). This has changed in version 2.1");
		}
		this._population = item.get(0);
	}
 
	// ===========-- counts --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="counts",type=Integer.class,namespace="http://varioml.org/xml/1.0")
	private Integer _counts ;
	public void setCounts( Integer count) { 
		this._counts = count ; if ( _category != null || _freq != null ) org.varioml.util.Util.fatal(Frequency.class," frequency choice group support only one of following: freq,counts and category ");
	}
	public Integer getCounts() {
		return this._counts;
	}
	// ===========-- category --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="category",type=FreqCategory.class,namespace="http://varioml.org/xml/1.0")
	private FreqCategory _category ;
	public void setCategory( FreqCategory category) { 
		this._category = category ; if ( _counts != null || _freq != null ) org.varioml.util.Util.fatal(Frequency.class," frequency choice group support only one of following: freq,counts and category ");
	}
	public FreqCategory getCategory() {
		return this._category;
	}
	// ===========-- freq --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="freq",type=Double.class,namespace="http://varioml.org/xml/1.0")
	private Double _freq ;
	public void setFreq( Double freq) { 
		this._freq = freq ; if ( _category != null || _counts != null ) org.varioml.util.Util.fatal(Frequency.class," frequency choice group support only one of following: freq,counts and category ");
	}
	public Double  getFreq() {
		return this._freq;
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