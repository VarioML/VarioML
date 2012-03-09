package org.varioml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class Util {

	public static class MyValidationEventHandler implements ValidationEventHandler {
		 
	    public boolean handleEvent(ValidationEvent event) {
	        System.out.println("\nEVENT");
	        System.out.println("SEVERITY:  " + event.getSeverity());
	        System.out.println("MESSAGE:  " + event.getMessage());
	        System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
	        System.out.println("LOCATOR");
	        System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
	        System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
	        System.out.println("    OFFSET:  " + event.getLocator().getOffset());
	        System.out.println("    OBJECT:  " + event.getLocator().getObject());
	        System.out.println("    NODE:  " + event.getLocator().getNode());
	        System.out.println("    URL:  " + event.getLocator().getURL());
	        return true;
	    }
	 
	}

	public static void fatal(Class cls, String message) {
		System.err.println("Fatal error in : " + cls.getName() + " message: " + message);
		System.err.flush();
		throw new RuntimeException(message);
	}

	public static void fatal(Class cls, Throwable e) {
		System.err.println("Fatal error in : " + cls.getName() + " message: " + e.getMessage());
		System.err.flush();
		throw new RuntimeException(e);
	}

	public static File findFile(String xmlFile) {

		File f = new File(xmlFile);
		if (!f.exists()) {
			final URL url = Util.class.getResource(xmlFile);
			if (url != null) {
				try {
					f = new File(url.toURI());
				} catch (URISyntaxException e) {
					fatal(Util.class, "Error in URI: " + xmlFile);
				}
			} else {
				fatal(Util.class, "File not found" + xmlFile);
			}

		}

		if (!f.exists()) {
			fatal(Util.class, "file not found: " + xmlFile);
		}

		return f;
	}

	public void writeJSON ( String file, Object obj   ) {
		
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
	    AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
	    AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
	    
		//http://wiki.fasterxml.com/JacksonJAXBAnnotations
		//http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
	    ObjectMapper mapper = new ObjectMapper();
	    // make deserializer use JAXB annotations (only)
	    mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
	    mapper.getSerializationConfig().setAnnotationIntrospector(pair);
	    ObjectWriter writer = mapper.defaultPrettyPrintingWriter();
	    File _file = new File(file);
	    try {
			// make serializer use JAXB annotations (only)
			//mapper.writeValue(_file, obj);
	    	writer.writeValue(_file, obj) ;
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

		
	}

	public Object readJSON ( String file, Class clz  ) {
		
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
	    AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
	    AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
	    
		//http://wiki.fasterxml.com/JacksonJAXBAnnotations
		//http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
	    ObjectMapper mapper = new ObjectMapper();
	    // make deserializer use JAXB annotations (only)
	    mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
	    mapper.getSerializationConfig().setAnnotationIntrospector(pair);

	    Object o = null;
	    try {
			FileInputStream in = new FileInputStream(file);
			o = mapper.readValue(in,clz);
			in.close();
		} catch (Exception e) { 
			Util.fatal(Util.class,e);
		}
	    return o;
	}

	public Object readXML(String schemaFile, String xmlFile, Class  clz ) {
		try {
			//todo: make this configuravle.. .now using JAXB as a default
			
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
	        Schema schema = sf.newSchema(new File( schemaFile)); 
			JAXBContext context = JAXBContext.newInstance(clz);
			//Marshaller m = context.createMarshaller();
			File file = findFile(xmlFile);
			Unmarshaller um = context.createUnmarshaller();
			um.setEventHandler( new MyValidationEventHandler());
			um.setSchema(schema);
			Object o = um.unmarshal(file);
			return o;
		} catch (Exception e) {
			Util.fatal(Util.class, e);
			return null;
		}

		
	}

	public void writeXML( String xmlFile, Object  obj) {
		writeXML(null,xmlFile,obj) ;
	}
	public void writeXML(String schemaFile, String xmlFile, Object  obj) {
		try {
			//todo: make this configuravle.. .now using JAXB as a default
			
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			File file = new File(xmlFile);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setEventHandler( new MyValidationEventHandler());
			if ( schemaFile != null) {
		        Schema schema = sf.newSchema(new File( schemaFile)); 
				m.setSchema(schema);				
			}
			PrintWriter out = new PrintWriter(file) ;
			m.marshal(obj, out);
			out.close();

		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
	}
	
	public static void log(Class cls, String message) {

		System.err.println("LOG : " + cls.getName() + " message: " + message);
		System.err.flush();

	}

	public static void main(String[] args) throws Exception {

		Util util = new Util();
		org.varioml.jaxb.CafeVariome o =  (org.varioml.jaxb.CafeVariome)util.readXML("cafe_variome.xsd", "cafe_variome.xml",org.varioml.jaxb.CafeVariome.class);
		util.writeJSON("tmp.json", o);
		Object x = util.readJSON("tmp.json",org.varioml.jaxb.CafeVariome.class);
		util.writeXML("cafe_variome.xsd", "tmp.xml",x);
		
	}
}
	
	

