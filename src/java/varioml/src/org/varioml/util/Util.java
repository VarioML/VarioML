package org.varioml.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;


public class Util {
	
	public static void fatal( Class cls, String message) {
		System.err.println("Fatal error in : "+cls.getName()+" message: "+message);
		System.err.flush();
		throw new RuntimeException(message);
	}

	public static void fatal( Class cls, Throwable e) {
		System.err.println("Fatal error in : "+cls.getName()+" message: "+e.getMessage());
		System.err.flush();
		throw new RuntimeException(e);
	}

	public static File findFile( String xmlFile) {

		File f = new File( xmlFile );
		if ( ! f.exists() ) {
			final URL url = Util.class.getResource(xmlFile);
			if ( url != null ) {
				try {
					f = new File( url.toURI());
				} catch (URISyntaxException e) {
					fatal(Util.class,"Error in URI: "+xmlFile);
				}
			} else {
				fatal(Util.class,"File not found"+xmlFile);				
			}
			
		}
		
		if  ( ! f.exists() ) { 
			fatal(Util.class,"file not found: "+xmlFile);
		}
		
		return f;
	}
	
	public static Serializer createSerializer( ) {
						
		Style style = new UnderscoreStyle();
		VarioTypeMatcher mat = new VarioTypeMatcher();
		Format format = new Format(style);		
		final Serializer ser = new Persister(mat,format);
		return ser;		
		
	}
	public static Serializer createSerializer2( ) {
		
		Format format = new Format();		
		VarioTypeMatcher mat = new VarioTypeMatcher();
		final Serializer ser = new Persister(mat,format);
		return ser;		
		
	}

	public static void log(Class  cls, String message) {
		System.err.println("Fatal error in : "+cls.getName()+" message: "+message);
		System.err.flush();
		
	}

}
