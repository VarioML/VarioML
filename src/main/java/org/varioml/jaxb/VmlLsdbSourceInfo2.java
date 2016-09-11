package org.varioml.jaxb;

import java.util.*;
public interface VmlLsdbSourceInfo2  {

	public  void setVariantClassList( List<VariantClass> variantClass)  ; 
	public  List<VariantClass> getVariantClassList()   ; 
	public  void addVariantClass(VariantClass item )  ; 
	public  void setExonList( List<Exon> exon)  ; 
	public  List<Exon> getExonList()   ; 
	public  void addExon(Exon item )  ; 
	public  void setRestrictionSite( RestrictionSite restrictionSite)  ; 
	public  RestrictionSite getRestrictionSite()  ; 
	public  void setGeneticOriginList( List<GeneticOrigin> geneticOrigin)  ; 
	public  List<GeneticOrigin> getGeneticOriginList()   ; 
	public  void addGeneticOrigin(GeneticOrigin item )  ; 


}
