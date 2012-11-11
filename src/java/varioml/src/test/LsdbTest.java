	package test;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.varioml.util.Util;





public class LsdbTest extends TestCase  {




		
		
	public void testJAXB() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb)util.readXML("schema/lsdb.xsd", "templates/lsdb1.xml",org.varioml.jaxb.Lsdb.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("lsdb.json", o);
		
	}

	public void testEXI() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb)util.readXML("schema/lsdb.xsd", "templates/lsdb1.xml",org.varioml.jaxb.Lsdb.class);
		
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeEXI("schema/lsdb.xsd","lsdb_exi_test.exi", o);
		o =  (org.varioml.jaxb.Lsdb)util.readEXI("schema/lsdb.xsd", "lsdb_exi_test.exi",org.varioml.jaxb.Lsdb.class);
		util.writeXML("schema/lsdb.xsd","lsdb_exi_test.xml", o);
		o =  (org.varioml.jaxb.Lsdb)util.readXML("schema/lsdb.xsd", "lsdb_exi_test.xml",org.varioml.jaxb.Lsdb.class);
		util.writeEXI("schema/lsdb.xsd","lsdb_exi_test2.exi", o);
		//todo: chk file sizes etc..

		
	}

	public void testCafeVariomeJAXBJSON() throws Exception { 

		Util util = new Util();
		org.varioml.jaxb.CafeVariome o =  (org.varioml.jaxb.CafeVariome)util.readXML("schema/cafe_variome.xsd", "templates/cafe_variome1.xml",org.varioml.jaxb.CafeVariome.class);
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
		org.varioml.jaxb.Panel o =  (org.varioml.jaxb.Panel)util.readXML("schema/lsdb.xsd", "templates/panel1.xml",org.varioml.jaxb.Panel.class);
		List<org.varioml.jaxb.Variant> src = o.getVariantList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Variant var = (org.varioml.jaxb.Variant) iterator.next();
				System.err.println(var.getName());
			}
			
		}
		util.writeJSON("panel.json", o);
		
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
		org.varioml.jaxb.Lsdb o =  (org.varioml.jaxb.Lsdb) util.readXML("schema/lsdb.xsd", "templates/lsdb1.xml",org.varioml.jaxb.Lsdb.class);
		List<org.varioml.jaxb.Source> src = o.getSourceList();
		if ( src != null ) {
			for (Iterator iterator = src.iterator(); iterator.hasNext();) {
				org.varioml.jaxb.Source source = (org.varioml.jaxb.Source) iterator.next();
				System.err.println(source.getName());
			}
			
		}
		util.writeJSON("test_all_full.json", o);
		util.writeXML("test_all_full.xml", o);
		Object x = util.readJSON("test_all_full.json",org.varioml.jaxb.Lsdb.class);
		util.writeXML("schema/lsdb.xsd", "test_all_full2.xml",x);
		
	}

	public static void main(String[] args) {
		
	}
}
