package org.varioml.doc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.varioml.util.RNGMetadataAPI;
import org.varioml.util.Util;
import org.varioml.util.RNGMetadataAPI.MetaData;
import org.w3c.dom.NodeList;

public class HTMLDocumentGenerator {
	private PrintStream out = System.out;

	public HTMLDocumentGenerator(String outFile) {
		File f = new File(outFile);
		PrintStream str = null;
		try {
			f.createNewFile();
			str = new PrintStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out = str;
	}

	protected void p(String str) {
		out.println(str);
	}

	public void HTMLHeader(String title) {
		p("<!DOCTYPE html SYSTEM \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		p("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		p("     <head>");
		p("       <title>" + title + "</title>");
		p("       <style type=\"text/css\">table { border:2px; border-collapse:collapse; }</style>");
		// p("       <style type=\"text/css\">th { border:1px; border-collapse:collapse; bgcolor:aliceblue;}</style>");
		p("     </head>");
		p("    <body>");
	}

	public void HTMLEnd() {
		p("    </body>");
		p("</html>)");
	}

	public void generate(MetaData data) {
		String type = getType( data.patternList);
		if ( type.length() >0 ) {
			type = " (<a name='"+type+"'<em>"+type+"</em></a>)";
		}
		p("     <h2 class='header'>" + data.name +type
				+ "</h2>");

		List<MetaData> props = data.properties;
		boolean hasAttribs = false;
		boolean hasElems = false;
		for (MetaData d : props) {
			hasAttribs = hasAttribs || d.nodeType == MetaData.XML_ATTRIBUTE;
			hasElems = hasElems || d.nodeType == MetaData.XML_ELEMENT;
		}
		if (hasAttribs) {
			p("     <div class='attributes'>");
			p("       <h3>Attributes</h3>");
			p("       <table border='1'>");
			p("         <tr><th>name</th><th>type</th><th>cardinality</th><th>value</th></tr>");
			for (MetaData d : props) {
				if (d.nodeType == MetaData.XML_ATTRIBUTE) {
					p("         <tr><td>" + d.name + "</td><td>" + d.dataType + "</td><td>"
							+ RNGMetadataAPI.cardinToString(d.min, d.max) + "</td><td>"
							+ RNGMetadataAPI.listToString(d.choiceList) + "</td></tr>");
				}
				;
			}
			p("        </table>");
			p("     </div>");
		}

		if (hasElems) {
			p("     <div class='elements'>");
			p("       <h3>Elements</h3>");
			p("       <table border='1'>");
			p("         <tr><th>name</th><th>type</th><th>cardinality</th><th>choice group</th></tr>");
			for (MetaData d : props) {
				if (d.nodeType == MetaData.XML_ELEMENT) {
					if (!d.isChoiceGroup) {
						String typeStr = "<em>string</em>";//todo: the guess
						if ( d.patternList.size()> 0) {
							typeStr="<a href='#"+getType(d.patternList)+"'>"+getType(d.patternList) + "</a>";
						} 
						p("         <tr><td>" + d.name + "</td><td>" + typeStr+"</td><td>"
								+ RNGMetadataAPI.cardinToString(d.min, d.max) + "</td><td>"
								+ RNGMetadataAPI.listToString(d.choiceList) + "</td></tr>");
						;

					} else {

						p("         <tr><td>" + "" + "</td><td>" + "<em>choice</em>" + "</td><td>"
								+ RNGMetadataAPI.cardinToString(d.min, d.max) + "</td><td>"
								+ RNGMetadataAPI.propertiesToString(d) + "</td></tr>");
						;

					}
				}
			}
			p("        </table>");
			p("     </div>");
		}
		if ( data.hasTextNode ) {
			//todo: improve this... type etc.
			p("</p><d class='text'>text node </d>") ;
		}
		p("     </p><div class='doc' <h3>Documentation</h3>" + data.documentation + "</h2>");

	}

	//get type from the list of name of gramma patterns. Patterns are ordered from top to down
	// todo: check .. there can be problems in here...
	private String getType(List<String> patternList) {
		if ( patternList.size() == 0) return "";
		
		return patternList.get(0);
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 0)
			Util.fatal(HTMLDocumentGenerator.class, "Relax RNG file is missing");
		RNGMetadataAPI app = RNGMetadataAPI.createInstance(args[0]);

		// MetaData data =
		// app.parseAndCreateMetadata("grammar/define/element[@name='variant']");
		// HTMLDocumentGenerator gener = new HTMLDocumentGenerator("tmp.html");
		// gener.HTMLHeader("VarioML");
		// gener.generate(data);
		// gener.HTMLEnd() ;
		// NodeList nodes = app.findAllXMLNodes("grammar/define/element");
		// for (int i = 0; i < nodes.getLength(); i++) {
		//
		// }

		HTMLDocumentGenerator gener = new HTMLDocumentGenerator("tmp.html");
		gener.HTMLHeader("VarioML");

		NodeList nodes = app.findAllXMLNodes("grammar/define/element");
		for (int i = 0; i < nodes.getLength(); i++) {
			MetaData data = app.createMetaDataObject(nodes.item(i), true);
			gener.generate(data);

		}
		gener.HTMLEnd();

	}

}
