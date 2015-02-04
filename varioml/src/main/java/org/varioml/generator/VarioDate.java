package org.varioml.generator;

public class VarioDate {

	//TODO implement... we need accuracy here because date can be incomplete (year or year-month only)
	private String date ;
	public VarioDate() {
		date = null;
	}
	public VarioDate(String date) {
		super();
		this.date = date;
	}
	
	@Override
	public String toString() {
		return date;
	}
		
	
}
