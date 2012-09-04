package test;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.varioml.basictypes.*;

@Order(elements{"Text","EvidenceCode","ProtocolId","DbXref","Comment"})
public class Comment {
 
	// ===========-- accession --===========
	@Attribute(required=false) //accession
	private String accession ;
	public void setAccession( String accession) { 
		this.accession=accession ;
	}
	public String getAccession()  
		return this.accession;
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
 
	// ===========-- term --===========
	@Attribute(required=false) //term
	private String term ;
	public void setTerm( String term) { 
		this.term=term ;
	}
	public String getTerm()  
		return this.term;
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
 
	// ===========-- text --===========
	@Element(required=false) //text
	private Text text ;
	public void setText( Text text) { 
		this.text=text ;
	}
	public Text getText()  
		return this.text;
	}
 
	// ===========-- evidence_code --===========
	@ElementList(required=false) //evidence_code
	private List<EvidenceCode> evidenceCode ;
	public void setEvidenceCodeList( List<EvidenceCode> evidenceCode) { 
		this.evidenceCode=evidenceCode ;
	}
	public List<EvidenceCode> getEvidenceCodeList()  { 
		return this.evidenceCode;
	}
	public void addEvidenceCode(EvidenceCode item ) { 
		if ( this.evidenceCode == null ) { 
			this.evidenceCode = new ArrayList<EvidenceCode>();
		}
		this.evidenceCode.add( item);
	}
 
	// ===========-- protocol_id --===========
	@ElementList(required=false) //protocol_id
	private List<DbXref> protocolId ;
	public void setProtocolIdList( List<DbXref> protocolId) { 
		this.protocolId=protocolId ;
	}
	public List<DbXref> getProtocolIdList()  { 
		return this.protocolId;
	}
	public void addProtocolId(DbXref item ) { 
		if ( this.protocolId == null ) { 
			this.protocolId = new ArrayList<DbXref>();
		}
		this.protocolId.add( item);
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
