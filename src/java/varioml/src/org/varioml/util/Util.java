package org.varioml.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.siemens.ct.exi.EXIFactory;
import com.siemens.ct.exi.GrammarFactory;
import com.siemens.ct.exi.api.sax.EXIResult;
import com.siemens.ct.exi.api.sax.EXISource;
import com.siemens.ct.exi.exceptions.EXIException;
import com.siemens.ct.exi.grammars.Grammars;
import com.siemens.ct.exi.helpers.DefaultEXIFactory;

import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonGenerator.Feature;
import de.undercouch.bson4jackson.BsonParser;

public class Util {

	
	@JsonIgnoreProperties({"_id"})
	public static abstract class IgnoreMongoIDMixIn {
		public IgnoreMongoIDMixIn () {}
	}
	
	public final Class IGNORE_ID_FIELD = IgnoreMongoIDMixIn.class;
	
	public static class MyValidationEventHandler implements
			ValidationEventHandler {

		public boolean handleEvent(ValidationEvent event) {
			System.out.println("\nEVENT");
			System.out.println("SEVERITY:  " + event.getSeverity());
			System.out.println("MESSAGE:  " + event.getMessage());
			System.out.println("LINKED EXCEPTION:  "
					+ event.getLinkedException());
			System.out.println("LOCATOR");
			System.out.println("    LINE NUMBER:  "
					+ event.getLocator().getLineNumber());
			System.out.println("    COLUMN NUMBER:  "
					+ event.getLocator().getColumnNumber());
			System.out
					.println("    OFFSET:  " + event.getLocator().getOffset());
			System.out
					.println("    OBJECT:  " + event.getLocator().getObject());
			System.out.println("    NODE:  " + event.getLocator().getNode());
			System.out.println("    URL:  " + event.getLocator().getURL());
			return true;
		}

	}

	public static void fatal(Class cls, String message) {
		System.err.println("Fatal error in : " + cls.getName() + " message: "
				+ message);
		System.err.flush();
		throw new RuntimeException(message);
	}

