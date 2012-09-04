package org.varioml.jaxb;

import java.util.*;
public interface VmlShareable  {

	public void setSharingPolicy( SharingPolicy sharingPolicy) ;
	public SharingPolicy getSharingPolicy() ;
	public void setCreationDate( org.varioml.util.VMLDate creationDate) ;
	public org.varioml.util.VMLDate getCreationDate() ;
	public void setModificationDate( org.varioml.util.VMLDate modificationDate) ;
	public org.varioml.util.VMLDate getModificationDate() ;


}
