	package test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.simpleframework.xml.Serializer;
import org.varioml.simplexml.DbXref;
import org.varioml.simplexml.Lsdb;
import org.varioml.simplexml.Source;
import org.varioml.simplexml.VariantGroup;
import org.varioml.util.Util;
import org.varioml.util.VarioDateTime;





public class LsdbTest extends TestCase  {


	private String fileName = "lsdb_test_all.xml" ;
	
	public void testParser() throws Exception { 

		final Serializer ser = Util.createSerializer();
		final File file = Util.findFile(fileName) ;
	
		Lsdb lsdb = ser.read(Lsdb.class, file);
		List<Source> src = lsdb.getSourceList();
		for (Source source : src) {
			System.err.println(source.getName());
		}
		int len1 = src.size();

		final File fileOut = new File("test_000.xml");

		ser.write(lsdb, fileOut);
		
		Lsdb lsdb2 = ser.read(Lsdb.class, fileOut);
		src = lsdb2.getSourceList();
		int len2 = src.size();
		for (Source source : src) {
			System.err.println(source.getName());
		}
		
		assertTrue(len1 == len2);
		
	}


	public void testParserNew1() throws Exception { 

		final Serializer ser = Util.createSerializer();
		final File file = Util.findFile("new_variant.xml") ;
	
		VariantGroup lsdb = ser.read(VariantGroup.class, file);

		final File fileOut = new File("test_new.xml");

		ser.write(lsdb, fileOut);
		
		
	}

	public void testParserNew2() throws Exception { 

		final Serializer ser = Util.createSerializer();
		final File file = Util.findFile("lsdb_test_all_new.xml") ;
		Lsdb lsdb = ser.read(Lsdb.class, file);
		List<Source> src = lsdb.getSourceList();


		final File fileOut = new File("test_new.xml");

		ser.write(lsdb, fileOut);
		
		
	}

	public void testWriter() throws Exception {

		final Serializer ser = Util.createSerializer();
		final File file = new File("test_001.xml");
		Lsdb lsdb = new Lsdb() ;
		//lsdb.setText("KOE");
		Source src= new Source() ;
		DbXref ref = new DbXref();
		ref.setAccessionAttr("abcd");
		src.addDbXref(ref);
		src.setName("Test");
		//Url url = new Url("http://test1.com");
		String url = "http://test.com";
		src.addUrl(url);
		src.addUrl(url);  
		src.addUrl(url);
		lsdb.setCreated( new VarioDateTime("2006-05-04T18:13:51.0Z"));
		lsdb.addSource(src);
		ser.write(lsdb, file);
		

		
	}
		
		
	public void testJAXB() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb)util.readXML("lsdb.xsd", "lsdb_test_all.xml",org.varioml.jaxb.Lsdb.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("lsdb.json", o);
		
	}

	public void testCafeVariomeJAXBJSON() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.CafeVariome o =  (org.varioml.jaxb.CafeVariome)util.readXML("cafe_variome.xsd", "cafe_variome.xml",org.varioml.jaxb.CafeVariome.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("cafe_variome.json", o);
		
	}

	public void testCafeVariomeJAXBJSON2() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb)util.readXML("lsdb.xsd", "templates/lsdb_19.2.2012.xml",org.varioml.jaxb.Lsdb.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("lsdb_full.json", o);
		
	}

	public void testCafeVariomeJAXBJSON3() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.CafeVariome o =  (org.varioml.jaxb.CafeVariome)util.readXML("cafe_variome.xsd", "cafe_variome.xml",org.varioml.jaxb.CafeVariome.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("cafe_variome.json", o);
		
	}

	public void testCafeVariomeJAXBJSON4() throws Exception { 

		Util util = new Util(); 
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb) util.readXML("lsdb.xsd", "lsdb_test_all_new.xml",org.varioml.jaxb.Lsdb.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("test_all_full.json", o);
		
	}

	public static void main(String[] args) {
		
	}
}
