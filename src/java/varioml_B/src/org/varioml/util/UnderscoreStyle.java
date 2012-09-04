package org.varioml.util;

import org.simpleframework.xml.stream.Style;

public class UnderscoreStyle implements Style {

	@Override
	public String getAttribute(String arg0) {
		if ( arg0 == null ) return null;
		StringBuffer bf = new StringBuffer();
		char[] arr= arg0.toCharArray();
		boolean notChanged = true;
		for (int i = 0; i < arr.length; i++) {
			if ( arr[i] >= 'A' && arr[i]<='Z' ) { 
				if ( notChanged && i>0) bf.append('_');
				bf.append( Character.toLowerCase( arr[i]));
				notChanged = false;
				//E.g. dbXRef => db_xref
			} else {
				bf.append(arr[i]);
				notChanged = true;
			}
		}
		return bf.toString();
	}

	@Override
	public String getElement(String arg0) {
		return getAttribute(arg0);
	}

	
}