	public static void fatal(Class cls, Throwable e) {
		System.err.println("Fatal error in : " + cls.getName() + " message: "
				+ e.getMessage());
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

	public void writeBSON(String file, Object obj) {
		// todo: fix cut and paste code
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy

		BsonFactory fac = new BsonFactory();
		fac.enable(Feature.ENABLE_STREAMING);

		ObjectMapper mapper = new ObjectMapper(fac);
		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);
		ObjectWriter writer = mapper.writer();
		File _file = new File(file);
		try {
			// make serializer use JAXB annotations (only)
			// mapper.writeValue(_file, obj);
			writer.writeValue(_file, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}

	public void writeBSON4MONGO(OutputStream out, Object obj) {
		// todo: fix cut and paste code
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy

		BsonFactory fac = new BsonFactory();
		fac.enable(BsonParser.Feature.HONOR_DOCUMENT_LENGTH);
		ObjectMapper mapper = new ObjectMapper(fac);
		//mapper.getDeserializationConfig().addMixInAnnotations(obj.getClass(), MixIn.class);

		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);
		ObjectWriter writer = mapper.writer();
		try {
			// make serializer use JAXB annotations (only)
			// mapper.writeValue(_file, obj);
			writer.writeValue(out, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}

	public ByteArrayOutputStream toBSON4MONGOByteStream( Object obj) {
		String _res=null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeBSON4MONGO(bos, obj);
		return bos;
	}


	public byte[] toBSON4MONGO( Object obj) {
		String _res=null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeBSON4MONGO(bos, obj);
		return bos.toByteArray();
	}

	public void writeBSON4MONGO(String file, Object obj) {
		// todo: fix cut and paste code
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy

		BsonFactory fac = new BsonFactory();
		fac.enable(BsonParser.Feature.HONOR_DOCUMENT_LENGTH);
		ObjectMapper mapper = new ObjectMapper(fac);
		//mapper.getDeserializationConfig().addMixInAnnotations(obj.getClass(), MixIn.class);

		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);
		ObjectWriter writer = mapper.writer();
		File _file = new File(file);
		try {
			// make serializer use JAXB annotations (only)
			// mapper.writeValue(_file, obj);
			writer.writeValue(_file, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}

	public Object readBSON(String file, Class clz) {
		// todo: fix cut and paste
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
		BsonFactory fac = new BsonFactory();
		fac.enable(Feature.ENABLE_STREAMING);

		ObjectMapper mapper = new ObjectMapper(fac);

		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);

		Object o = null;
		try {
			FileInputStream in = new FileInputStream(file);
			o = mapper.readValue(in, clz);
			in.close();
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
		return o;
	}

	
	public Object readBSON(InputStream in, Class clz) {
		// todo: fix cut and paste
		
		//org.codehaus.jackson.map.DeserializationConfig.Feature.¤
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
		BsonFactory fac = new BsonFactory();
		fac.enable(Feature.ENABLE_STREAMING);

		ObjectMapper mapper = new ObjectMapper(fac);

		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);

		Object o = null;
		try {
			o = mapper.readValue(in, clz);
			in.close();
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
		return o;
	}

	public Object readBSON(InputStream in, Class clz, Class mixIn) {
		// todo: fix cut and paste
		
		//org.codehaus.jackson.map.DeserializationConfig.Feature.¤
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
		BsonFactory fac = new BsonFactory();
		fac.enable(Feature.ENABLE_STREAMING);

		ObjectMapper mapper = new ObjectMapper(fac);
		mapper.getDeserializationConfig().addMixInAnnotations(clz, mixIn);
		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);

		Object o = null;
		try {
			o = mapper.readValue(in, clz);
			in.close();
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
		return o;
	}

	public Object toVarioML( byte[] bytes, Class clz, Class mixIn) {
		
		ByteArrayInputStream in  = new ByteArrayInputStream(bytes);
		return readBSON(in, clz, mixIn);
		
	}
	public void writeJSON(String file, Object obj) {

		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
		ObjectMapper mapper = new ObjectMapper();
		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);
		ObjectWriter writer = mapper.defaultPrettyPrintingWriter();
		File _file = new File(file);
		try {
			// make serializer use JAXB annotations (only)
			// mapper.writeValue(_file, obj);
			writer.writeValue(_file, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}
	public void writeJSON(OutputStream out, Object obj) {

		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
		ObjectMapper mapper = new ObjectMapper();
		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);
		ObjectWriter writer = mapper.defaultPrettyPrintingWriter();
		try {
			// make serializer use JAXB annotations (only)
			// mapper.writeValue(_file, obj);
			writer.writeValue(out, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}
	
	public String toJSONString ( Object obj) {
		String _res=null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeJSON(bos, obj);
		return new String( bos.toByteArray());
	}
	
	public Object readJSON(String file, Class clz) {

		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary,
				secondary);

		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
		ObjectMapper mapper = new ObjectMapper();
		// make deserializer use JAXB annotations (only)
		mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
		mapper.getSerializationConfig().setAnnotationIntrospector(pair);

		Object o = null;
		try {
			FileInputStream in = new FileInputStream(file);
			o = mapper.readValue(in, clz);
			in.close();
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
		return o;
	}

	public Object readXML(String schemaFile, String xmlFile, Class clz) {
		try {
			// todo: make this configuravle.. .now using JAXB as a default

			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(schemaFile));
			JAXBContext context = JAXBContext.newInstance(clz);
			// Marshaller m = context.createMarshaller();
			File file = findFile(xmlFile);
			Unmarshaller um = context.createUnmarshaller();
			um.setEventHandler(new MyValidationEventHandler());
			um.setSchema(schema);
			Object o = um.unmarshal(file);
			return o;
		} catch (Exception e) {
			Util.fatal(Util.class, e);
			return null;
		}
	}

	public Object readEXI(String schemaFile, String xmlFile, Class clz) {
		try {
			// todo: fix this quick hack

			// SchemaFactory sf = SchemaFactory
			// .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			// Schema schema = sf.newSchema(new File(schemaFile));
			JAXBContext context = JAXBContext.newInstance(clz);
			// Marshaller m = context.createMarshaller();

			EXIFactory exiFactory = DefaultEXIFactory.newInstance();
			GrammarFactory grammarFactory = GrammarFactory.newInstance();
			Grammars g = grammarFactory.createGrammars(schemaFile);
			exiFactory.setGrammars(g);

			EXISource saxSource = new EXISource(exiFactory);
			XMLReader xmlReader = saxSource.getXMLReader();

			InputStream exiIS = new FileInputStream(xmlFile);
			SAXSource exiSource = new SAXSource(new InputSource(exiIS));
			exiSource.setXMLReader(xmlReader);

			Unmarshaller um = context.createUnmarshaller();
			um.setEventHandler(new MyValidationEventHandler());
			//um.setSchema(schemaFile);
			Object o = um.unmarshal(exiSource);

			return o;
		} catch (Exception e) {
			Util.fatal(Util.class, e);
			return null;
		}
	}

	public void writeXML(String xmlFile, Object obj) {
		writeXML(null, xmlFile, obj);
	}

	public void writeXML(String schemaFile, String xmlFile, Object obj) {
		try {
			// todo: make this configuravle.. .now using JAXB as a default

			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			File file = new File(xmlFile);

			PrintWriter out = new PrintWriter(file);

			// XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance()
			// .createXMLStreamWriter(out);
			// xmlStreamWriter.setPrefix("vm", "http://varioml.org/xml/1.0");
			// xmlStreamWriter.setDefaultNamespace("http://varioml.org/xml/1.0");
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.setEventHandler(new MyValidationEventHandler());
			if (schemaFile != null) {
				Schema schema = sf.newSchema(new File(schemaFile));
				m.setSchema(schema);
			}
			m.marshal(obj, out);
			out.close();

		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
	}

	public void writeEXI(String schemaFile, String exiFile, Object obj) {
		try {
			// todo: quick hack..

			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller m = context.createMarshaller();
			m.setEventHandler(new MyValidationEventHandler());

			EXIFactory exiFactory = DefaultEXIFactory.newInstance();
			GrammarFactory grammarFactory = GrammarFactory.newInstance();
			Grammars g = grammarFactory.createGrammars(schemaFile);
			exiFactory.setGrammars(g);
			// encode
			OutputStream exiOS = new FileOutputStream(exiFile);

			EXIResult exiResult = new EXIResult(exiFactory);
			exiResult.setOutputStream(exiOS);
			m.marshal(obj, exiResult);
			exiOS.close();

		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
	}

	public void writeEXI(String schemaFile, OutputStream ostream, Object obj) {
		///todo: cut and paste...
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller m = context.createMarshaller();
			m.setEventHandler(new MyValidationEventHandler());

			EXIFactory exiFactory = DefaultEXIFactory.newInstance();
			GrammarFactory grammarFactory = GrammarFactory.newInstance();
			Grammars g = grammarFactory.createGrammars(schemaFile);
			exiFactory.setGrammars(g);
			// encode

			EXIResult exiResult = new EXIResult(exiFactory);
			exiResult.setOutputStream(ostream);
			m.marshal(obj, exiResult);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
	}

	public static void log(Class cls, String message) {

		System.err.println("LOG : " + cls.getName() + " message: " + message);
		System.err.flush();

	}

	public static void encode(org.xml.sax.ContentHandler ch, String xmlLocation)
			throws SAXException, IOException {
		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
		xmlReader.setContentHandler(ch);

		// parse xml file
		xmlReader.parse(new InputSource(xmlLocation));
	}

	public static void decode(XMLReader exiReader, String exiLocation,
			String xmlOutLocation) throws SAXException, IOException,
			TransformerException {

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		InputStream exiIS = new FileInputStream(exiLocation);
		SAXSource exiSource = new SAXSource(new InputSource(exiIS));
		exiSource.setXMLReader(exiReader);

		OutputStream os = new FileOutputStream(xmlOutLocation);
		transformer.transform(exiSource, new StreamResult(os));
		os.close();
	}

	public static void compressTest(String xsdLocation, String sourceXml,
			String compressedXml) {

		try {
			EXIFactory exiFactory = DefaultEXIFactory.newInstance();
			GrammarFactory grammarFactory = GrammarFactory.newInstance();
			Grammars g = grammarFactory.createGrammars(xsdLocation);
			exiFactory.setGrammars(g);
			// encode
			OutputStream exiOS = new FileOutputStream(compressedXml);
			EXIResult exiResult = new EXIResult(exiFactory);
			exiResult.setOutputStream(exiOS);
			encode(exiResult.getHandler(), compressedXml);
			exiOS.close();

			// decode
			EXISource saxSource = new EXISource(exiFactory);
			XMLReader xmlReader = saxSource.getXMLReader();
			decode(xmlReader, compressedXml, "tmp.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EXIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		Util util = new Util();
		org.varioml.jaxb.CafeVariome o = (org.varioml.jaxb.CafeVariome) util
				.readEXI("schema/cafe_variome.xsd", "1KG.exi",
						org.varioml.jaxb.CafeVariome.class);
//		util.writeXML("schema/cafe_variome.xsd", "test.xml", o);
//		util.writeEXI("schema/cafe_variome.xsd", "test.exi", o);
        util.writeBSON4MONGO("test.bson", o);
//
//		org.varioml.jaxb.CafeVariome o2 = (org.varioml.jaxb.CafeVariome) util
//				.readXML("schema/cafe_variome.xsd", "test.xml",
//						org.varioml.jaxb.CafeVariome.class);
//
		org.varioml.jaxb.CafeVariome o3 = (org.varioml.jaxb.CafeVariome) util
				.readBSON("test.bson", org.varioml.jaxb.CafeVariome.class);
//
		util.writeJSON("tmp.json", o);
		util.writeJSON("tmp2.json", o3);
		// Object x = util.readJSON("tmp.json", org.varioml.jaxb.Panel.class);
		// //util.writeXML("lsdb.xsd", "tmp.xml", x);

	}
}
