package org.varioml.util;

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
import java.net.URISyntaxException;
import java.net.URL;

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

import org.varioml.jaxb.CafeVariome;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.megginson.sax.XMLWriter;
import com.siemens.ct.exi.EXIFactory;
import com.siemens.ct.exi.GrammarFactory;
import com.siemens.ct.exi.api.sax.EXIResult;
import com.siemens.ct.exi.api.sax.EXISource;
import com.siemens.ct.exi.exceptions.EXIException;
import com.siemens.ct.exi.grammars.Grammars;
import com.siemens.ct.exi.helpers.DefaultEXIFactory;
//import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonGenerator.Feature;
import de.undercouch.bson4jackson.BsonParser;

public class Util {

//	public static class MyNamespaceMapper extends NamespacePrefixMapper {
//
////		private String PREFIX = ""; // DEFAULT NAMESPACE
//		private String URI = "http://www.example.com/FOO";
//
//		public MyNamespaceMapper(String pREFIX, String uRI) {
//			super();
//			PREFIX = pREFIX;
//			URI = uRI;
//		}
//
//		@Override
//		public String getPreferredPrefix(String namespaceUri,
//				String suggestion, boolean requirePrefix) {
//			if (URI.equals(namespaceUri)) {
//				return PREFIX;
//			}
//			return suggestion;
//		}
//
//		@Override
//		public String[] getPreDeclaredNamespaceUris() {
//			return new String[] { URI };
//		}
//
//	}

	@JsonIgnoreProperties({ "_id" })
	public static abstract class IgnoreMongoIDMixIn {
		public IgnoreMongoIDMixIn() {
		}
	}
	public final Class IGNORE_ID_FIELD = IgnoreMongoIDMixIn.class;

