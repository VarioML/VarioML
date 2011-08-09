package org.varioml.data;
import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.Root;

@Root(strict=true)

@org.simpleframework.xml.Order(elements={"population","freq","evidence_code","protocol_id","db_xref","comment"})
public class Frequency {
	//xml-element used for code generation: //lsdb/individual/variant/frequency

	public Frequency(  ) {
	}
 
	// ===========-- samples --===========
	@org.simpleframework.xml.Attribute(required=false,name="samples")
	private int _attr_samples ;
	public void setSamplesAttr( int attr_samples) { 
		this._attr_samples = attr_samples ;
	}
	public int getSamplesAttr() { 
		return this._attr_samples;
	}
 
	// ===========-- type --===========
	@org.simpleframework.xml.Attribute(required=false,name="type")
	private String _attr_type ;
	public void setTypeAttr( String attr_type) { 
		this._attr_type = attr_type ;
	}
	public String getTypeAttr() { 
		return this._attr_type;
	}
 
	// ===========-- population --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="population") 
	private List<Population> _population ;
	public void setPopulationList( List<Population> population) { 
		this._population = population ;
	}
	public List<Population> getPopulationList()  { 
		return this._population;
	}
	public void addPopulation(Population item ) { 
		if ( this._population == null ) { 
			this._population = new ArrayList<Population>();
		}
		this._population.add( item);
	}
 
	// ===========-- freq --===========
	@org.simpleframework.xml.Element(required=false,name="freq") 
	private double _freq ;
	public void setFreq( double freq) { 
		this._freq = freq ;
	}
	public double getFreq() {
		return this._freq;
	}
 
	// ===========-- evidence_code --===========
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="evidence_code") 
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
	@org.simpleframework.xml.ElementList(required=false,inline=true,entry="protocol_id") 
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
