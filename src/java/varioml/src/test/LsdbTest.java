	package test;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.varioml.util.Util;





public class LsdbTest extends TestCase  {




		
		
	public void testJAXB() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb)util.readXML("schema/lsdb.xsd", "test_data/lsdb_test_all.xml",org.varioml.jaxb.Lsdb.class);
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
		org.varioml.jaxb.CafeVariome o =  (org.varioml.jaxb.CafeVariome)util.readXML("schema/cafe_variome.xsd", "test_data/cafe_variome.xml",org.varioml.jaxb.CafeVariome.class);
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
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb)util.readXML("schema/lsdb.xsd", "templates/panel1.xml",org.varioml.jaxb.Lsdb.class);
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
		org.varioml.jaxb.CafeVariome o =  (org.varioml.jaxb.CafeVariome)util.readXML("schema/cafe_variome.xsd", "test_data/cafe_variome.xml",org.varioml.jaxb.CafeVariome.class);
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
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb) util.readXML("schema/lsdb.xsd", "test_data/lsdb_test_all_new.xml",org.varioml.jaxb.Lsdb.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("test_all_full.json", o);
		Object x = util.readJSON("test_all_full.json",org.varioml.jaxb.Lsdb.class);
		util.writeXML("schema/lsdb.xsd", "test_all_full2.xml",x);
		
	}

	public static void main(String[] args) {
		
	}
}
