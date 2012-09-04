package org.varioml.util;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateUtils {
  public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

  public static String now() {
	  return now(DATE_FORMAT_NOW);
  }
  public static String now( String format) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(cal.getTime());

  }

  public static void  main(String arg[]) {
    System.out.println("Now : " + DateUtils.now());
    
    System.out.println(DateUtils.now("dd MMMMM yyyy"));
    System.out.println(DateUtils.now("yyyyMMdd"));
    System.out.println(DateUtils.now("dd.MM.yy"));
    System.out.println(DateUtils.now("MM/dd/yy"));
    System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
    System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
    System.out.println(DateUtils.now("h:mm a"));
    System.out.println(DateUtils.now("H:mm:ss:SSS"));
    System.out.println(DateUtils.now("K:mm a,z"));
    System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
  }
}