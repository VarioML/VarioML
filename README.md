VarioML - formatting genetic data in JSON or XML
================================================

VarioML is a flexible framework which can be used as a template for developing
 serialization formats for variation data focusing on the LSDB (Locus Specific
 Mutation Database) use cases.
Several data formats come pre-defined, but VarioML elements function as building
 blocks that can be restructured to fit different implementations to build new
 data exchange formats - from patient-centered formats to aggregated variant
 data formats.
Originally an XML/RelaxNG framework, VarioML has been rebuilt using JSON Schema.

VarioML was first developed in the
 [GEN2PHEN](https://en.wikipedia.org/wiki/GEN2PHEN) program from 2011 - 2013 and
 it was based on an observation model developed there.
A simplified UML model can be found
[here](https://raw.github.com/VarioML/VarioML/master/xml/lsdb_main/uml/varioml.jpg).
VarioML has since been reimplemented in JSON, and we're currently working on
 improviding the documentation with more examples.

__VarioML is a collaborative, community-driven specification in active
development.__
If you'd like to collaborate with us, please let us know through *admin* at
 *varioml.org*, or feel free to [create an issue](/VarioML/VarioML/issues/new).

When using or discussing VarioML, please refer to:
> Byrne et al (2012).
> VarioML framework for comprehensive variation data representation and exchange.
> [BMC Bioinformatics. 2012 Oct 3;13:254](https://pubmed.ncbi.nlm.nih.gov/23031277/).
> doi: [10.1186/1471-2105-13-254](https://doi.org/10.1186/1471-2105-13-254). 


#### Important:
- All new implementations should use validation to ensure consistency with the
  core specification.
  When using Schematron, contact *admin* at *varioml.org* for help with
  generating validation scripts.
- Use standard ontologies for the content, such as the
  [Sequence Ontology](http://www.sequenceontology.org/) and
  [VariO](http://variationontology.org/).
  Some LSDB specific terms are defined using
  [SKOS vocabularies](https://github.com/VarioML/VarioML/tree/master/ontology/skos).
- Source attributes in database xrefs (such as `gene` and `ref_seq`) as well as
  ontology terms should use database abbreviations defined in the
  [MIRIAM registry](http://identifiers.org).
  For example, use `hgnc.symbol` for gene names, `refseq` for NCBI reference
  sequences, and `obo.so` for Sequence Ontology references.



## JSON implementation

The JSON implementation is a clean redesign from the VarioML XML format, and our
 current focus.
A JSON schema is provided to validate your data files, to provide descriptions
 and examples, and as the template for the documentation. 


#### VarioML-JSON is implemented in [LOVD3](http://github.com/LOVDnl/LOVD3).

- LOVD3 uses VarioML in all its recent APIs (both the submission API first
  released in 2016 and the new data retrieval API to be released in 2021).
  The new data retrieval API uses the
  [GA4GH Table Discovery API](https://github.com/ga4gh-discovery/ga4gh-search)
  wrapped around VarioML data objects.
- See an [example implementation](json/examples/lovd.json) written
  for the LOVD3 submission API.
- For the basic JSON Schema, see either the
  [LSDB object's JSON Schema example](json/examples/lovd.json-schema) based on
  the XML implementation, or the clean reimplementation which is still 
  [work in progress](json/schemas/v.2.0/LSDB.json).



## XML implementation

A translation for EXI is supported using the
 [Excificient](http://exificient.github.io/) library.
XML validation is supported using Schematron.


__VarioML-XML is implemented in [Café Variome](http://cafevariome.org).__
See [the specification](https://web.archive.org/web/20160803110941/http://varioml.org/cafevariome_minspec.htm)
 for the implementation.


#### Apps

View a collection of [demo applications](https://github.com/VarioML/Apps).
Also see [validation tools](https://github.com/VarioML/VarioML/tree/master/xml/cafe_variome/validator).



## News
- 2021-03-02: We'll be updating the documentation on the JSON implementation.
  Please bear with us.
- 2016-09-10: Libraries updated. NEMDB Examples added.
- 2012-08-19: Population changed to singleton in frequency.
  This does not have an impact in any existing XML implementations (the property
  is not used).
- 2012-08-08: Gene has changed to a list of genes.
  One variant may have impact on more than one gene.
- 2012-08-08: [EXI](http://www.w3.org/XML/EXI/) binary XML support added.
  EXI can reduce the size of memory usage by 3-~10 times, already even without
  compression.
- 2012-02-09: JAXB / JSON (based on Jackson) API implementation is in
  org.varioml.jaxb folder.
  The code has not been fully tested.
  The SimpleXML implementation will be retired (support is possible though).
- Comments/feedback: admin <> varioml.org.
  Please send us an email if you are using VarioML so that we can accommodate
  your requirements!



## Funding

VarioML has received funding from the European Community's Seventh Framework
Programme (FP7/2007-2013) under grant agreement number 200754 - the
[GEN2PHEN](https://en.wikipedia.org/wiki/GEN2PHEN) project.
