package org.varioml.data;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class EvidenceOntologyTerm extends OntologyTerm {

	@ElementList(inline=true,required=false)
	private List<OntologyTerm> evidence_code;

	@ElementList(inline=true,required=false)
	private List<DbXref> protprotocolId;


	
}
