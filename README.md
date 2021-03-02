VarioML
=======

VarioML is a XML/RelaxNG and JSON based framework which can be used as a
 template for developing serialization formats for variation data focusing on
 the LSDB (Locus Specific Mutation Database) use cases.
A translation for EXI is supported using the
 [Excificient](http://exificient.github.io/) library.
VarioML is flexible and provides common building blocks for different
 implementations.
XML validation is supported using Schematron.
JSON validation is supported using JSON Schema.
VarioML was developed in the [GEN2PHEN](https://en.wikipedia.org/wiki/GEN2PHEN)
 program and it was based on an observation model developed there.
A simplified UML model can be found
 [here](https://raw.github.com/VarioML/VarioML/master/xml/lsdb_main/uml/varioml.jpg).

__VarioML is a collaborative, community-driven specification in active
 development.__
If you'd like to collaborate with us, your first step should be to contact
 *admin* at *varioml.org*, as documentation is not yet complete.


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


#### VarioML (using XML) is implemented in [Café Variome](http://cafevariome.org).

[//]: # (FIXME: The first and third link here are broken.)

- See [the specification](http://varioml.org/cafevariome_minspec.htm) for the
  implementation.
- See the [Schematron implementation](https://github.com/VarioML/VarioML/tree/master/xml/cafe_variome/validator).
- Or you can [validate your XML](http://varioml.org/validator.htm) against the
  Café Variome schema.


#### VarioML (using JSON) is implemented in [LOVD3](http://github.com/LOVDnl/LOVD3).

- LOVD3 uses VarioML in all its new APIs (both the submission API first released
  in 2016 and the new data retrieval API to be released in 2021).
- See an [example implementation](blob/master/json/examples/lovd.json) written
  for the LOVD3 submission API.
- See the [JSON Schema](blob/master/json/examples/lovd.json-schema).


#### More information on VarioML can be found at: http://varioml.org

VarioML has received funding from the European Community's Seventh Framework
 Programme (FP7/2007-2013) under grant agreement number 200754 - the
 [GEN2PHEN](https://en.wikipedia.org/wiki/GEN2PHEN) project.



## Apps

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
