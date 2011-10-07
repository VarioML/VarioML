	package test;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import org.simpleframework.xml.Serializer;
import org.varioml.data.DbXref;
import org.varioml.data.Lsdb;
import org.varioml.data.Source;
import org.varioml.data.VariantGroup;
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


	public void testParserNew() throws Exception { 

		final Serializer ser = Util.createSerializer();
		final File file = Util.findFile("new_variant.xml") ;
	
		VariantGroup lsdb = ser.read(VariantGroup.class, file);

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
		
		


}
