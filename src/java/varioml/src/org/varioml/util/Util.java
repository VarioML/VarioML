package org.varioml.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

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
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.strategy.Visitor;
import org.simpleframework.xml.strategy.VisitorStrategy;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

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

	public static Serializer createSerializer() {

		Style style = new UnderscoreStyle();
		VarioTypeMatcher mat = new VarioTypeMatcher();
		Format format = new Format(style);
		Visitor vis = new Visitor() {

			@Override
			public void write(Type type, NodeMap<OutputNode> node) throws Exception {
				OutputNode element = node.getNode();
				OutputNode n = node.get("class") ; // todo: fix the hack. We need to remove simplexml default typing since since do not work with traits
				if ( n != null && n.getValue().startsWith("varioml.parsers")) { 
					node.remove("class");
				}
			}

			@Override
			public void read(Type type, NodeMap<InputNode> node) throws Exception {

			}
		};
		VisitorStrategy str = new VisitorStrategy(vis);
		final Serializer ser = new Persister(str, mat, format);
		return ser;

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
	
	public Object readXML(String schemaFile, String xmlFile, Class clz ) {
		try {
			//todo: make this configuravle.. .now using JAXB as a default
			
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
	        Schema schema = sf.newSchema(new File( schemaFile)); 
	        
			JAXBContext context = JAXBContext.newInstance(clz);
			Marshaller m = context.createMarshaller();
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
			
	public static Serializer createSerializer2() {

		Format format = new Format();
		VarioTypeMatcher mat = new VarioTypeMatcher();
		final Serializer ser = new Persister(mat, format);
		return ser;

	}

	public static void log(Class cls, String message) {
		System.err.println("Fatal error in : " + cls.getName() + " message: " + message);
		System.err.flush();

	}

	
	
}
