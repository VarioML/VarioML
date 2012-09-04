package org.varioml.simplexml;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"gender","dob","original_id","role","relationship","organism","strain","cultivar","phenotype","observation","population","variant","variant_group","source","db_xref","comment","sharing_policy","creation_date","modification_date"})
public class Individual {
	//xml-element used for code generation: //lsdb/individual

	public Individual(  ) {
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
 
	// ===========-- gender --===========
	@org.simpleframework.xml.Element(required=false,name="gender") 
	private Gender _gender ;
	public void setGender( Gender gender) { 
		this._gender = gender ;
	}
	public Gender getGender() {
		return this._gender;
	}
 
	// ===========-- dob --===========
	@org.simpleframework.xml.Element(required=false,name="dob") 
	private org.varioml.util.VarioDate _dob ;
	public void setDob( org.varioml.util.VarioDate dob) { 
		this._dob = dob ;
	}
	public org.varioml.util.VarioDate getDob() {
		return this._dob;
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
 
	// ===========-- role --===========
	@org.simpleframework.xml.Element(required=false,name="role") 
	private Role _role ;
	public void setRole( Role role) { 
		this._role = role ;
	}
	public Role getRole() {
		return this._role;
	}
 
	// ===========-- relationship --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="relationship") 
	private List<Relationship> _relationship ;
	public void setRelationshipList( List<Relationship> relationship) { 
		this._relationship = relationship ;
	}
	public List<Relationship> getRelationshipList()  { 
		return this._relationship;
	}
	public void addRelationship(Relationship item ) { 
		if ( this._relationship == null ) { 
			this._relationship = new ArrayList<Relationship>();
		}
		this._relationship.add( item);
	}
 
	// ===========-- organism --===========
	@org.simpleframework.xml.Element(required=false,name="organism") 
	private Organism _organism ;
	public void setOrganism( Organism organism) { 
		this._organism = organism ;
	}
	public Organism getOrganism() {
		return this._organism;
	}
 
	// ===========-- strain --===========
	@org.simpleframework.xml.Element(required=false,name="strain") 
	private Strain _strain ;
	public void setStrain( Strain strain) { 
		this._strain = strain ;
	}
	public Strain getStrain() {
		return this._strain;
	}
 
	// ===========-- cultivar --===========
	@org.simpleframework.xml.Element(required=false,name="cultivar") 
	private Cultivar _cultivar ;
	public void setCultivar( Cultivar cultivar) { 
		this._cultivar = cultivar ;
	}
	public Cultivar getCultivar() {
		return this._cultivar;
	}
 
	// ===========-- phenotype --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="phenotype") 
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
 
	// ===========-- observation --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="observation") 
	private List<Observation> _observation ;
	public void setObservationList( List<Observation> observation) { 
		this._observation = observation ;
	}
	public List<Observation> getObservationList()  { 
		return this._observation;
	}
	public void addObservation(Observation item ) { 
		if ( this._observation == null ) { 
			this._observation = new ArrayList<Observation>();
		}
		this._observation.add( item);
	}
 
	// ===========-- population --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="population") 
	private List<Population> _population ;
	public void setPopulationList( List<Population> population) { 
		this._population = population ;
	}
	public List<Population> getPopulationList()  { 
		return this._population;
	}
	public void addPopulation(Population item ) { 
		if ( this._population == null ) { 
			this._population = new ArrayList<Population>();
		}
		this._population.add( item);
	}
 
	// ===========-- variant --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="variant") 
	private List<Variant> _variant ;
	public void setVariantList( List<Variant> variant) { 
		this._variant = variant ;
	}
	public List<Variant> getVariantList()  { 
		return this._variant;
	}
	public void addVariant(Variant item ) { 
		if ( this._variant == null ) { 
			this._variant = new ArrayList<Variant>();
		}
		this._variant.add( item);
	}
 
	// ===========-- variant_group --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="variant_group") 
	private List<VariantGroup> _variantGroup ;
	public void setVariantGroupList( List<VariantGroup> variantGroup) { 
		this._variantGroup = variantGroup ;
	}
	public List<VariantGroup> getVariantGroupList()  { 
		return this._variantGroup;
	}
	public void addVariantGroup(VariantGroup item ) { 
		if ( this._variantGroup == null ) { 
			this._variantGroup = new ArrayList<VariantGroup>();
		}
		this._variantGroup.add( item);
	}
 
	// ===========-- source --===========
	@org.simpleframework.xml.Element(required=false,name="source") 
	private Source _source ;
	public void setSource( Source source) { 
		this._source = source ;
	}
	public Source getSource() {
		return this._source;
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
 
	// ===========-- sharing_policy --===========
	@org.simpleframework.xml.Element(required=false,name="sharing_policy") 
	private SharingPolicy _sharingPolicy ;
	public void setSharingPolicy( SharingPolicy sharingPolicy) { 
		this._sharingPolicy = sharingPolicy ;
	}
	public SharingPolicy getSharingPolicy() {
		return this._sharingPolicy;
	}
 
	// ===========-- creation_date --===========
	@org.simpleframework.xml.Element(required=false,name="creation_date") 
	private org.varioml.util.VarioDate _creationDate ;
	public void setCreationDate( org.varioml.util.VarioDate creationDate) { 
		this._creationDate = creationDate ;
	}
	public org.varioml.util.VarioDate getCreationDate() {
		return this._creationDate;
	}
 
	// ===========-- modification_date --===========
	@org.simpleframework.xml.Element(required=false,name="modification_date") 
	private org.varioml.util.VarioDate _modificationDate ;
	public void setModificationDate( org.varioml.util.VarioDate modificationDate) { 
		this._modificationDate = modificationDate ;
	}
	public org.varioml.util.VarioDate getModificationDate() {
		return this._modificationDate;
	}
}
