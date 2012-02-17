package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="pathogenicity")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_phenotype","_pathogenicity","_description","_evidenceCode","_protocolId","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_accession","_attr_panelRef","_attr_scope","_attr_source","_attr_term","_attr_uri","_phenotype","_pathogenicity","_description","_evidenceCode","_protocolId","_dbXref","_comment"})


public class Pathogenicity /**/implements VmlSimpleObservation /**/ {
	//xml-element used for code generation: //lsdb/individual/variant/pathogenicity

	public Pathogenicity(  ) {
	}
 
	// ===========-- accession --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="accession")
	private String _attr_accession ;
@Deprecated
	public void setAccessionAttr( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
@Deprecated
	public String getAccessionAttr() { 
		return this._attr_accession;
	}
	public void setAccession( String attr_accession) { 
		this._attr_accession = attr_accession ;
	}
	public String getAccession() { 
		return this._attr_accession;
	}
 
	// ===========-- panel_ref --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="panel_ref")
	private String _attr_panelRef ;
@Deprecated
	public void setPanelRefAttr( String attr_panelRef) { 
		this._attr_panelRef = attr_panelRef ;
	}
@Deprecated
	public String getPanelRefAttr() { 
		return this._attr_panelRef;
	}
	public void setPanelRef( String attr_panelRef) { 
		this._attr_panelRef = attr_panelRef ;
	}
	public String getPanelRef() { 
		return this._attr_panelRef;
	}
 
	// ===========-- scope --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="scope")
	private String _attr_scope ;
@Deprecated
	public void setScopeAttr( String attr_scope) { 
		this._attr_scope = attr_scope ;
	}
@Deprecated
	public String getScopeAttr() { 
		return this._attr_scope;
	}
	public void setScope( String attr_scope) { 
		this._attr_scope = attr_scope ;
	}
	public String getScope() { 
		return this._attr_scope;
	}
 
	// ===========-- source --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="source")
	private String _attr_source ;
@Deprecated
	public void setSourceAttr( String attr_source) { 
		this._attr_source = attr_source ;
	}
@Deprecated
	public String getSourceAttr() { 
		return this._attr_source;
	}
	public void setSource( String attr_source) { 
		this._attr_source = attr_source ;
	}
	public String getSource() { 
		return this._attr_source;
	}
 
	// ===========-- term --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="term")
	private String _attr_term ;
@Deprecated
	public void setTermAttr( String attr_term) { 
		this._attr_term = attr_term ;
	}
@Deprecated
	public String getTermAttr() { 
		return this._attr_term;
	}
	public void setTerm( String attr_term) { 
		this._attr_term = attr_term ;
	}
	public String getTerm() { 
		return this._attr_term;
	}
 
	// ===========-- uri --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="uri")
	private String _attr_uri ;
@Deprecated
	public void setUriAttr( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
@Deprecated
	public String getUriAttr() { 
		return this._attr_uri;
	}
	public void setUri( String attr_uri) { 
		this._attr_uri = attr_uri ;
	}
	public String getUri() { 
		return this._attr_uri;
	}
 
	// ===========-- phenotype --===========
   @org.codehaus.jackson.annotate.JsonProperty("phenotypes")
   @javax.xml.bind.annotation.XmlElement(required=false,name="phenotype",namespace="http://varioml.org/xml/1.0")
	private List<Phenotype> _phenotype ;
	public void setPhenotypeList( List<Phenotype> phenotype) { 
		this._phenotype = phenotype ;
	}
	public List<Phenotype> getPhenotypeList()  { 
		return this._phenotype;
	}
	public void addPhenotype(Phenotype item ) { 
		if ( this._phenotype == null ) { 
			this._phenotype = new ArrayList<Phenotype>();
		}
		this._phenotype.add( item);
	}
 
	// ===========-- pathogenicity --===========
   @org.codehaus.jackson.annotate.JsonProperty("pathogenicities")
   @javax.xml.bind.annotation.XmlElement(required=false,name="pathogenicity",namespace="http://varioml.org/xml/1.0")
	private List<Pathogenicity> _pathogenicity ;
	public void setPathogenicityList( List<Pathogenicity> pathogenicity) { 
		this._pathogenicity = pathogenicity ;
	}
	public List<Pathogenicity> getPathogenicityList()  { 
		return this._pathogenicity;
	}
	public void addPathogenicity(Pathogenicity item ) { 
		if ( this._pathogenicity == null ) { 
			this._pathogenicity = new ArrayList<Pathogenicity>();
		}
		this._pathogenicity.add( item);
	}
 
	// ===========-- description --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="description",namespace="http://varioml.org/xml/1.0")
	private String _description ;
	public void setDescription( String description) { 
		this._description = description ;
	}
	public String getDescription() {
		return this._description;
	}
 
	// ===========-- evidence_code --===========
   @org.codehaus.jackson.annotate.JsonProperty("evidence_codes")
   @javax.xml.bind.annotation.XmlElement(required=false,name="evidence_code",namespace="http://varioml.org/xml/1.0")
	private List<EvidenceCode> _evidenceCode ;
	public void setEvidenceCodeList( List<EvidenceCode> evidenceCode) { 
		this._evidenceCode = evidenceCode ;
	}
	public List<EvidenceCode> getEvidenceCodeList()  { 
		return this._evidenceCode;
	}
	public void addEvidenceCode(EvidenceCode item ) { 
		if ( this._evidenceCode == null ) { 
			this._evidenceCode = new ArrayList<EvidenceCode>();
		}
		this._evidenceCode.add( item);
	}
 
	// ===========-- protocol_id --===========
   @org.codehaus.jackson.annotate.JsonProperty("protocol_ids")
   @javax.xml.bind.annotation.XmlElement(required=false,name="protocol_id",namespace="http://varioml.org/xml/1.0")
	private List<ProtocolId> _protocolId ;
	public void setProtocolIdList( List<ProtocolId> protocolId) { 
		this._protocolId = protocolId ;
	}
	public List<ProtocolId> getProtocolIdList()  { 
		return this._protocolId;
	}
	public void addProtocolId(ProtocolId item ) { 
		if ( this._protocolId == null ) { 
			this._protocolId = new ArrayList<ProtocolId>();
		}
		this._protocolId.add( item);
	}
 
	// ===========-- db_xref --===========
   @org.codehaus.jackson.annotate.JsonProperty("db_xrefs")
   @javax.xml.bind.annotation.XmlElement(required=false,name="db_xref",namespace="http://varioml.org/xml/1.0")
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
   @org.codehaus.jackson.annotate.JsonProperty("comments")
   @javax.xml.bind.annotation.XmlElement(required=false,name="comment",namespace="http://varioml.org/xml/1.0")
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