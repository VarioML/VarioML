package org.varioml.jaxb;

import java.util.*;
public interface VmlAnnotatable  {

	public List<Comment> getCommentList() ;
   public List<DbXref>  getDbXrefList()  ;
   public void setDbXrefList( List<DbXref> dbXref) ; 
   public void setCommentList( List<Comment> comment) ; 
   public void addDbXref(DbXref item ) ; 
   public void addComment(Comment item ) ; 


}
