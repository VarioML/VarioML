VarioML
=======
VarioML is a XML/RelaxNG based framework which can be used as a template for developing serialization
formats for variation data focusing on the LSDB (Locus Specific Mutation Database) use cases. Translations
for JSON and EXI are supported using [Jackson](https://github.com/FasterXML/jackson) and
[Excificient](http://exificient.github.io/). VarioML is flexible and provides common building blocks for different
implementations, which should be fixed further using appropriate schema definition languages like Schematron.
VarioML is developed in [GEN2PHEN](http://www.gen2phen.org/) program and it based on an observation
model developed there. Simplified UML mode is [here](https://raw.github.com/VarioML/VarioML/master/xml/lsdb_main/uml/varioml.jpg)


__VarioML is a collaborative, community-driven specification in active development.__
Your first step should be to contact *admin* at *varioml.org*, as documentation is not yet complete.

__Important__:
- All new implementations should use validation to ensure consistency with the core specification. Contact *admin* at *varioml.org* for help with generating validation scripts using Schematron.
- Use standard ontologies for the content, such as the [Sequence Ontology](http://www.sequenceontology.org/) and
 [VariO](http://variationontology.org/). Some LSDB spesific terms are defined using [SKOS vocabularies](https://github.com/VarioML/VarioML/tree/master/ontology/skos)
- Source attributes in database xrefs (such as *gene* and *ref_seq*) as well as ontology terms should use database abbreviations defined in the [MIRIAM registry](http://identifiers.org). For example, use *hgnc.symbol* for gene names, *refseq* for NCBI reference sequences, and *obo.so* for Sequence Ontology references.

__VarioML is currently implemented in [Café Variome](http://cafevariome.org).__
- The specification for the implementation is here: http://varioml.org/cafevariome_minspec.htm.
- A Schematron implementation is here: https://github.com/VarioML/VarioML/tree/master/xml/cafe_variome/validator
- An online tool that performs client-side (secure) validation of XML against the Café Variome schema is here: http://varioml.org/validator.htm


__More information on VarioML can be found at:__ http://varioml.org/

VarioML has received funding from the European Community's Seventh Framework Programme (FP7/2007-2013)
under grant agreement number 200754 - the [GEN2PHEN](http://gen2phen.o) project.




## Apps

Demo applications can be found here https://github.com/VarioML/Apps.
Also see validation tools here: https://github.com/VarioML/VarioML/tree/master/xml/cafe_variome/validator, and here: http:varioml.org/validator.htm

## News
- 10.9.2016 Libraries updated. NEMDB Examples added
- 19.8.2012 Population changed to singleton in frequency. Do not have impact in existing XML implementations (property not used)
- 8.8.2012 Gene has changed to list of genes. One mutation may have impact on more than one genes
- 8.8.2012 EXI (http://www.w3.org/XML/EXI/) binary XML support added. EXI can reduce the size of disk and memory usage by 3-~10 times, already without compression.
- 9.2.2012: JAXB / JSON (based on Jackson) API implementation is in org.varioml.jaxb folder. Code is not fully tested. SimpleXML implementation will be retired (support is possible though)

- Note: Version 1.0 is now on a separate branch. The main trunk is heading towards release 2.0. The only difference between 1.0 and 2.0 is that the /variant/ element can have haplotype elements containing variants which are in the /cis/ position. Namespaces will be kept the same.

- Comments/feedback: admin <> varioml.org. Please send email if you are using the software so that we can accommodate your requirements!
