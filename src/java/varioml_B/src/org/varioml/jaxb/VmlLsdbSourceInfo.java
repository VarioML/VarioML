package org.varioml.jaxb;

import java.util.*;
public interface VmlLsdbSourceInfo  {

	public  void setSeqRegionList( List<SeqRegion> seqRegion)  ; 
	public  List<SeqRegion> getSeqRegionList()   ; 
	public  void addSeqRegion(SeqRegion item )  ; 
	public  void setVariantTypeList( List<VariantType> variantType)  ; 
	public  List<VariantType> getVariantTypeList()   ; 
	public  void addVariantType(VariantType item )  ; 
	public  void setVariantClassList( List<VariantClass> variantClass)  ; 
	public  List<VariantClass> getVariantClassList()   ; 
	public  void addVariantClass(VariantClass item )  ; 
	public  void setOriginalId( OriginalId originalId)  ; 
	public  OriginalId getOriginalId()  ; 
	public  void setVariantDetection( VariantDetection variantDetection)  ; 
	public  VariantDetection getVariantDetection()  ; 


}
