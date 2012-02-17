package org.varioml.jaxb;

import java.util.*;
public interface VmlShareable  {

	public void setSharingPolicy( SharingPolicy sharingPolicy) ;
	public SharingPolicy getSharingPolicy() ;
	public void setCreationDate( org.varioml.util.JAXBVarioDate creationDate) ;
	public org.varioml.util.JAXBVarioDate getCreationDate() ;
	public void setModificationDate( org.varioml.util.JAXBVarioDate modificationDate) ;
	public org.varioml.util.JAXBVarioDate getModificationDate() ;


}
