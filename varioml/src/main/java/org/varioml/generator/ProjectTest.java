package org.varioml.generator;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectTest {

	private String name ;
	@XmlElement(name="db_xref",required=false) 
	private List<DbXRef> dbXRef ;
	
	
	@XmlRootElement(name="xyz")
	static class DbXRef { 
		private String uri;

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DbXRef> getDbXRef() {
		return dbXRef;
	}
	public void setDbXRef(List<String> dbXRef) {
		dbXRef = dbXRef;
	}

	public void addDbXRef( String uri) {
		DbXRef _xref = new DbXRef();
		_xref.setUri(uri);
		addDbXRef( _xref);
	}
	public void addDbXRef( DbXRef xref) {
		if ( dbXRef == null) {
			dbXRef = new ArrayList<DbXRef>();
		}
		dbXRef.add(xref) ;
	}
}
