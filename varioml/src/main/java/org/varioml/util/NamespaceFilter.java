package org.varioml.util;
import javax.xml.bind.Marshaller;

import org.apache.xerces.jaxp.JAXPConstants;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.XMLFilterImpl;


public class NamespaceFilter extends XMLFilterImpl {

    private String usedNamespaceUri;
    private boolean addNamespace;

    //State variable
    private boolean addedNamespace = false;

    public NamespaceFilter(String namespaceUri,
            boolean addNamespace) {
        super();
        
        if (addNamespace)
            this.usedNamespaceUri = namespaceUri;
        else 
            this.usedNamespaceUri = ""; 
        this.addNamespace = addNamespace;
    }

    @Override
    public void setContentHandler (ContentHandler handler)
    {
        super.setContentHandler(handler) ;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        if (addNamespace) {
            startControlledPrefixMapping();
        }
    }



    @Override
    public void startElement(String ns, String elemName, String elemQualfiedName,
            Attributes attribs) throws SAXException {
    	
        super.startElement(this.usedNamespaceUri, elemName, elemQualfiedName, attribs);
    }

    @Override
    public void endElement(String arg0, String arg1, String arg2)
            throws SAXException {

        super.endElement(this.usedNamespaceUri, arg1, arg2);
    }

    @Override
    public void startPrefixMapping(String prefix, String url)
            throws SAXException {

        if (addNamespace) {
            this.startControlledPrefixMapping();
        } else {
            //Remove the namespace, i.e. don't call startPrefixMapping for parent!
        }

    }
    
    //http://stackoverflow.com/questions/11968399/how-to-make-jaxb-unmarshaller-to-ignore-prefixes
    //http://forum.springsource.org/showthread.php?85694-Namespace-prefix-Mapping-Jaxb2Marshaller
    private void startControlledPrefixMapping() throws SAXException {

        if (this.addNamespace && !this.addedNamespace) {
            //We should add namespace since it is set and has not yet been done.
            super.startPrefixMapping("", this.usedNamespaceUri); // this do not do anything ?
            //Make sure we dont do it twice
            //System.err.println("Controlled: ") ;
            this.addedNamespace = true;
        }
    }

}
