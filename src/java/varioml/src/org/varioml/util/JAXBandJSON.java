package org.varioml.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.varioml.data.CommentText;
import org.varioml.jaxb.Comment;
import org.varioml.jaxb.Exon;
import org.varioml.jaxb.Panel;


public class JAXBandJSON {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		ProjectTest test = new ProjectTest();
		test.setName("Juha M");
		test.addDbXRef("ref1");
		test.addDbXRef("ref2");
		test.addDbXRef("ref3");
		
		final JAXBContext jaxbContext = JAXBContext.newInstance(ProjectTest.class);

		StringWriter writer = new StringWriter();
		jaxbContext.createMarshaller().marshal(test, writer);
		
		UnderscoreStyle style = new UnderscoreStyle();
		//System.out.print ( style.getAttribute("db_xref"));
		//System.err.print(writer.toString()) ;

		
		Panel ex = new Panel();
		ex.setUri("uri:iiii");

		Comment com1 = new Comment();
		com1.setTerm("test");
		Comment com2 = new Comment();
		com1.setTerm("test2");
		org.varioml.jaxb.CommentText txt = new org.varioml.jaxb.CommentText();
		txt.setString("textstuff");
		txt.setLangAttr("en");
		com1.addText(txt);
		com1.addText(txt);
		com1.addText(txt);
		com1.addText(txt); 
		ex.addComment(com1);
		//ex.addComment(com2);
		com2.addText(txt);
		
		final JAXBContext jc = JAXBContext.newInstance(Panel.class);

		StringWriter writer2 = new StringWriter();

		
		Marshaller marshaller = jc.createMarshaller();  
		  
		marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );  
		  
		marshaller.marshal( ex, System.out );  
		
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
	    AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
	    AnnotationIntrospector pair = new AnnotationIntrospector.Pair(primary, secondary);
	    
		//http://wiki.fasterxml.com/JacksonJAXBAnnotations
		//http://ondra.zizka.cz/stranky/programovani/java/jaxb-json-jackson-howto.texy
	    ObjectMapper mapper = new ObjectMapper();
	    // make deserializer use JAXB annotations (only)
	    mapper.getDeserializationConfig().setAnnotationIntrospector(pair);
	    mapper.getSerializationConfig().setAnnotationIntrospector(pair);
	    // make serializer use JAXB annotations (only)
	    mapper.writeValue( System.out, ex );
	}

}