	public static class MyValidationEventHandler implements
			ValidationEventHandler {

		@Override
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
		ObjectMapper mapper = createObjectMapperForBson();
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
		BsonFactory fac = new BsonFactory();
		fac.enable(BsonParser.Feature.HONOR_DOCUMENT_LENGTH);
		ObjectMapper mapper = createObjectMapper(fac);
		ObjectWriter writer = mapper.writer();
		try {
			writer.writeValue(out, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}

	public ByteArrayOutputStream toBSON4MONGOByteStream(Object obj) {
		String _res = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeBSON4MONGO(bos, obj);
		return bos;
	}

	public byte[] toBSON4MONGO(Object obj) {
		String _res = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeBSON4MONGO(bos, obj);
		return bos.toByteArray();
	}

	public void writeBSON4MONGO(String file, Object obj) {

		BsonFactory fac = new BsonFactory();
		fac.enable(BsonParser.Feature.HONOR_DOCUMENT_LENGTH);
		ObjectMapper mapper = createObjectMapper(fac);
		ObjectWriter writer = mapper.writer();
		File _file = new File(file);
		try {
			writer.writeValue(_file, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}

	public Object readBSON(final String file, final Class clz) {
		Object o = null;
		try {
			FileInputStream in = new FileInputStream(file);
			o = createObjectMapperForBson().readValue(in, clz);
			in.close();
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
		return o;
	}

	public Object readBSON(final InputStream in, final Class clz) {
		Object o = null;
		try {
			o = createObjectMapperForBson().readValue(in, clz);
			in.close();
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
		return o;
	}

	public Object readBSON(final InputStream in, final Class clz,
			final Class mixIn) {
		ObjectMapper mapper = createObjectMapperForBson();
		if (mixIn != null) {
			mapper.addMixInAnnotations(clz, mixIn);
		}
		Object o = null;
		try {
			o = mapper.readValue(in, clz);
			in.close();
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}
		return o;
	}

	public Object toVarioML(byte[] bytes, Class clz, Class mixIn) {

		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		return readBSON(in, clz, mixIn);

	}

	public Object toVarioML(byte[] bytes, Class clz) {

		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		return readBSON(in, clz, null);

	}

	public void writeJSON(String file, Object obj) {

		File _file = new File(file);
		try {
			createObjectMapper().writeValue(_file, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}

	protected ObjectMapper createObjectMapperForBson() {
		BsonFactory fac = new BsonFactory();
		fac.enable(Feature.ENABLE_STREAMING);
		return createObjectMapper(fac);
	}
	
	protected ObjectMapper createObjectMapper( ) {
		return createObjectMapper(null);
	}
	
	protected ObjectMapper createObjectMapper( JsonFactory fac) {
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
		// http://wiki.fasterxml.com/JacksonJAXBAnnotations
		// http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
		ObjectMapper mapper;
		if ( fac == null ) {
			mapper = new ObjectMapper();						
		} else {
			mapper = new ObjectMapper(fac);			
		}
		mapper.setAnnotationIntrospector(pair);
		return mapper;
	}

	
	public void writeJSON(OutputStream out, Object obj) {
		ObjectMapper mapper = createObjectMapper(); 
		try {
			mapper.writeValue(out, obj);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
		}

	}

	public String toJSONString(Object obj) {
		String _res = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		writeJSON(bos, obj);
		return new String(bos.toByteArray());
	}

	public Object readJSON(String file, Class clz) {

		ObjectMapper mapper = createObjectMapper();
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
			File file = findFile(xmlFile);
			return readXML(schemaFile, new FileInputStream(file), clz);
		} catch (Exception e) {
			Util.fatal(Util.class, e);
			return null;
		}
	}

	public Object readXML(String schemaFile, InputStream iStream, Class clz) {
		try {
			// todo: make this configuravle.. .now using JAXB as a default

			SchemaFactory sf = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = sf.newSchema(new File(schemaFile));
			JAXBContext context = JAXBContext.newInstance(clz);
			// Marshaller m = context.createMarshaller();
			Unmarshaller um = context.createUnmarshaller();
			um.setEventHandler(new MyValidationEventHandler());
			um.setSchema(schema);
			Object o = um.unmarshal(iStream);
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
			// um.setSchema(schemaFile);
			Object o = um.unmarshal(exiSource);

			return o;
		} catch (Exception e) {
			System.err.println("XML schema may have changed");
			Util.fatal(Util.class, e);
			return null;
		}
	}

	public void writeXML(String xmlFile, Object obj) {
		writeXML(null, xmlFile, obj);
	}

	public void writeXML(String schemaFile, String xmlFile, Object obj) {
		try {
			// todo: make this configurable.. .now using JAXB as a default

			SchemaFactory sf = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			File file = new File(xmlFile);

			PrintWriter out = new PrintWriter(file);
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


	public void writeXMLWithoutNS(String schemaFile, String xmlFile, Object obj) {
		try {
			// todo: make this configuravle.. .now using JAXB as a default
			// todo: copy and paste code
			SchemaFactory sf = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			File file = new File(xmlFile);

			PrintWriter out = new PrintWriter(file);
//			XMLOutputFactory fac = XMLOutputFactory.newInstance();
//			fac.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
//			XMLStreamWriter xmlStreamWriter = fac.createXMLStreamWriter(out);
//			xmlStreamWriter.setDefaultNamespace("http://varioml.org/xml/1.0");
//			xmlStreamWriter.setPrefix("", "http://varioml.org/xml/1.0");
			
			NamespaceFilter outFilter = new NamespaceFilter(
					"http://varioml.org/xml/1.0", false); // remove name spaces
			XMLWriter writer = new XMLWriter(out);
			outFilter.setContentHandler(writer);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			//m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			m.setEventHandler(new MyValidationEventHandler());
			if (schemaFile != null) {
				Schema schema = sf.newSchema(new File(schemaFile));
				m.setSchema(schema);
			}
			m.marshal(obj, outFilter);
			
			writer.flush();
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
		// /todo: cut and paste...
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
			e.printStackTrace();
		} catch (EXIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {

		Util util = new Util();
		org.varioml.jaxb.CafeVariome o = (org.varioml.jaxb.CafeVariome) util
				.readXML("schema/cafe_variome.xsd", "findis.xml",
						org.varioml.jaxb.CafeVariome.class);

		util.writeXML("schema/cafe_variome.xsd", "test_xml.xml", o);
		util.writeJSON("test.json", o);
		util.writeBSON("test.bson", o);
		util.writeEXI("schema/cafe_variome.xsd", "test.exi", o);
		util.writeBSON4MONGO("test.mongo", o);
		o = (CafeVariome)util.readBSON("test.bson", org.varioml.jaxb.CafeVariome.class);
		util.writeXML("test_bson.xml", o);
		o = (CafeVariome)util.readJSON("test.json", org.varioml.jaxb.CafeVariome.class);
		util.writeXML("test_json.xml", o);
		o = (CafeVariome)util.readEXI("schema/cafe_variome.xsd", "test.exi", org.varioml.jaxb.CafeVariome.class);
		util.writeXML("test_exi.xml", o);

	}
}
