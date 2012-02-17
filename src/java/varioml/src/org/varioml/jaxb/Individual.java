package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="individual")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_gender","_dob","_originalId","_role","_relationship","_organism","_strain","_cultivar","_phenotype","_population","_variant","_variantGroup","_source","_dbXref","_comment","_sharingPolicy","_creationDate","_modificationDate"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_id","_attr_uri","_gender","_dob","_originalId","_role","_relationship","_organism","_strain","_cultivar","_phenotype","_population","_variant","_variantGroup","_source","_dbXref","_comment","_sharingPolicy","_creationDate","_modificationDate"})


public class Individual /**/implements VmlObservationTarget,VmlShareable/**/ {
	//xml-element used for code generation: //lsdb/individual

	public Individual(  ) {
	}
 
	// ===========-- id --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="id")
	private String _attr_id ;
@Deprecated
	public void setIdAttr( String attr_id) { 
		this._attr_id = attr_id ;
	}
@Deprecated
	public String getIdAttr() { 
		return this._attr_id;
	}
	public void setId( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getId() { 
		return this._attr_id;
	}
 
	// ===========-- uri --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="uri")
	private String _attr_uri ;
@Deprecated
	public void setUriAttr( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
@Deprecated
	public String getUriAttr() { 
		return this._attr_uri;
	}
	public void setUri( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUri() { 
		return this._attr_uri;
	}
 
	// ===========-- gender --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="gender",namespace="http://varioml.org/xml/1.0")
	private Gender _gender ;
	public void setGender( Gender gender) { 
		this._gender = gender ;
	}
	public Gender getGender() {
		return this._gender;
	}
 
	// ===========-- dob --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="dob",namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.JAXBVarioDate _dob ;
	public void setDob( org.varioml.util.JAXBVarioDate dob) { 
		this._dob = dob ;
	}
	public org.varioml.util.JAXBVarioDate getDob() {
		return this._dob;
	}
 
	// ===========-- original_id --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="original_id",namespace="http://varioml.org/xml/1.0")
	private OriginalId _originalId ;
	public void setOriginalId( OriginalId originalId) { 
		this._originalId = originalId ;
	}
	public OriginalId getOriginalId() {
		return this._originalId;
	}
 
	// ===========-- role --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="role",namespace="http://varioml.org/xml/1.0")
	private Role _role ;
	public void setRole( Role role) { 
		this._role = role ;
	}
	public Role getRole() {
		return this._role;
	}
 
	// ===========-- relationship --===========
   @org.codehaus.jackson.annotate.JsonProperty("relationships")
   @javax.xml.bind.annotation.XmlElement(required=false,name="relationship",namespace="http://varioml.org/xml/1.0")
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
   @javax.xml.bind.annotation.XmlElement(required=false,name="organism",namespace="http://varioml.org/xml/1.0")
	private Organism _organism ;
	public void setOrganism( Organism organism) { 
		this._organism = organism ;
	}
	public Organism getOrganism() {
		return this._organism;
	}
 
	// ===========-- strain --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="strain",namespace="http://varioml.org/xml/1.0")
	private Strain _strain ;
	public void setStrain( Strain strain) { 
		this._strain = strain ;
	}
	public Strain getStrain() {
		return this._strain;
	}
 
	// ===========-- cultivar --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="cultivar",namespace="http://varioml.org/xml/1.0")
	private Cultivar _cultivar ;
	public void setCultivar( Cultivar cultivar) { 
		this._cultivar = cultivar ;
	}
	public Cultivar getCultivar() {
		return this._cultivar;
	}
 
	// ===========-- phenotype --===========
   @org.codehaus.jackson.annotate.JsonProperty("phenotypes")
   @javax.xml.bind.annotation.XmlElement(required=false,name="phenotype",namespace="http://varioml.org/xml/1.0")
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
 
	// ===========-- population --===========
   @org.codehaus.jackson.annotate.JsonProperty("populations")
   @javax.xml.bind.annotation.XmlElement(required=false,name="population",namespace="http://varioml.org/xml/1.0")
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
   @org.codehaus.jackson.annotate.JsonProperty("variants")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant",namespace="http://varioml.org/xml/1.0")
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
   @org.codehaus.jackson.annotate.JsonProperty("variant_groups")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant_group",namespace="http://varioml.org/xml/1.0")
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
   @javax.xml.bind.annotation.XmlElement(required=false,name="source",namespace="http://varioml.org/xml/1.0")
	private Source _source ;
	public void setSource( Source source) { 
		this._source = source ;
	}
	public Source getSource() {
		return this._source;
	}
 
	// ===========-- db_xref --===========
   @org.codehaus.jackson.annotate.JsonProperty("db_xrefs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="db_xref",namespace="http://varioml.org/xml/1.0")
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
   @org.codehaus.jackson.annotate.JsonProperty("comments")
   @javax.xml.bind.annotation.XmlElement(required=false,name="comment",namespace="http://varioml.org/xml/1.0")
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
   @javax.xml.bind.annotation.XmlElement(required=false,name="sharing_policy",namespace="http://varioml.org/xml/1.0")
	private SharingPolicy _sharingPolicy ;
	public void setSharingPolicy( SharingPolicy sharingPolicy) { 
		this._sharingPolicy = sharingPolicy ;
	}
	public SharingPolicy getSharingPolicy() {
		return this._sharingPolicy;
	}
 
	// ===========-- creation_date --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="creation_date",namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.JAXBVarioDate _creationDate ;
	public void setCreationDate( org.varioml.util.JAXBVarioDate creationDate) { 
		this._creationDate = creationDate ;
	}
	public org.varioml.util.JAXBVarioDate getCreationDate() {
		return this._creationDate;
	}
 
	// ===========-- modification_date --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="modification_date",namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.JAXBVarioDate _modificationDate ;
	public void setModificationDate( org.varioml.util.JAXBVarioDate modificationDate) { 
		this._modificationDate = modificationDate ;
	}
	public org.varioml.util.JAXBVarioDate getModificationDate() {
		return this._modificationDate;
	}
}