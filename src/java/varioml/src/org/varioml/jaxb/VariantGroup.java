package org.varioml.jaxb;
import java.util.ArrayList;
import java.util.List;

@org.codehaus.jackson.annotate.JsonAutoDetect( fieldVisibility =  org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE, getterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE,setterVisibility= org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE)
@org.codehaus.jackson.map.annotate.JsonSerialize(include = org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL)
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
@javax.xml.bind.annotation.XmlRootElement(namespace="http://varioml.org/xml/1.0",name="variant_group")
@javax.xml.bind.annotation.XmlType(propOrder = {  "_groupType","_variant","_frequency","_pathogenicity","_evidenceCode","_protocolId","_dbXref","_comment"})
@org.codehaus.jackson.annotate.JsonPropertyOrder(value={  "_attr_id","_attr_orientation","_attr_uri","_groupType","_variant","_frequency","_pathogenicity","_evidenceCode","_protocolId","_dbXref","_comment"})


public class VariantGroup /**/ /**/ {
	//xml-element used for code generation: //lsdb/individual/variant_group

	public VariantGroup(  ) {
	}
 
	// ===========-- id --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="id")
	private String _attr_id ;
@Deprecated
	public void setIdAttr( String attr_id) { 
		this._attr_id = attr_id ;
	}
@Deprecated
	public String getIdAttr() { 
		return this._attr_id;
	}
	public void setId( String attr_id) { 
		this._attr_id = attr_id ;
	}
	public String getId() { 
		return this._attr_id;
	}
 
	// ===========-- orientation --===========
	@javax.xml.bind.annotation.XmlAttribute(required=false,name="orientation")
	private String _attr_orientation ;
@Deprecated
	public void setOrientationAttr( String attr_orientation) { 
		this._attr_orientation = attr_orientation ;
	}
@Deprecated
	public String getOrientationAttr() { 
		return this._attr_orientation;
	}
	public void setOrientation( String attr_orientation) { 
		this._attr_orientation = attr_orientation ;
	}
	public String getOrientation() { 
		return this._attr_orientation;
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
 
	// ===========-- group_type --===========
   @javax.xml.bind.annotation.XmlElement(required=false,name="group_type",namespace="http://varioml.org/xml/1.0")
	private GroupType _groupType ;
	public void setGroupType( GroupType groupType) { 
		this._groupType = groupType ;
	}
	public GroupType getGroupType() {
		return this._groupType;
	}
 
	// ===========-- variant --===========
   @org.codehaus.jackson.annotate.JsonProperty("variants")
   @javax.xml.bind.annotation.XmlElement(required=false,name="variant",namespace="http://varioml.org/xml/1.0")
	private List<Variant> _variant ;
	public void setVariantList( List<Variant> variant) { 
		this._variant = variant ;
	}
	public List<Variant> getVariantList()  { 
		return this._variant;
	}
	public void addVariant(Variant item ) { 
		if ( this._variant == null ) { 
			this._variant = new ArrayList<Variant>();
		}
		this._variant.add( item);
	}
 
	// ===========-- frequency --===========
   @org.codehaus.jackson.annotate.JsonProperty("frequencies")
   @javax.xml.bind.annotation.XmlElement(required=false,name="frequency",namespace="http://varioml.org/xml/1.0")
	private List<Frequency> _frequency ;
	public void setFrequencyList( List<Frequency> frequency) { 
		this._frequency = frequency ;
	}
	public List<Frequency> getFrequencyList()  { 
		return this._frequency;
	}
	public void addFrequency(Frequency item ) { 
		if ( this._frequency == null ) { 
			this._frequency = new ArrayList<Frequency>();
		}
		this._frequency.add( item);
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
