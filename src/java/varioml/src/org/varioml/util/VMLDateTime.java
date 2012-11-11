package org.varioml.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.varioml.generator.DateTransform;

public class VMLDateTime extends VMLDate{

	public static final DateTimeFormatter XML_DATE_TIME_FORMAT = ISODateTimeFormat
	.dateTime();

	private DateTime dateTime;

	public DateTime getDateTime() {
		return dateTime ;
	}

	public void setDateTime( String dateTime) {
		try {
			this.dateTime = XML_DATE_TIME_FORMAT.parseDateTime(dateTime);
			setDate(dateTime);
		} catch (Exception e) {
			Util.log(DateTransform.class, "Invalid date date: " + dateTime + " "
					+ e.getMessage());
		}
	}

}
