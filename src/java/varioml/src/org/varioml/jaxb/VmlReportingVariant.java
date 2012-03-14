package org.varioml.jaxb;

import java.util.*;
public interface VmlReportingVariant extends VmlSimpleVariantEvent,VmlVariantEvent {

	public  void setType( String attr_type)  ; 
	public  String getType()  ; 
	public  void setRefSeq( RefSeq refSeq)  ; 
	public  RefSeq getRefSeq()  ; 
	public  void setSequence( Sequence sequence)  ; 
	public  Sequence getSequence()  ; 
	public  void setGenotype( Genotype genotype)  ; 
	public  Genotype getGenotype()  ; 
	public  void setTissueDistribution( TissueDistribution tissueDistribution)  ; 
	public  TissueDistribution getTissueDistribution()  ; 


}
