package org.varioml.generator;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.varioml.util.Util;

public class VarioDateTime extends VarioDate{

	public static final DateTimeFormatter XML_DATE_TIME_FORMAT = ISODateTimeFormat
	.dateTime();

	private DateTime dateTime;
	public VarioDateTime(String date) throws Exception  {

		super(date);

		try {
			dateTime = XML_DATE_TIME_FORMAT.parseDateTime(date);
		} catch (Exception e) {
			Util.log(DateTransform.class, "Invalid date date: " + date + " "
					+ e.getMessage());
		}
		// TODO Auto-generated constructor stub
	}

	public DateTime getDateTime() {
		return dateTime ;
	}
}
