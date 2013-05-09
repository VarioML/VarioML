package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonAutoDetect( fieldVisibility =  com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE, getterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE,setterVisibility= com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE)
@com.fasterxml.jackson.databind.annotation.JsonSerialize(include = com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="panel")
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlType(propOrder = {  "_individual","_gender","_dob","_age","_originalId","_role","_relationship","_organism","_strain","_cultivar","_phenotype","_observation","_population","_variant","_variantGroup","_source","_dbXref","_comment","_sharingPolicy","_creationDate","_modificationDate"})
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={  "_attr_id","_attr_size","_attr_type","_attr_uri","_attr_xmlns","_attr_xmlnsXsi","_attr_xsiSchemaLocation","_individual","_gender","_dob","_age","_originalId","_role","_relationship","_organism","_strain","_cultivar","_phenotype","_observation","_population","_variant","_variantGroup","_source","_dbXref","_comment","_sharingPolicy","_creationDate","_modificationDate"})


public class Panel /**/implements VmlObservationTarget,VmlShareable/**/ {
	//xml-element used for code generation: //panel

	public Panel(  ) {
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
 
	// ===========-- size --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="size")
	private Integer _attr_size ;
	public void setSize( Integer attr_size) { 
		this._attr_size = attr_size ;
	}
	public Integer getSize() { 
		return this._attr_size;
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
 
	// ===========-- individual --===========
   @com.fasterxml.jackson.annotation.JsonProperty("individuals")
   @javax.xml.bind.annotation.XmlElement(required=false,name="individual",type=Individual.class,namespace="http://varioml.org/xml/1.0")
	private List<Individual> _individual ;
	public void setIndividualList( List<Individual> individual) { 
		this._individual = individual ;
	}
	public List<Individual> getIndividualList()  { 
		return this._individual;
	}
	public void addIndividual(Individual item ) { 
		if ( this._individual == null ) { 
			this._individual = new ArrayList<Individual>();
		}
		this._individual.add( item);
	}
 
	// ===========-- gender --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="gender",type=Gender.class,namespace="http://varioml.org/xml/1.0")
	private Gender _gender ;
	public void setGender( Gender gender) { 
		this._gender = gender ;
	}
	public Gender getGender() {
		return this._gender;
	}
 
	// ===========-- dob --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="dob",type=org.varioml.util.VMLDate.class,namespace="http://varioml.org/xml/1.0")
	private org.varioml.util.VMLDate _dob ;
	public void setDob( org.varioml.util.VMLDate dob) { 
		this._dob = dob ;
	}
	public org.varioml.util.VMLDate getDob() {
		return this._dob;
	}
 
	// ===========-- age --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="age",type=Age.class,namespace="http://varioml.org/xml/1.0")
	private Age _age ;
	public void setAge( Age age) { 
		this._age = age ;
	}
	public Age getAge() {
		return this._age;
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
 
	// ===========-- role --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="role",type=Role.class,namespace="http://varioml.org/xml/1.0")
	private Role _role ;
	public void setRole( Role role) { 
		this._role = role ;
	}
	public Role getRole() {
		return this._role;
	}
 
	// ===========-- relationship --===========
   @com.fasterxml.jackson.annotation.JsonProperty("relationships")
   @javax.xml.bind.annotation.XmlElement(required=false,name="relationship",type=Relationship.class,namespace="http://varioml.org/xml/1.0")
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
	@javax.xml.bind.annotation.XmlElement(required=false,name="organism",type=Organism.class,namespace="http://varioml.org/xml/1.0")
	private Organism _organism ;
	public void setOrganism( Organism organism) { 
		this._organism = organism ;
	}
	public Organism getOrganism() {
		return this._organism;
	}
 
	// ===========-- strain --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="strain",type=Strain.class,namespace="http://varioml.org/xml/1.0")
	private Strain _strain ;
	public void setStrain( Strain strain) { 
		this._strain = strain ;
	}
	public Strain getStrain() {
		return this._strain;
	}
 
	// ===========-- cultivar --===========
	@javax.xml.bind.annotation.XmlElement(required=false,name="cultivar",type=Cultivar.class,namespace="http://varioml.org/xml/1.0")
	private Cultivar _cultivar ;
	public void setCultivar( Cultivar cultivar) { 
		this._cultivar = cultivar ;
	}
	public Cultivar getCultivar() {
		return this._cultivar;
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
 
	// ===========-- observation --===========
   @com.fasterxml.jackson.annotation.JsonProperty("observations")
   @javax.xml.bind.annotation.XmlElement(required=false,name="observation",type=Observation.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("populations")
   @javax.xml.bind.annotation.XmlElement(required=false,name="population",type=Population.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("variants")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant",type=Variant.class,namespace="http://varioml.org/xml/1.0")
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
   @com.fasterxml.jackson.annotation.JsonProperty("variant_groups")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant_group",type=VariantGroup.class,namespace="http://varioml.org/xml/1.0")
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
	@javax.xml.bind.annotation.XmlElement(required=false,name="source",type=Source.class,namespace="http://varioml.org/xml/1.0")
	private Source _source ;
	public void setSource( Source source) { 
		this._source = source ;
	}
	public Source getSource() {
		return this._source;
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
}