package org.varioml.jaxb;

import java.util.*;
public interface VmlVariantObservation extends VmlAnnotatable,VmlObservable,VmlIdentifiable {

   public void setName( VariantName name) ; 
   public VariantName getName() ; 
   public void setSequence( Sequence sequence) ; 
   public Sequence getSequence() ; 
   public void setConsequenceList( List<Consequence> consequence) ;  
   public List<Consequence> getConsequenceList()  ;  
   public void addConsequence(Consequence item ) ;  
   public void setPathogenicityList( List<Pathogenicity> pathogenicity) ;  
   public List<Pathogenicity> getPathogenicityList()  ;  
   public void addPathogenicity(Pathogenicity item ) ;  
   public void setSeqChanges( SeqChanges seqChanges) ;  
   public SeqChanges getSeqChanges() ; 
   public void setAliases( Aliases aliases) ;  
   public Aliases getAliases() ; 
   public void setLocationList( List<Location> location) ;  
   public List<Location> getLocationList()  ;  
   public void addLocation(Location item ) ;  


}
