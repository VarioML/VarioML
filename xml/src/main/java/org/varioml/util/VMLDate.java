package org.varioml.util;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@javax.xml.bind.annotation.XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
public class VMLDate {

	//TODO implement... we need accuracy here because date can be incomplete (year or year-month only)
	
	@XmlValue
	private String date ;
	public VMLDate() {
		date = null;
	}
	public VMLDate(String date) {
		super();
		this.date = date;
	}
	
	@Override
	public String toString() {
		return date;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	
}
