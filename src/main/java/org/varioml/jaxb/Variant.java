package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

import org.varioml.util.Util;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="variant")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_gene","_refSeq","_name","_haplotype","_panel","_seqRegion","_variantType","_variantClass","_originalId","_exon","_sequence","_genotype","_consequence","_pathogenicity","_sample","_tissue","_variantDetection","_restrictionSite","_tissueDistribution","_geneticOrigin","_frequency","_seqChanges","_aliases","_source","_location","_sharingPolicy","_creationDate","_modificationDate","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_copyCount","_attr_genotypic","_attr_id","_attr_subcellularPart","_attr_type","_attr_uri","_gene","_refSeq","_name","_haplotype","_panel","_seqRegion","_variantType","_variantClass","_originalId","_exon","_sequence","_genotype","_consequence","_pathogenicity","_sample","_tissue","_variantDetection","_restrictionSite","_tissueDistribution","_geneticOrigin","_frequency","_seqChanges","_aliases","_source","_location","_sharingPolicy","_creationDate","_modificationDate","_value","_evidenceCode","_protocolId","_observationDate","_dbXref","_comment"})


public class Variant /**/implements VmlReportingVariant,VmlShareable /**/ {
	//xml-element used for code generation: //lsdb/variant

	public Variant(  ) {
	}
 
	// ===========-- copy_count --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="copy_count")
	private Double _attr_copyCount ;
	public void setCopyCount( Double attr_copyCount) { 
		this._attr_copyCount = attr_copyCount ;
	}
	public Double getCopyCount() { 
		return this._attr_copyCount;
	}
 
