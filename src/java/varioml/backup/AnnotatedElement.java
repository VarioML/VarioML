package org.varioml.data;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;

public class AnnotatedElement {

	@ElementList(inline=true,required=false)
	private List<DbXref> dbXref;
	
	@ElementList(inline=true,required=false)
	private List<Comment> comment;

	public List<DbXref> getDbXref() {
		return dbXref;
	}

	public void setDbXref(List<DbXref> dbXref) {
		this.dbXref = dbXref;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public void addComment(Comment comment) {
		if (comment == null) {
			this.comment = new ArrayList<Comment>();
		}
		this.comment.add(comment);
	}
	
	public void addDbXRef(DbXref dbXref) {
		if (dbXref == null) {
			this.dbXref = new ArrayList<DbXref>();
		}
		this.dbXref.add(dbXref);
	}
}
