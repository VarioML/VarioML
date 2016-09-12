package examples;

import java.net.URL;

import org.varioml.jaxb.CafeVariome;
import org.varioml.jaxb.Frequency;
import org.varioml.jaxb.Vreport;
import org.varioml.util.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

public class Example {


	public static void main(String[] args) throws Exception {

		Util u = new Util();

		URL url1 = new URL(
				"https://raw.github.com/VarioML/VarioML/master/xml/cafe_variome/examples/alamut_example.xml");
		URL url2 = new URL(
				"https://raw.github.com/VarioML/VarioML/master/xml/cafe_variome/examples/cafe_variome_example.xml");

		/* CafeVariome example */
		CafeVariome cv1 = (CafeVariome) u.readXML("xml/xsd/cafe_variome.xsd", url1.openStream(), CafeVariome.class);
		u.writeJSON("alamut_example.json", cv1);
		CafeVariome cv2 = (CafeVariome) u.readXML("xml/xsd/cafe_variome.xsd", url2.openStream(), CafeVariome.class);
		u.writeJSON("cafe_variome_example.json", cv2);
		u.writeEXI("xml/xsd/cafe_variome.xsd", "cafe_variome_example.exi", cv2);

		/* NEMDB example */
		URL url3 = new URL("https://raw.github.com/VarioML/VarioML/master/xml/nemdb/examples/example1.xml");
		Vreport vreport = (Vreport) u.readXML("xml/xsd/vreports.xsd", url3.openStream(), Vreport.class);
		u.writeJSON("vreport.json", vreport);
		u.writeEXI("xml/xsd/vreports.xsd", "vreport_example.exi", vreport);

		/* Generate JSON schema from the Java class */
		u.toJSONSchema("vreport.json-schema", Vreport.class);

		/* Generate JSON schema for the json editor. https://github.com/jdorn/json-editor*/
		u.toJSONSchemaHtml5Enabled("vreport.json-schema-html5", Vreport.class);
		

	}
}
