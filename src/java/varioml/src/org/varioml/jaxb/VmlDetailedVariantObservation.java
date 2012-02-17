package org.varioml.jaxb;

import java.util.*;
public interface VmlDetailedVariantObservation extends VmlVariantObservation {

	public void setVariantTypeList( List<VariantType> variantType) ;
	public List<VariantType> getVariantTypeList() ;
	public void addVariantType(VariantType item ) ;
	public void setVariantClassList( List<VariantClass> variantClass) ;
	public List<VariantClass> getVariantClassList() ;
	public void addVariantClass(VariantClass item ) ;
	public void setOriginalId( OriginalId originalId) ;
	public OriginalId getOriginalId() ;
	public void setExonList( List<Exon> exon) ;
	public List<Exon> getExonList() ;
	public void addExon(Exon item ) ;
	public void setVariantDetection( VariantDetection variantDetection) ;
	public VariantDetection getVariantDetection() ;
	public void setRestrictionSite( RestrictionSite restrictionSite) ;
	public RestrictionSite getRestrictionSite() ;
	public void setGeneticOriginList( List<GeneticOrigin> geneticOrigin) ;
	public List<GeneticOrigin> getGeneticOriginList() ;
	public void addGeneticOrigin(GeneticOrigin item ) ;


}
