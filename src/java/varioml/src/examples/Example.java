package examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.varioml.jaxb.CafeVariome;
import org.varioml.util.Util;

public class Example {
	
	
	public static void main(String[] args) throws Exception {
		Util u = new Util();
		
		URL url1 = new URL("https://raw.github.com/VarioML/VarioML/master/xml/cafe_variome/examples/alamut_example.xml");
		URL url2 = new URL("https://raw.github.com/VarioML/VarioML/master/xml/cafe_variome/examples/cafe_variome_example.xml");
		BufferedReader in = new BufferedReader( new InputStreamReader(url1.openStream()));
		
		CafeVariome cv1 = (CafeVariome) u.readXML("schema/cafe_variome.xsd", url1.openStream(), CafeVariome.class);
		u.writeJSON("alamut_example.json", cv1);
		CafeVariome cv2 = (CafeVariome) u.readXML("schema/cafe_variome.xsd", url2.openStream(), CafeVariome.class);
		u.writeJSON("cafe_variome_example.json", cv2);
		u.writeEXI("schema/cafe_variome.xsd","cafe_variome_example.exi", cv2);
		
;	}

}
