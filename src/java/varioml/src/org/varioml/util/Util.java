package org.varioml.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

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
