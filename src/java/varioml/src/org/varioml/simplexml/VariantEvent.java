package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"name","variant_type","variant_class","original_id","exon","sequence","consequence","pathogenicity","variant_detection","restriction_site","genetic_origin","frequency","seq_changes","aliases","location","evidence_code","protocol_id","db_xref","comment"})
public class VariantEvent {
	//xml-element used for code generation: //variant_group/variant/haplotype/variant

	public VariantEvent(  ) {
	}
 
	// ===========-- id --===========
	@org.simpleframework.xml.Attribute(required=false,name="id")
	private String _attr_id ;
	public void setIdAttr( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getIdAttr() { 
		return this._attr_id;
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
 
	// ===========-- name --===========
	@org.simpleframework.xml.Element(required=false,name="name") 
	private VariantName _name ;
	public void setName( VariantName name) { 
		this._name = name ;
	}
	public VariantName getName() {
		return this._name;
	}
 
	// ===========-- variant_type --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="variant_type") 
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
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="variant_class") 
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
	@org.simpleframework.xml.Element(required=false,name="original_id") 
	private OriginalId _originalId ;
	public void setOriginalId( OriginalId originalId) { 
		this._originalId = originalId ;
	}
	public OriginalId getOriginalId() {
		return this._originalId;
	}
 
	// ===========-- exon --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="exon") 
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
	@org.simpleframework.xml.Element(required=false,name="sequence") 
	private Sequence _sequence ;
	public void setSequence( Sequence sequence) { 
		this._sequence = sequence ;
	}
	public Sequence getSequence() {
		return this._sequence;
	}
 
	// ===========-- consequence --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="consequence") 
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
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="pathogenicity") 
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
 
	// ===========-- variant_detection --===========
	@org.simpleframework.xml.Element(required=false,name="variant_detection") 
	private VariantDetection _variantDetection ;
	public void setVariantDetection( VariantDetection variantDetection) { 
		this._variantDetection = variantDetection ;
	}
	public VariantDetection getVariantDetection() {
		return this._variantDetection;
	}
 
	// ===========-- restriction_site --===========
	@org.simpleframework.xml.Element(required=false,name="restriction_site") 
	private RestrictionSite _restrictionSite ;
	public void setRestrictionSite( RestrictionSite restrictionSite) { 
		this._restrictionSite = restrictionSite ;
	}
	public RestrictionSite getRestrictionSite() {
		return this._restrictionSite;
	}
 
	// ===========-- genetic_origin --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="genetic_origin") 
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
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="frequency") 
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
	@org.simpleframework.xml.Element(required=false,name="seq_changes") 
	private SeqChanges _seqChanges ;
	public void setSeqChanges( SeqChanges seqChanges) { 
		this._seqChanges = seqChanges ;
	}
	public SeqChanges getSeqChanges() {
		return this._seqChanges;
	}
 
	// ===========-- aliases --===========
	@org.simpleframework.xml.Element(required=false,name="aliases") 
	private Aliases _aliases ;
	public void setAliases( Aliases aliases) { 
		this._aliases = aliases ;
	}
	public Aliases getAliases() {
		return this._aliases;
	}
 
	// ===========-- location --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="location") 
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
