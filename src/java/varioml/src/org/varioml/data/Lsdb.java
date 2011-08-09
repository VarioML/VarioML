package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

@Root(strict=true)
@NamespaceList({
@Namespace(reference="http://varioml.org/xml/1.0"),
@Namespace(reference="http://www.w3.org/2001/XMLSchema-instance",prefix="xsi")
})

@org.simpleframework.xml.Order(elements={"created","source","individual","db_xref","comment"})
public class Lsdb {
	//xml-element used for code generation: //lsdb

	public Lsdb(  ) {
	}
 
	// ===========-- id --===========
	@org.simpleframework.xml.Attribute(required=false,name="id")
	private String _attr_id ;
	public void setIdAttr( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getIdAttr() { 
		return this._attr_id;
	}
 
	// ===========-- schema_version --===========
	@org.simpleframework.xml.Attribute(required=false,name="schema_version")
	private float _attr_schemaVersion ;
	public void setSchemaVersionAttr( float attr_schemaVersion) { 
		this._attr_schemaVersion = attr_schemaVersion ;
	}
	public float getSchemaVersionAttr() { 
		return this._attr_schemaVersion;
	}
 
	// ===========-- submissionid_type --===========
	@org.simpleframework.xml.Attribute(required=false,name="submissionid_type")
	private String _attr_submissionidType ;
	public void setSubmissionidTypeAttr( String attr_submissionidType) { 
		this._attr_submissionidType = attr_submissionidType ;
	}
	public String getSubmissionidTypeAttr() { 
		return this._attr_submissionidType;
	}
 
	// ===========-- uri --===========
	@org.simpleframework.xml.Attribute(required=false,name="uri")
	private String _attr_uri ;
	public void setUriAttr( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUriAttr() { 
		return this._attr_uri;
	}
 
	// ===========-- xmlns --===========
	@org.simpleframework.xml.Attribute(required=false,name="xmlns")
	private String _attr_xmlns ;
	public void setXmlnsAttr( String attr_xmlns) { 
		this._attr_xmlns = attr_xmlns ;
	}
	public String getXmlnsAttr() { 
		return this._attr_xmlns;
	}
 
	// ===========-- xmlns:xsi --===========
	@org.simpleframework.xml.Attribute(required=false,name="xmlns:xsi")
	private String _attr_xmlnsXsi ;
	public void setXmlnsXsiAttr( String attr_xmlnsXsi) { 
		this._attr_xmlnsXsi = attr_xmlnsXsi ;
	}
	public String getXmlnsXsiAttr() { 
		return this._attr_xmlnsXsi;
	}
 
	// ===========-- created --===========
	@org.simpleframework.xml.Element(required=false,name="created") 
	private org.varioml.util.VarioDateTime _created ;
	public void setCreated( org.varioml.util.VarioDateTime created) { 
		this._created = created ;
	}
	public org.varioml.util.VarioDateTime getCreated() {
		return this._created;
	}
 
	// ===========-- source --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="source") 
	private List<Source> _source ;
	public void setSourceList( List<Source> source) { 
		this._source = source ;
	}
	public List<Source> getSourceList()  { 
		return this._source;
	}
	public void addSource(Source item ) { 
		if ( this._source == null ) { 
			this._source = new ArrayList<Source>();
		}
		this._source.add( item);
	}
 
	// ===========-- individual --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="individual") 
	private List<Individual> _individual ;
	public void setIndividualList( List<Individual> individual) { 
		this._individual = individual ;
	}
	public List<Individual> getIndividualList()  { 
		return this._individual;
	}
	public void addIndividual(Individual item ) { 
		if ( this._individual == null ) { 
			this._individual = new ArrayList<Individual>();
		}
		this._individual.add( item);
	}
 
	// ===========-- db_xref --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="db_xref") 
	private List<DbXref> _dbXref ;
	public void setDbXrefList( List<DbXref> dbXref) { 
		this._dbXref = dbXref ;
	}
	public List<DbXref> getDbXrefList()  { 
		return this._dbXref;
	}
	public void addDbXref(DbXref item ) { 
		if ( this._dbXref == null ) { 
			this._dbXref = new ArrayList<DbXref>();
		}
		this._dbXref.add( item);
	}
 
	// ===========-- comment --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="comment") 
	private List<Comment> _comment ;
	public void setCommentList( List<Comment> comment) { 
		this._comment = comment ;
	}
	public List<Comment> getCommentList()  { 
		return this._comment;
	}
	public void addComment(Comment item ) { 
		if ( this._comment == null ) { 
			this._comment = new ArrayList<Comment>();
		}
		this._comment.add( item);
	}
}
