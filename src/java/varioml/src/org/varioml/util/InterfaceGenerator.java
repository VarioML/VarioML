package org.varioml.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class InterfaceGenerator {

	public static final String EOL = System.getProperty("line.separator");

	public static final String ANNOTATABLE[] = { "getCommentList", "getDbXrefList" };
	public static final String ANNOTATABLE_METHODS = 
			"	public List<Comment> getCommentList() ;"
			+ EOL + "   public List<DbXref>  getDbXrefList()  ;" + EOL
			+ "   public void setDbXrefList( List<DbXref> dbXref) ; " + EOL
			+ "   public void setCommentList( List<Comment> comment) ; " + EOL
			+ "   public void addDbXref(DbXref item ) ; " + EOL
			+ "   public void addComment(Comment item ) ; " + EOL +

			"";

	public static final String REFERENCEABLE[] = { "get" +
			"Accession", "getSource", "getUri", "getName" };
	public static final String REFERENCEABLE_METHODS = 
			"   public void setAccession( String attr_accession) ; "
			+ EOL
			+ "   public String getAccession() ; "
			+ EOL
			+ "   public void setName( String attr_name) ; "
			+ EOL
			+ "   public String getName() ; "
			+ EOL
			+ "   public void setSource( String attr_source) ; "
			+ EOL
			+ "   public String getSource() ; "
			+ EOL
			+ "   public void setUri( String attr_uri) ; "
			+ EOL
			+ "   public String getUri() ; "
			+ EOL +

			"";

	public static final String ONTOLOGYABLE[] = { "getAccession", "getSource", "getUri", "getTerm" };
	public static final String ONTOLOGYABLE_METHODS = 
			"   public void setAccession( String attr_accession) ; "
			+ EOL
			+ "   public String getAccession() ; "
			+ EOL
			+ "   public void setTerm( String attr_name) ; "
			+ EOL
			+ "   public String getTerm() ; "
			+ EOL
			+ "   public void setSource( String attr_source) ; "
			+ EOL
			+ "   public String getSource() ; "
			+ EOL
			+ "   public void setUri( String attr_uri) ; "
			+ EOL
			+ "   public String getUri() ; "
			+ EOL +

			"";

	public static final String OBSERVABLE[] = { "getEvidenceCodeList", "getProtocolIdList" };
	public static final String OBSERVABLE_METHODS = 
			"	public void setValueList( List<Value> value) ; " 
			+ EOL 
		    + "	public List<Value> getValueList() ;" 
			+ EOL
	        + "	public void addValue(Value item ) ;" 
	        + EOL
			+ "	public void setEvidenceCodeList( List<EvidenceCode> evidenceCode) ;  "
			+ EOL
			+ "	public List<EvidenceCode> getEvidenceCodeList()  ;  "
			+ EOL
			+ "	public void addEvidenceCode(EvidenceCode item ) ;  "
			+ EOL
			+ "	public void setProtocolIdList( List<ProtocolId> protocolId) ;  "
			+ EOL
			+ "	public List<ProtocolId> getProtocolIdList()  ;  "
			+ EOL
			+ "	public void addProtocolId(ProtocolId item ) ;  " 
			+ EOL 			
			+ "	public void setObservationDate( ObservationDate observationDate) ;" 
			+ EOL
			+ "	public ObservationDate getObservationDate() ;"
			+ EOL 
			+ "";

	public static final String VARIANT_CHARACTERISTIC[] = { "getPathogenicityList",
			"addConsequence" };
	public static final String VARIANT_CHARACTERISTIC_METHODS = 
			"   public void setName( VariantName name) ; "
			+ EOL
			+ "   public VariantName getName() ; "
			+ EOL
			+ "   public void setSequence( Sequence sequence) ; "
			+ EOL
			+ "   public Sequence getSequence() ; "
			+ EOL
			+ "   public void setConsequenceList( List<Consequence> consequence) ;  "
			+ EOL
			+ "   public List<Consequence> getConsequenceList()  ;  "
			+ EOL
			+ "   public void addConsequence(Consequence item ) ;  "
			+ EOL
			+ "   public void setPathogenicityList( List<Pathogenicity> pathogenicity) ;  "
			+ EOL
			+ "   public List<Pathogenicity> getPathogenicityList()  ;  "
			+ EOL
			+ "   public void addPathogenicity(Pathogenicity item ) ;  "
			+ EOL
			+ "   public void setSeqChanges( SeqChanges seqChanges) ;  "
			+ EOL
			+ "   public SeqChanges getSeqChanges() ; "
			+ EOL
			+ "   public void setAliases( Aliases aliases) ;  "
			+ EOL
			+ "   public Aliases getAliases() ; "
			+ EOL
			+ "   public void setLocationList( List<Location> location) ;  "
			+ EOL
			+ "   public List<Location> getLocationList()  ;  "
			+ EOL
			+ "   public void addLocation(Location item ) ;  " + EOL + "";

	
	public static final String CONS_VARIANT[] = { "setRefSeq", "setVariantTypeList","getTissueDistribution" }; //extend by REPORTING_VARIANT2
	public static final String CONS_VARIANT_METHODS =
			"	public  void setType( String attr_type)  ; " 
					+ EOL +"	public  String getType()  ; " 
					+ EOL +"	public  void setRefSeq( RefSeq refSeq)  ; " 
					+ EOL +"	public  RefSeq getRefSeq()  ; "

//					+ EOL +"	public  void setVariantTypeList( List<VariantType> variantType)  ; " 
//					+ EOL +"	public  List<VariantType> getVariantTypeList()   ; " 
//					+ EOL +"	public  void addVariantType(VariantType item )  ; " 
				 
//					+ EOL +"	public  void setOriginalId( OriginalId originalId)  ; " 
//					+ EOL +"	public  OriginalId getOriginalId()  ; "
					+ EOL +"	public  void setSequence( Sequence sequence)  ; " 
					+ EOL +"	public  Sequence getSequence()  ; "
					+ EOL +"	public  void setGenotype( Genotype genotype)  ; " 
					+ EOL +"	public  Genotype getGenotype()  ; "
				 
//					+ EOL +"	public  void setVariantDetection( VariantDetection variantDetection)  ; " 
//					+ EOL +"	public  VariantDetection getVariantDetection()  ; "
					+ EOL +"	public  void setTissueDistribution( TissueDistribution tissueDistribution)  ; " 
					+ EOL +"	public  TissueDistribution getTissueDistribution()  ; "
					+ EOL ;
	

	public static final String LSDB_VARIANT_SOURCE_INFO_DETAILS[] = { "getVariantClassList", "addExon","addGeneticOrigin" };
	public static final String LSDB_VARIANT_SOURCE_INFO_DETAILS_METHODS =
			"	public  void setVariantClassList( List<VariantClass> variantClass)  ; " 
					+ EOL +"	public  List<VariantClass> getVariantClassList()   ; " 
					+ EOL +"	public  void addVariantClass(VariantClass item )  ; " 

					+ EOL +"	public  void setExonList( List<Exon> exon)  ; " 
					+ EOL +"	public  List<Exon> getExonList()   ; " 
					+ EOL +"	public  void addExon(Exon item )  ; " 
				 
				 
					+ EOL +"	public  void setRestrictionSite( RestrictionSite restrictionSite)  ; " 
					+ EOL +"	public  RestrictionSite getRestrictionSite()  ; "
				 
					+ EOL +"	public  void setGeneticOriginList( List<GeneticOrigin> geneticOrigin)  ; " 
					+ EOL +"	public  List<GeneticOrigin> getGeneticOriginList()   ; " 
					+ EOL +"	public  void addGeneticOrigin(GeneticOrigin item )  ; " 
					+ EOL ;

	public static final String LSDB_VARIANT_SOURCE_INFO[] = { "getOriginalId", "getOriginalId","getVariantDetection" };
	public static final String LSDB_VARIANT_SOURCE_INFO_METHODS = 
			"	public  void setVariantTypeList( List<VariantType> variantType)  ; " 
					+ EOL +"	public  List<VariantType> getVariantTypeList()   ; " 
					+ EOL +"	public  void addVariantType(VariantType item )  ; " 

					+ EOL +"	public  void setOriginalId( OriginalId originalId)  ; " 
					+ EOL +"	public  OriginalId getOriginalId()  ; "

					+ EOL +"	public  void setVariantDetection( VariantDetection variantDetection)  ; " 
					+ EOL +"	public  VariantDetection getVariantDetection()  ; "
					+ EOL;
	
	public static final String FREQUENCEYABLE[] = { "getFrequencyList" };
	public static final String FREQUENCEYABLE_METHODS = 
			"   public void setFrequencyList( List<Frequency> frequency) ;  "
			+ EOL
			+ "   public List<Frequency> getFrequencyList()  ;  "
			+ EOL
			+ "   public void addFrequency(Frequency item ) ;  " + EOL + "";

	public static final String SOURCABLE[] = { "getSource", "getAliases" };
	public static final String SOURCABLE_METHODS = 
			"   public void setSource( Source source) ;  "
			+ EOL + "   public Source getSource() ; " + EOL + "";

	public static final String IDENTIFIABLE[] = { "getId", "getUri" };
	public static final String IDENTIFIABLE_METHODS = 
			"public void setId( String attr_id) ;  "
			+ EOL + "public String getId() ; " + EOL + "public void setUri( String attr_uri) ;  "
			+ EOL + "public String getUri() ; " + EOL +

			"";

	public static final String SHAREABLE[] = { "getSharingPolicy", "getModificationDate" };
	public static final String SHAREABLE_METHODS = 
			"	public void setSharingPolicy( SharingPolicy sharingPolicy) ;"
			+ EOL
			+ "	public SharingPolicy getSharingPolicy() ;"
			+ EOL
			+ "	public void setCreationDate( org.varioml.util.VMLDate creationDate) ;"
			+ EOL
			+ "	public org.varioml.util.VMLDate getCreationDate() ;"
			+ EOL
			+ "	public void setModificationDate( org.varioml.util.VMLDate modificationDate) ;"
			+ EOL + "	public org.varioml.util.VMLDate getModificationDate() ;" + EOL +

			"";

	public static final String OBSERVATION_TARGET[] = { "getPopulationList", "getStrain" };
	public static final String OBSERVATION_TARGET_METHODS = 
			"	public void setOriginalId( OriginalId originalId);"
			+ EOL
			+ "	public OriginalId getOriginalId() ;"
			+ EOL
			+ "	public void setRole( Role role) ;"
			+ EOL
			+ "	public Role getRole() ;"
			+ EOL
			+ "	public void setRelationshipList( List<Relationship> relationship) ;"
			+ EOL
			+ "	public List<Relationship> getRelationshipList();  "
			+ EOL
			+ "	public void addRelationship(Relationship item ) ;"
			+ EOL
			+ "	public void setOrganism( Organism organism) ;"
			+ EOL
			+ "	public Organism getOrganism() ;"
			+ EOL
			+ "	public void setStrain( Strain strain) ;"
			+ EOL
			+ "	public Strain getStrain() ;"
			+ EOL
			+ "	public void setCultivar( Cultivar cultivar) ;"
			+ EOL
			+ "	public Cultivar getCultivar() ;"
			+ EOL
			+ "	public void setPhenotypeList( List<Phenotype> phenotype) ;"
			+ EOL
			+ "	public List<Phenotype> getPhenotypeList() ; "
			+ EOL
			+ "	public void addPhenotype(Phenotype item ) ;"
			+ EOL
			+ "	public void setObservationList( List<Observation> observation) ; " 
			+ EOL
			+ "	public List<Observation> getObservationList()  ; " 
			+ EOL
			+ "	public void addObservation(Observation item ) ; "
			+ EOL
			+ "	public void setPopulationList( List<Population> population) ;"
			+ EOL
			+ "	public List<Population> getPopulationList() ;"
			+ EOL
			+ "	public void addPopulation(Population item ) ;"
			+ EOL
			+ "	public void setVariantList( List<Variant> variant) ;"
			+ EOL
			+ "	public List<Variant> getVariantList() ; "
			+ EOL
			+ "	public void addVariant(Variant item ) ;"
			+ EOL
			+ "	public void setVariantGroupList( List<VariantGroup> variantGroup) ;"
			+ EOL
			+ "	public List<VariantGroup> getVariantGroupList()  ;"
			+ EOL
			+ "	public void addVariantGroup(VariantGroup item ) ;" + EOL +

			"";

	public static final List<String[]> interfaces = new ArrayList<String[]>();

	public static boolean isSame(String[] signature, Method m[]) {
		assert (signature.length > 0);

		boolean foundAll = true;
		for (int i = 0; i < signature.length; i++) {
			boolean found = false;
			for (int j = 0; (j < m.length && !found); j++) {
				found = signature[i].equals(m[j].getName());
			}
			foundAll = foundAll && found;
		}
		return foundAll;

	}

	public void generateCode(String interfaceName, String interfaceDefs, String code) {
		boolean overwrite = true;

		File fileOut = new File("src/org/varioml/jaxb/" + interfaceName + ".java");
		if (fileOut.exists()) {
			if (overwrite) {
				fileOut.delete();
			} else {
				Util.log(InterfaceGenerator.class, "File " + fileOut.getPath() + " not over witten");
			}
		}
		try {
			fileOut.createNewFile();
			PrintStream out = new PrintStream(fileOut);
			out.println("package org.varioml.jaxb;");
			out.println("");
			out.println("import java.util.*;");

			out.println("public interface " + interfaceName + " " + interfaceDefs + " {");
			out.println("");
			out.println(code);
			out.println("");
			out.println("}");
			out.close();
		} catch (Exception e) {
			Util.fatal(InterfaceGenerator.class, e);
		}

	}

	private void patchSource(Class c, String interfaces) {
		String name = "src/" + c.getName().replaceAll("\\.", "/") + ".java";
		String MARK = "/**/";
		// String test = "+++"+MARK+name+MARK+"////" ;
		// System.err.println(
		// test.replaceAll("/\\*\\*/.+/\\*\\*/","/**/HEY/**/" )) ;
		String regex = "(\\s*public\\s+class\\s+\\S+\\s*/\\*\\*/)\\s*(implements\\s*[\\S,]*)?\\s*(/\\*\\*/.*)?$";
		Pattern pattern = Pattern.compile(regex);

		File f = new File(name);
		try {
			FileReader fread = new FileReader(f);
			BufferedReader fr = new BufferedReader(fread);
			String line = fr.readLine();
			StringBuffer bf = new StringBuffer();
			while (line != null) {

				if (line.matches("\\s*public\\s+class.+")) {
					if (interfaces.startsWith("implements")) {
						// just replace everything between the comments
						line = line.replaceAll("/\\*\\*/.*/\\*\\*/", "/**/" + interfaces + "/**/");
					} else {
						Matcher matcher = pattern.matcher(line);
						if (matcher.find()) {
							if (matcher.group(3) == null)
								Util.fatal(InterfaceGenerator.class,
										"Check parsers: " + matcher.group(0));
							if (interfaces.contains(","))
								Util.fatal(InterfaceGenerator.class,
										"multiple interfaces not supported here");
							String implLine = "";
							implLine = matcher.group(2);
							if (implLine == null) {
								implLine = "implements " + interfaces;
							} else {
								if (!implLine.contains(interfaces)) {
									implLine = implLine + "," + interfaces;
								}
							}
							line = matcher.group(1) + implLine + matcher.group(3);
							System.err.println("Interface added: " + line);
						} else {
							System.err.println("NOT FOUND. Line not changed: " + line);
						}
					}
				}
				bf.append(line);
				line = fr.readLine();
				if (line != null)
					bf.append(EOL);
			}
			fread.close();
			PrintStream ps = new PrintStream(f);
			ps.print(bf);
			ps.close();
		} catch (Exception e) {
			Util.fatal(InterfaceGenerator.class, e);
		}

	}

	public void generateInterfaces() {

		generateCode("VmlAnnotatable", "", ANNOTATABLE_METHODS);
		generateCode("VmlIdentifiable", "", IDENTIFIABLE_METHODS);
		generateCode("VmlShareable", "", SHAREABLE_METHODS);
		generateCode("VmlObservationTarget", "extends VmlIdentifiable, VmlAnnotatable",
				OBSERVATION_TARGET_METHODS);
		generateCode("VmlDbXRef", "extends VmlAnnotatable", REFERENCEABLE_METHODS);
		generateCode("VmlOntologyTerm", "extends VmlAnnotatable", ONTOLOGYABLE_METHODS);
		generateCode("VmlObservable", "extends VmlAnnotatable", OBSERVABLE_METHODS);
		generateCode("VmlSimpleObservation", "extends VmlObservable,VmlOntologyTerm", "");
		generateCode("VmlVariantObservation",
				"extends VmlAnnotatable,VmlObservable,VmlIdentifiable",
				VARIANT_CHARACTERISTIC_METHODS);

		generateCode("VmlLsdbSourceInfo", "",
				LSDB_VARIANT_SOURCE_INFO_METHODS);

		generateCode("VmlLsdbSourceInfo2", "",
				LSDB_VARIANT_SOURCE_INFO_DETAILS_METHODS);


		generateCode("VmlVariantEvent", "extends VmlVariantObservation, VmlLsdbSourceInfo, VmlLsdbSourceInfo2,VmlFrequency",
				"");
		
		generateCode("VmlSimpleVariantEvent", "extends VmlVariantObservation,VmlLsdbSourceInfo",
				CONS_VARIANT_METHODS);

		generateCode("VmlReportingVariant", "extends VmlSimpleVariantEvent,VmlVariantEvent",
				CONS_VARIANT_METHODS);
		generateCode("VmlFrequency", "", FREQUENCEYABLE_METHODS);

	}

	public static void main(String[] args) throws Exception {

		String dir = "src/org/varioml/jaxb";
		String build = "org.varioml.jaxb";
		String Idir = "src/org/varioml/obj";
		String pkg = "org.varioml.obj";

		File directory = new File(dir);
		if (!directory.isDirectory())
			Util.fatal(InterfaceGenerator.class, "not dir");

		String filename[] = directory.list();

		InterfaceGenerator gen = new InterfaceGenerator();
		gen.generateInterfaces();

		for (int i = 0; i < filename.length; i++) {

			String f = filename[i];
			if (f.endsWith(".java")) {
				String className = build + "." + f.replaceAll(".java$", "");
				try {
					Class c = Class.forName(className); // note rember to
														// compile generated
														// classes/interfaces
														// first
					if (!c.isInterface()) {

						Method m[] = c.getDeclaredMethods();
						if (isSame(LSDB_VARIANT_SOURCE_INFO_DETAILS, m) && isSame(CONS_VARIANT,m) && isSame(LSDB_VARIANT_SOURCE_INFO, m)) {
							System.err.println("LSDB Reporting Variant: " + c.getName());
							gen.patchSource(c, "implements VmlReportingVariant ");
						} else if (isSame(LSDB_VARIANT_SOURCE_INFO_DETAILS, m) && isSame(VARIANT_CHARACTERISTIC,m)) {
							System.err.println("VariantEvent: " + c.getName());
							gen.patchSource(c, "implements VmlVariantEvent");
						} else if (isSame(CONS_VARIANT, m) && isSame(LSDB_VARIANT_SOURCE_INFO,m)) {
							System.err.println("VmlSimpleVariantEvent (ConsVariant): " + c.getName());
							gen.patchSource(c, "implements VmlSimpleVariantEvent");
						} else if (isSame(VARIANT_CHARACTERISTIC, m)) {
							System.err.println("Variant observation: " + c.getName());
							gen.patchSource(c, "implements VmlVariantObservation ");
						} else if (isSame(OBSERVATION_TARGET, m)) {
							System.err.println("Observation target: " + c.getName());
							gen.patchSource(c, "implements VmlObservationTarget ");
						} else if (isSame(OBSERVABLE, m) && isSame(ONTOLOGYABLE, m)) {
							System.err.println("Observation: " + c.getName());
							gen.patchSource(c, "implements VmlSimpleObservation ");
						} else if (isSame(ONTOLOGYABLE, m)) {
							System.err.println("Ontology term: " + c.getName());
							gen.patchSource(c, "implements VmlOntologyTerm ");
						} else if (isSame(REFERENCEABLE, m)) {
							System.err.println("VmlDbXRef: " + c.getName());
							gen.patchSource(c, "implements VmlDbXRef ");
						} else if ( isSame(ANNOTATABLE,m)) {
							System.err.println("VmlAnnotatable: " + c.getName());
							gen.patchSource(c, "implements VmlAnnotatable ");							
						}
						if (isSame(FREQUENCEYABLE, m) && !(isSame(LSDB_VARIANT_SOURCE_INFO_DETAILS, m) && isSame(LSDB_VARIANT_SOURCE_INFO,m))) {
							System.err.println("Frequencyable: " + c.getName());
							gen.patchSource(c, "VmlFrequency");
						}
						if (isSame(SHAREABLE, m)) {
							System.err.println("Shareable: " + c.getName());
							gen.patchSource(c, "VmlShareable");
						}

					}

				} catch (Exception ex) {
					System.err.println(ex.getMessage());
				}
			}

		}

	}

}
