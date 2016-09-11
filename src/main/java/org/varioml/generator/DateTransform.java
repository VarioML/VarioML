package org.varioml.generator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.*;
import org.joda.time.format.*;

import org.simpleframework.xml.transform.Transform;
import org.varioml.util.Util;

public class DateTransform implements Transform<VarioDate>	 {


    private static final DateFormat XML_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat XML_YEAR_FORMAT = new SimpleDateFormat("yyyy");
    private static final DateFormat XML_YEAR_MON_FORMAT = new SimpleDateFormat("yyyy-MM");

	@Override
	public VarioDate read(String date) throws Exception {

		try {
			if (date.length() == 4) {
				XML_YEAR_FORMAT.parse(date);
			}
			else if (date.length() == 7) {
				XML_YEAR_MON_FORMAT.parse(date);
			}
			else if (date.length() == 10) {
				XML_DATE_FORMAT.parse(date);
			} else {

				throw new Exception("wrong format");
				
			}
		} catch (Exception e) {
			Util.log(DateTransform.class, "Invalid date date: "+date+" "+e.getMessage());
			throw e;
		}
		return new VarioDate(date);
	}

	@Override
	public String write(VarioDate _date) throws Exception {

		String date = _date.toString();
		//TODO fix copy pate code
		try {
			if (date.length() == 4) {
				XML_YEAR_FORMAT.parse(date);
			}
			else if (date.length() == 7) {
				XML_YEAR_MON_FORMAT.parse(date);
			}
			else if (date.length() == 10) {
				XML_DATE_FORMAT.parse(date);
			} else {

				throw new Exception("wrong format");
				
			}
		} catch (Exception e) {
			Util.log(DateTransform.class, "Invalid date date: "+date+" "+e.getMessage());
			throw e;
		}
		return date;

	}

	
	public static void main(String[] args) throws Exception {
		
		DateTransform tf = new DateTransform();
		System.out.println(tf.read("2006-05-04"));
		System.out.println(tf.read("2006-05"));
		System.out.println(tf.read("2006"));
		System.out.println(tf.read("20"));
	}
}
