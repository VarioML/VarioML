package test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.HyphenStyle;
import org.simpleframework.xml.stream.Style;
import org.varioml.data.DbXref;
import org.varioml.data.Lsdb;
import org.varioml.data.Source;
import org.varioml.data.Source.Contact;
import org.varioml.util.UnderscoreStyle;
import org.varioml.util.Util;

import junit.framework.TestCase;





public class LsdbTest extends TestCase  {


	private String fileName = "/airebase_example.xml" ;

	public void testParser() throws Exception { 

		final Serializer ser = Util.createSerializer();
		final File file = Util.findFile(fileName) ;
		
		Lsdb lsdb = ser.read(Lsdb.class, file);

		List<Source> src = lsdb.getSource();
		for (Source source : src) {
			System.err.println(source.getName());
		}
		ser.write(lsdb, new File("example0.xml"));
		
		
		
	}

		
	public void testWriter() throws Exception { 
		final File f = new File( "example1.xml");
		
		
		
		Lsdb lsdb = new Lsdb();
		Source src = new Source();
		Style style = new UnderscoreStyle();
		Format format = new Format(style);
		
		final Serializer ser = new Persister(format);
		
		src.setName("Database");
		Contact c = new Contact();
		c.setAddress("Osoite 17");
		c.setName("Juha Muilu");
		src.addContact(c); 
		lsdb.addSource(src);
		DbXref db = new DbXref();
		db.setSource("test");
		db.setAccession("xyz");

		DbXref db2 = new DbXref();
		db2.setSource("test");
		db2.setAccession("xyz");

		ArrayList<DbXref> tmp = new ArrayList<DbXref>();
		ArrayList<DbXref> tmp3 = new ArrayList<DbXref>();
		tmp3.add(db2);
		db.setDbXref(tmp3);
		tmp.add(db);
		src.setDbXref( tmp);
		ser.write(lsdb,f);
		
		
	}

	public void testParser2() throws Exception { 

		final File f = new File( "tmp.xml");
		Style style = new UnderscoreStyle();
		Format format = new Format(style);
		
		final Serializer ser = new Persister(format);


		Lsdb lsdb = ser.read(Lsdb.class, f);
		
		List<Source> src = lsdb.getSource();
		for (Source source : src) {
			System.err.println(source.getName());
		}
		ser.write(lsdb, new File("example11.xml"));
	}


}
