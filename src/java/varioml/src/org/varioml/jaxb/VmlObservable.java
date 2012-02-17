package org.varioml.jaxb;

import java.util.*;
public interface VmlObservable extends VmlAnnotatable {

	public void setEvidenceCodeList( List<EvidenceCode> evidenceCode) ;  
	public List<EvidenceCode> getEvidenceCodeList()  ;  
	public void addEvidenceCode(EvidenceCode item ) ;  
	public void setProtocolIdList( List<ProtocolId> protocolId) ;  
	public List<ProtocolId> getProtocolIdList()  ;  
	public void addProtocolId(ProtocolId item ) ;  


}
