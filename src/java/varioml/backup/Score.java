package org.varioml.data;


import org.simpleframework.xml.Attribute;

public class Score  extends AnnotatedElement {

	@Attribute
	private float value ;

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
}
