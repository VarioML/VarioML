package org.varioml.jaxb;

import java.util.*;
public interface VmlObservationTarget extends VmlIdentifiable, VmlAnnotatable {

	public void setOriginalId( OriginalId originalId);
	public OriginalId getOriginalId() ;
	public void setRole( Role role) ;
	public Role getRole() ;
	public void setRelationshipList( List<Relationship> relationship) ;
	public List<Relationship> getRelationshipList();  
	public void addRelationship(Relationship item ) ;
	public void setOrganism( Organism organism) ;
	public Organism getOrganism() ;
	public void setStrain( Strain strain) ;
	public Strain getStrain() ;
	public void setCultivar( Cultivar cultivar) ;
	public Cultivar getCultivar() ;
	public void setPhenotypeList( List<Phenotype> phenotype) ;
	public List<Phenotype> getPhenotypeList() ; 
	public void addPhenotype(Phenotype item ) ;
	public void setPopulationList( List<Population> population) ;
	public List<Population> getPopulationList() ;
	public void addPopulation(Population item ) ;
	public void setVariantList( List<Variant> variant) ;
	public List<Variant> getVariantList() ; 
	public void addVariant(Variant item ) ;
	public void setVariantGroupList( List<VariantGroup> variantGroup) ;
	public List<VariantGroup> getVariantGroupList()  ;
	public void addVariantGroup(VariantGroup item ) ;


}
