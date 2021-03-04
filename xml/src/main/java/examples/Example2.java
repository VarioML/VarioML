package examples;

import org.varioml.jaxb.Lsdb;
import org.varioml.util.Util;

public class Example2 {


	public static void main(String[] args) throws Exception {

		Util u = new Util();

		/* LOVD example */
		Lsdb lsdb = (Lsdb) u.readXML("xml/xsd/cafe_variome.xsd","xml/lsdb_main/examples/lovd_example.xml", Lsdb.class);

		/* Generate JSON schema from the Java class */
		u.toJSONSchema("lovd.json-schema", Lsdb.class);

		/* Generate JSON schema for the json editor. https://github.com/jdorn/json-editor*/
		u.toJSONSchemaHtml5Enabled("lovd.json-schema-html5", Lsdb.class);

		


	}
}
