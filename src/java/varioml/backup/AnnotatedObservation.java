package org.varioml.data;

import java.util.ArrayList;
import java.util.List;

public class AnnotatedObservation extends AnnotatedElement {
	
	private  List<DbXref> protocolId ;

	public List<DbXref> getProtocolId() {
		return protocolId;
	}
	
	public void setProtocolId(List<DbXref> protocolId) {
		this.protocolId = protocolId;
	}
	

	public void addProtocolId(DbXref protocolId) {
		if (protocolId == null) {
			this.protocolId = new ArrayList<DbXref>();
		}
		this.protocolId.add(protocolId);
	}
	
}
