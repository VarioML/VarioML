package org.varioml.data;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Lsdb {

	@ElementList(inline=true)
	private List<Source> source;

	public List<Source> getSource() {
		return source;
	}

	public void setSource(List<Source> source) {
		this.source = source;
	}
	
	public void addSource( Source src)  { 
		if ( source == null ) {
			source = new  ArrayList<Source>();
		}
		source.add(src);
	}
	

}
