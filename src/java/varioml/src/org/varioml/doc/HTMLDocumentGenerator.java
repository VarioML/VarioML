package org.varioml.doc;

//todo solve library problem. xerces implementation do not work with this. Remove xercesImpl.jar from classpath

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Node;

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
		p("     <link rel=\"stylesheet\" href=\"_files/xmlns-style.css\" type=\"text/css\">");
		p("     <link rel=\"stylesheet\" href=\"_files/style.css\" type=\"text/css\">");
		p("     <link rel=\"stylesheet\" href=\"_files/style2.css\" type=\"text/css\">");
		p("     </head>");
		p("    <body>");
		p("    <H1>VarioML Documentation</H1>");
		p("    <p> ~ contents here ~ </p>");
		p("    <hr/>");	
	}

	public void HTMLEnd() {
		p("    </body>");
		p("</html>)");
	}

	public void generate(MetaData data) {
		String type = getType( data.patternList);
		if ( type.length() >0 ) {
			type = " (<a name='"+type+"'><em>"+type+"</em></a>)";
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
								+ (d.properties.size()> 0 ? RNGMetadataAPI.propertiesToString(d):"<em>Foreign XML nodes</em>") + "</td></tr>");
						;

					}
				}
			}
			p("        </table>");
			p("     </div>");
		}
		if ( data.hasTextNode ) {
			//todo: improve this... type etc.
			p("</p><div class='text'><h3>text node of type " +data.dataType+ " </h3></div>") ;
		}
		p("     </p><div class='doc'> <h3>Documentation</h3>" + data.documentation + "</div><hr/>");

	}

	//get type from the list of name of gramma patterns. Patterns are ordered from top to down
	// todo: check .. there can be problems in here...
	private String getType(List<String> patternList) {
		if ( patternList.size() == 0) return "";
		
		return patternList.get(0);
	}

	public static void main(String[] args) throws Exception {
		if (args.length  < 2)
			Util.fatal(HTMLDocumentGenerator.class, "Usage: java  -jar VarioML.jar file-in.rng[,file..] file-out.html");
		List<String> argsStr = new ArrayList<String>();
		for ( int  i = 0 ; i < args.length-1 ; i++) {
			System.err.println( args[i]);
			argsStr.add(args[i]);
		}
		RNGMetadataAPI app = RNGMetadataAPI.createInstance(argsStr.toArray( new String[args.length-1]));

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

		HTMLDocumentGenerator gener = new HTMLDocumentGenerator(args[ args.length - 1]);
		gener.HTMLHeader("VarioML");

		List<org.w3c.dom.Node> nodes = app.findAllXMLNodesOrDie("grammar/define/element");
		for (int i = 0; i < nodes.size(); i++) {
			MetaData data = app.createMetaDataObject(nodes.get(i), true);
			gener.generate(data);

		}
		gener.HTMLEnd();

	}

}