	// ===========-- genotypic --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="genotypic")
	private Boolean _attr_genotypic ;
	public void setGenotypic( Boolean attr_genotypic) { 
		this._attr_genotypic = attr_genotypic ;
	}
	public Boolean isGenotypic() { 
		return this._attr_genotypic;
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
 
	// ===========-- subcellular_part --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="subcellular_part")
	private String _attr_subcellularPart ;
	public void setSubcellularPart( String attr_subcellularPart) { 
		this._attr_subcellularPart = attr_subcellularPart ;
	}
	public String getSubcellularPart() { 
		return this._attr_subcellularPart;
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
 
	// ===========-- gene --===========
   @com.fasterxml.jackson.annotation.JsonProperty("genes")
   @javax.xml.bind.annotation.XmlElement(required=false,name="gene",type=Gene.class,namespace="http://varioml.org/xml/1.0")
	private List<Gene> _gene ;
	public void setGeneList( List<Gene> gene) { 
		this._gene = gene ;
	}
	public List<Gene> getGeneList()  { 
		return this._gene;
	}
	public void addGene(Gene item ) { 
		if ( this._gene == null ) { 
			this._gene = new ArrayList<Gene>();
		}
		this._gene.add( item);
	}
	public void setGene( Gene gene) { 
		addGene( gene);
		if ( getGeneList().size() > 1) Util.fatal(Variant.class, "Legacy mathod setGene failed.. Only one gene is expected for the variant (in this legacy case)");
	}
	public Gene getGene() { 
		if ( getGeneList() != null & getGeneList().size()> 1) Util.fatal(Variant.class, "Legacy mathod getGene failed.. Only one gene is expected for the variant (in this legacy case)");
		return getGeneList() == null ? null : getGeneList().get(0);
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
 
	// ===========-- name --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="name",type=VariantName.class,namespace="http://varioml.org/xml/1.0")
	private VariantName _name ;
	public void setName( VariantName name) { 
		this._name = name ;
	}
	public VariantName getName() {
		return this._name;
	}
 
	// ===========-- haplotype --===========
   @com.fasterxml.jackson.annotation.JsonProperty("haplotypes")
   @javax.xml.bind.annotation.XmlElement(required=false,name="haplotype",type=Haplotype.class,namespace="http://varioml.org/xml/1.0")
	private List<Haplotype> _haplotype ;
	public void setHaplotypeList( List<Haplotype> haplotype) { 
		this._haplotype = haplotype ;
	}
	public List<Haplotype> getHaplotypeList()  { 
		return this._haplotype;
	}
	public void addHaplotype(Haplotype item ) { 
		if ( this._haplotype == null ) { 
			this._haplotype = new ArrayList<Haplotype>();
		}
		this._haplotype.add( item);
	}
 
	// ===========-- panel --===========
   @com.fasterxml.jackson.annotation.JsonProperty("panels")
   @javax.xml.bind.annotation.XmlElement(required=false,name="panel",type=Panel.class,namespace="http://varioml.org/xml/1.0")
	private List<Panel> _panel ;
	public void setPanelList( List<Panel> panel) { 
		this._panel = panel ;
	}
	public List<Panel> getPanelList()  { 
		return this._panel;
	}
	public void addPanel(Panel item ) { 
		if ( this._panel == null ) { 
			this._panel = new ArrayList<Panel>();
		}
		this._panel.add( item);
	}
 
	// ===========-- seq_region --===========
   @com.fasterxml.jackson.annotation.JsonProperty("seq_regions")
   @javax.xml.bind.annotation.XmlElement(required=false,name="seq_region",type=SeqRegion.class,namespace="http://varioml.org/xml/1.0")
	private List<SeqRegion> _seqRegion ;
	public void setSeqRegionList( List<SeqRegion> seqRegion) { 
		this._seqRegion = seqRegion ;
	}
	public List<SeqRegion> getSeqRegionList()  { 
		return this._seqRegion;
	}
	public void addSeqRegion(SeqRegion item ) { 
		if ( this._seqRegion == null ) { 
			this._seqRegion = new ArrayList<SeqRegion>();
		}
		this._seqRegion.add( item);
	}
 
	// ===========-- variant_type --===========
   @com.fasterxml.jackson.annotation.JsonProperty("variant_types")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant_type",type=VariantType.class,namespace="http://varioml.org/xml/1.0")
	private List<VariantType> _variantType ;
	public void setVariantTypeList( List<VariantType> variantType) { 
		this._variantType = variantType ;
	}
	public List<VariantType> getVariantTypeList()  { 
		return this._variantType;
	}
	public void addVariantType(VariantType item ) { 
		if ( this._variantType == null ) { 
			this._variantType = new ArrayList<VariantType>();
		}
		this._variantType.add( item);
	}
 
	// ===========-- variant_class --===========
   @com.fasterxml.jackson.annotation.JsonProperty("variant_classs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant_class",type=VariantClass.class,namespace="http://varioml.org/xml/1.0")
	private List<VariantClass> _variantClass ;
	public void setVariantClassList( List<VariantClass> variantClass) { 
		this._variantClass = variantClass ;
	}
	public List<VariantClass> getVariantClassList()  { 
		return this._variantClass;
	}
	public void addVariantClass(VariantClass item ) { 
		if ( this._variantClass == null ) { 
			this._variantClass = new ArrayList<VariantClass>();
		}
		this._variantClass.add( item);
	}
 
	// ===========-- original_id --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="original_id",type=OriginalId.class,namespace="http://varioml.org/xml/1.0")
	private OriginalId _originalId ;
	public void setOriginalId( OriginalId originalId) { 
		this._originalId = originalId ;
	}
	public OriginalId getOriginalId() {
		return this._originalId;
	}
 
	// ===========-- exon --===========
   @com.fasterxml.jackson.annotation.JsonProperty("exons")
   @javax.xml.bind.annotation.XmlElement(required=false,name="exon",type=Exon.class,namespace="http://varioml.org/xml/1.0")
	private List<Exon> _exon ;
	public void setExonList( List<Exon> exon) { 
		this._exon = exon ;
	}
	public List<Exon> getExonList()  { 
		return this._exon;
	}
	public void addExon(Exon item ) { 
		if ( this._exon == null ) { 
			this._exon = new ArrayList<Exon>();
		}
		this._exon.add( item);
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
 
	// ===========-- genotype --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="genotype",type=Genotype.class,namespace="http://varioml.org/xml/1.0")
	private Genotype _genotype ;
	public void setGenotype( Genotype genotype) { 
		this._genotype = genotype ;
	}
	public Genotype getGenotype() {
		return this._genotype;
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
 
	// ===========-- sample --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="sample",type=Sample.class,namespace="http://varioml.org/xml/1.0")
	private Sample _sample ;
	public void setSample( Sample sample) { 
		this._sample = sample ;
	}
	public Sample getSample() {
		return this._sample;
	}
 
	// ===========-- tissue --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="tissue",type=Tissue.class,namespace="http://varioml.org/xml/1.0")
	private Tissue _tissue ;
	public void setTissue( Tissue tissue) { 
		this._tissue = tissue ;
	}
	public Tissue getTissue() {
		return this._tissue;
	}
 
	// ===========-- variant_detection --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="variant_detection",type=VariantDetection.class,namespace="http://varioml.org/xml/1.0")
	private VariantDetection _variantDetection ;
	public void setVariantDetection( VariantDetection variantDetection) { 
		this._variantDetection = variantDetection ;
	}
	public VariantDetection getVariantDetection() {
		return this._variantDetection;
	}
 
	// ===========-- restriction_site --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="restriction_site",type=RestrictionSite.class,namespace="http://varioml.org/xml/1.0")
	private RestrictionSite _restrictionSite ;
	public void setRestrictionSite( RestrictionSite restrictionSite) { 
		this._restrictionSite = restrictionSite ;
	}
	public RestrictionSite getRestrictionSite() {
		return this._restrictionSite;
	}
 
	// ===========-- tissue_distribution --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="tissue_distribution",type=TissueDistribution.class,namespace="http://varioml.org/xml/1.0")
	private TissueDistribution _tissueDistribution ;
	public void setTissueDistribution( TissueDistribution tissueDistribution) { 
		this._tissueDistribution = tissueDistribution ;
	}
	public TissueDistribution getTissueDistribution() {
		return this._tissueDistribution;
	}
 
	// ===========-- genetic_origin --===========
   @com.fasterxml.jackson.annotation.JsonProperty("genetic_origins")
   @javax.xml.bind.annotation.XmlElement(required=false,name="genetic_origin",type=GeneticOrigin.class,namespace="http://varioml.org/xml/1.0")
	private List<GeneticOrigin> _geneticOrigin ;
	public void setGeneticOriginList( List<GeneticOrigin> geneticOrigin) { 
		this._geneticOrigin = geneticOrigin ;
	}
	public List<GeneticOrigin> getGeneticOriginList()  { 
		return this._geneticOrigin;
	}
	public void addGeneticOrigin(GeneticOrigin item ) { 
		if ( this._geneticOrigin == null ) { 
			this._geneticOrigin = new ArrayList<GeneticOrigin>();
		}
		this._geneticOrigin.add( item);
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
 
	// ===========-- sharing_policy --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="sharing_policy",type=SharingPolicy.class,namespace="http://varioml.org/xml/1.0")
	private SharingPolicy _sharingPolicy ;
	public void setSharingPolicy( SharingPolicy sharingPolicy) { 
		this._sharingPolicy = sharingPolicy ;
	}
	public SharingPolicy getSharingPolicy() {
		return this._sharingPolicy;
	}
 
	// ===========-- creation_date --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="creation_date",type=org.varioml.util.VMLDate.class,namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.VMLDate _creationDate ;
	public void setCreationDate( org.varioml.util.VMLDate creationDate) { 
		this._creationDate = creationDate ;
	}
	public org.varioml.util.VMLDate getCreationDate() {
		return this._creationDate;
	}
 
	// ===========-- modification_date --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="modification_date",type=org.varioml.util.VMLDate.class,namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.VMLDate _modificationDate ;
	public void setModificationDate( org.varioml.util.VMLDate modificationDate) { 
		this._modificationDate = modificationDate ;
	}
	public org.varioml.util.VMLDate getModificationDate() {
		return this._modificationDate;
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
