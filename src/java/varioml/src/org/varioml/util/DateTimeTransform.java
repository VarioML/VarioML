package org.varioml.util;

import org.joda.time.*;
import org.joda.time.format.*;

import org.simpleframework.xml.transform.Transform;

public class DateTimeTransform implements Transform<VarioDateTime> {

	public static final DateTimeFormatter XML_DATE_TIME_FORMAT = ISODateTimeFormat
			.dateTime();

	@Override
	public VarioDateTime read(String date) throws Exception {
		return new VarioDateTime(date);
	}

	@Override
	public String write(VarioDateTime _date) throws Exception {

		//TODO clean
		String date = _date.toString(); 
		try {
			DateTime dt = XML_DATE_TIME_FORMAT.parseDateTime(date);
		} catch (Exception e) {
			Util.log(DateTransform.class, "Invalid date date: " + date + " "
					+ e.getMessage());
			throw e;
		}
		return date;

	}

	public static void main(String[] args) throws Exception {

		DateTimeTransform tf = new DateTimeTransform();
		System.out.println(tf.read("2006-05-04T18:13:51.0Z"));
	}
}
