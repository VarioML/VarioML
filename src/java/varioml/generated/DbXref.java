package test;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.varioml.basictypes.*;

@Order(elements{"DbXref","Comment"})
public class DbXref {
 
	// ===========-- accession --===========
	@Attribute(required=false) //accession
	private String accession ;
	public void setAccession( String accession) { 
		this.accession=accession ;
	}
	public String getAccession()  
		return this.accession;
	}
 
	// ===========-- name --===========
	@Attribute(required=false) //name
	private String name ;
	public void setName( String name) { 
		this.name=name ;
	}
	public String getName()  
		return this.name;
	}
 
	// ===========-- source --===========
	@Attribute(required=false) //source
	private String source ;
	public void setSource( String source) { 
		this.source=source ;
	}
	public String getSource()  
		return this.source;
	}
 
	// ===========-- uri --===========
	@Attribute(required=false) //uri
	private String uri ;
	public void setUri( String uri) { 
		this.uri=uri ;
	}
	public String getUri()  
		return this.uri;
	}
 
	// ===========-- db_xref --===========
	@ElementList(required=false) //db_xref
	private List<DbXref> dbXref ;
	public void setDbXrefList( List<DbXref> dbXref) { 
		this.dbXref=dbXref ;
	}
	public List<DbXref> getDbXrefList()  { 
		return this.dbXref;
	}
	public void addDbXref(DbXref item ) { 
		if ( this.dbXref == null ) { 
			this.dbXref = new ArrayList<DbXref>();
		}
		this.dbXref.add( item);
	}
 
	// ===========-- comment --===========
	@ElementList(required=false) //comment
	private List<Comment> comment ;
	public void setCommentList( List<Comment> comment) { 
		this.comment=comment ;
	}
	public List<Comment> getCommentList()  { 
		return this.comment;
	}
	public void addComment(Comment item ) { 
		if ( this.comment == null ) { 
			this.comment = new ArrayList<Comment>();
		}
		this.comment.add( item);
	}
