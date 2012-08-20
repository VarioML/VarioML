<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE rdf:RDF [
<!ENTITY vmo "http://purl.org/varioml/pharm/1.0/" > 
<!ENTITY vmkb "http://varioml.org/varioml_kb.owl#">
<!ENTITY PMID "http://purl.org/obo/owl/PMID#" >
<!ENTITY dc "http://purl.org/dc/elements/1.1/" >
<!ENTITY owl "http://www.w3.org/2002/07/owl#" >
<!ENTITY owl11 "http://www.w3.org/2006/12/owl11#" >
<!ENTITY xsd "http://www.w3.org/2001/XMLSchema#" >
<!ENTITY dl "http://ontology.dumontierlab.com/" >
<!ENTITY pmid "http://purl.org/obo/owl/PMID#PMID_" >
<!ENTITY owl11xml "http://www.w3.org/2006/12/owl11-xml#" >
<!ENTITY rdfs "http://www.w3.org/2000/01/rdf-schema#" >
<!ENTITY rdf "http://www.w3.org/1999/02/22-rdf-syntax-ns#" >
<!ENTITY snap "http://www.ifomis.org/bfo/1.0/snap#" >
<!ENTITY span "http://www.ifomis.org/bfo/1.0/span#" >
<!ENTITY fn "http://www.w3.org/2005/xpath-functions" >
]>
<!--
    https://raw.github.com/VarioML/VarioML/master/ontology/owl/varioml.owl
    -->
<!--/Users/muilu/l/ontologies/varioml -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:vmkb="http://varioml.org/ontology/owl/varioml_kb.owl#"
    xmlns:vmo="http://purl.org/varioml/pharm/1.0/"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" 
    xmlns:vml="http://varioml.org/xml/1.0"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:owl="http://www.w3.org/2002/07/owl#"
    xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:foaf="http://xmlns.com/foaf/0.1/"
    xmlns:PMID="http://purl.org/obo/owl/PMID#" xmlns:pmid="&PMID;PMID_"
    xmlns:owl11="http://www.w3.org/2006/12/owl11#"
    xmlns:owl11xml="http://www.w3.org/2006/12/owl11-xml#"
    xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
    xmlns:snap="http://www.ifomis.org/bfo/1.0/snap#"
    xmlns:span="http://www.ifomis.org/bfo/1.0/span#" xmlns:dl="http://ontology.dumontierlab.com/"
    exclude-result-prefixes="xs" version="2.0">
    <!-- remeber http://www.dpawson.co.uk/xsl/sect2/N2696.html  -->
    <xsl:output method="xml" indent="yes"/>

    <!--
        http://bio2rdf.org/uniprot:P26838
        http://bio2rdf.org/hugo:COL1A1
        http://bio2rdf.org/hgnc:12345
        http://bio2rdf.org/omim:12345
    -->
    <xsl:variable name="hugo_uri">http://identifiers.org/hgnc.symbol:</xsl:variable>
    <xsl:variable name="b2r_hugo_uri">http://bio2rdf.org/hugo:</xsl:variable>
    <xsl:variable name="hgnc_uri">http://identifiers.org/hgnc:</xsl:variable>
    <xsl:variable name="b2r_hgnc_uri">http://bio2rdf.org/hgnc:</xsl:variable>
    <xsl:variable name="uniprot_uri">http://identifiers.org/uniprot:</xsl:variable>
    <xsl:variable name="b2r_uniprot_uri">http://bio2rdf.org/uniprot:</xsl:variable>
    <xsl:variable name="omim_uri">http://identifiers.org/omim:</xsl:variable>
    <xsl:variable name="b2r_omim_uri">http://bio2rdf.org/omim:</xsl:variable>
    <xsl:variable name="refseq_uri">http://identifiers.org/refseq:</xsl:variable>
    <xsl:variable name="b2r_refseq_uri">http://bio2rdf.org/refseq:</xsl:variable>
    <xsl:variable name="genbank_uri">http://bio2rdf.org/genbank:</xsl:variable>
    
    <xsl:template match="/">
        <rdf:RDF>

            <owl:Ontology rdf:about="">
                <dc:publisher rdf:datatype="&xsd;string">http://varioml.org</dc:publisher>
                <dc:language>en</dc:language>
                <dc:creator>VarioML cv2po-template</dc:creator>
                <dc:date rdf:datatype="&xsd;string">Feb 1, 2008</dc:date>
                <dc:title>Cafe variome knowledge base</dc:title>
                <owl:versionInfo rdf:datatype="&xsd;string">1.0</owl:versionInfo>
                <dc:description>Instantiated variome ontology</dc:description>
                <dc:creator rdf:datatype="&xsd;string">Juha Muilu (xslt)</dc:creator>
                <owl:imports rdf:resource="&dl;pharmacogenomics-complex"/>
                <owl:imports rdf:resource="&vmo;"/>
            </owl:Ontology>

            <!-- http://www.w3.org/2002/07/owl#Thing -->

            <owl:Class rdf:about="&owl;Thing"/>


            <!-- take out unique genes -->
            <xsl:for-each-group select="vml:cafe_variome/vml:variant/vml:gene"
                group-by="./@accession">
                <xsl:apply-templates select="."/>
            </xsl:for-each-group>
            
            <xsl:for-each-group select="vml:cafe_variome/vml:variant//vml:ref_seq"
                group-by="./@accession">
                <xsl:apply-templates select="."/>
            </xsl:for-each-group>
            
            <xsl:for-each-group select="vml:cafe_variome/vml:variant//vml:phenotype"
                group-by="./@term">
                <xsl:apply-templates select="."/>
            </xsl:for-each-group>

            <xsl:for-each select="vml:cafe_variome/vml:variant//vml:location">
                <xsl:apply-templates select="."/>
            </xsl:for-each>
            
            <xsl:apply-templates select="vml:cafe_variome"/>
        </rdf:RDF>
    </xsl:template>

    <xsl:template match="vml:cafe_variome">
        <xsl:apply-templates select="vml:variant"/>
    </xsl:template>


    <!-- 
        VARIANT
        Gene variant which has the mutation ( vml:variant)
    -->
    <xsl:template match="vml:variant">
        
        <xsl:element name="dl:GeneVariant">

            
            <xsl:attribute name="rdf:about">
                <xsl:call-template name="GenerateLocalURI">
                    <!-- generate local uri for URI for GeneVariant using URI of variant, if available, gene accnum and variant name-->
                    <xsl:with-param name="primary" select="@uri"/>
                    <xsl:with-param name="secondary" select="vml:gene/@accession"/>
                    <xsl:with-param name="tertiary" select="vml:name"/>
                </xsl:call-template>
                
            </xsl:attribute>
            
            <xsl:element name="dl:isVariantOf">
                <!-- gene variant is variant of gene -->
                <xsl:attribute name="rdf:resource">
                    <xsl:value-of select="vmkb:dbxref(vml:gene/@accession,vml:gene/@source)"/>
                </xsl:attribute>
            </xsl:element>

            <xsl:element name="dl:hasPart">
                <!-- ... GeneVariant has the Variant  as a part -->
                <xsl:attribute name="rdf:resource">                    
                    <xsl:call-template name="GenerateLocalURI">
                        <!-- generate local uri for URI for the Variant using URI of variant, if available, ref_seq accnum and variant name-->
                        <xsl:with-param name="primary" select="@uri"/>
                        <xsl:with-param name="secondary" select="vml:ref_seq/@accession"/>
                        <xsl:with-param name="tertiary" select="vml:name"/>
                    </xsl:call-template>                    
               </xsl:attribute>
            </xsl:element>

            <xsl:for-each select="vml:pathogenicity/vml:phenotype">
                <!-- here we loop over all pathogenicity phenotype elements and assign disease dispositions variant may have-->
                <xsl:if test="not(starts-with(lower-case(../@term),'no'))"> <!-- todo: no clear...fix -->
                    <xsl:element name="dl:hasDisposition">
                        <xsl:attribute name="rdf:resource"> 
                            <xsl:value-of select="vmkb:ontologyterm(@accession,@source,@term)"></xsl:value-of>
                        </xsl:attribute>
                    </xsl:element>                    
                </xsl:if>
            </xsl:for-each>                        
 
            <xsl:for-each select="vml:panel//vml:phenotype">
                <!-- here we loop over all phenotype elements of observation targets and assign co_exist relationships -->
                <xsl:if test="not(starts-with(lower-case(../@term),'no'))"> <!-- todo: fix -->
                    <xsl:element name="vmo:coexist">
                        <xsl:attribute name="rdf:resource"> 
                            <xsl:value-of select="vmkb:ontologyterm(@accession,@source,@term)"></xsl:value-of>
                        </xsl:attribute>
                    </xsl:element>                    
                </xsl:if>
            </xsl:for-each>                        
            
        </xsl:element>

        <xsl:element name="vmo:Variant">
            <!-- actual mutation  -->
            <xsl:attribute name="rdf:about">
                <xsl:call-template name="GenerateLocalURI">
                    <!-- generate local uri for URI for the Variant using URI of variant, if available, ref_seq accnum and variant name-->
                    <xsl:with-param name="primary" select="@uri"/>
                    <xsl:with-param name="secondary" select="vml:ref_seq/@accession"/>
                    <xsl:with-param name="tertiary" select="vml:name"/>
                </xsl:call-template>                    
            </xsl:attribute>
            <xsl:call-template name="Comment">
                <xsl:with-param name="text">Variant</xsl:with-param>
            </xsl:call-template>
            <xsl:call-template name="Label">
                <xsl:with-param name="text">
                    <xsl:value-of
                        select="concat(vml:ref_seq/@accession,':',encode-for-uri(vml:name))"/>
                </xsl:with-param>
            </xsl:call-template>

            <xsl:variable name="refseq">
                <xsl:value-of select="vml:ref_seq/@accession"/>
            </xsl:variable>
            
            <!-- locate variant on reference sequence if it can be done  -->
            <xsl:if test="$refseq">
                <xsl:analyze-string select="vml:name" regex="(\d+)-?(\d+)?">
                    <xsl:matching-substring>
                        <xsl:if test="regex-group(1)">
                            
                            <xsl:variable name="end">
                                <xsl:value-of select="regex-group(2)"/>
                            </xsl:variable>
                            
                            <xsl:element name="dl:isSpatiallyLocatedIn">
                                <xsl:attribute name="rdf:resource">
                                    <xsl:value-of select="vmkb:refSeqId($refseq,regex-group(1),$end)"/>                                      
                                </xsl:attribute>
                            </xsl:element>                            
                        </xsl:if>
                    </xsl:matching-substring>                    
                </xsl:analyze-string>                
            </xsl:if>
            
            
            
            <xsl:if test="vml:location">
                <!-- IMPLEMENT -->
                <xsl:element name="dl:isSpatiallyLocatedIn">
                    <xsl:attribute name="rdf:resource">
                        <xsl:value-of select="vmkb:refSeqId(vml:location/vml:ref_seq/@accession,vml:location/vml:start/.,vml:location/vml:end/.)"/>                                        
                    </xsl:attribute>
                </xsl:element>
            </xsl:if>
            
          </xsl:element>
        

        <xsl:variable name="refseq">
            <xsl:value-of select="vml:ref_seq/@accession"/>
        </xsl:variable>
        <xsl:variable name="ref_uri">
            <xsl:value-of select="vml:ref_seq/@uri"/>
        </xsl:variable>
        
        <xsl:variable name="ref_source">
            <xsl:value-of select="vml:ref_seq/@source"/>
        </xsl:variable>
        
        <xsl:analyze-string select="vml:name" regex="(\d+)-?(\d+)?">
            <xsl:matching-substring>
                <xsl:if test="regex-group(1)">
                    
                    <xsl:variable name="end">
                        <xsl:value-of select="regex-group(2)"/>
                    </xsl:variable>
                    
                    <xsl:call-template name="OneDimensionalRegion">
                        <xsl:with-param name="refseq"><xsl:value-of select="$refseq"/></xsl:with-param>
                        <xsl:with-param name="start"><xsl:value-of select="regex-group(1)"/></xsl:with-param>
                        <xsl:with-param name="end"><xsl:value-of select="$end"/></xsl:with-param>
                        <xsl:with-param name="ref_uri"><xsl:value-of select="$ref_uri"/></xsl:with-param>
                        <xsl:with-param name="ref_source"><xsl:value-of select="$ref_source"/></xsl:with-param>
                    </xsl:call-template>                    
                    
                </xsl:if>
            </xsl:matching-substring>
        </xsl:analyze-string>

    </xsl:template>

    <xsl:template match="vml:gene">

        <xsl:call-template name="DbXRef">
            <xsl:with-param name="type" select="'dl:Gene'"/>
        </xsl:call-template>

    </xsl:template>

    <xsl:template match="vml:location">
        
        <xsl:variable name="refseq">
            <xsl:value-of select="vml:ref_seq/@accession"/>
        </xsl:variable>
        <!-- chromosome should be part of accession number -->
        <xsl:variable name="ref_uri">
            <xsl:value-of select="vml:ref_seq/@uri"/>
        </xsl:variable>
        
        <xsl:variable name="ref_source">
            <xsl:value-of select="vml:ref_seq/@source"/>
        </xsl:variable>
        <xsl:variable name="start">
            <xsl:value-of select="vml:start"/>
        </xsl:variable>
        
        <xsl:variable name="end">
            <xsl:value-of select="vml:end"/>
        </xsl:variable>
        
        <xsl:call-template name="OneDimensionalRegion">
            <xsl:with-param name="refseq"><xsl:value-of select="$refseq"/></xsl:with-param>
            <xsl:with-param name="start"><xsl:value-of select="$start"/></xsl:with-param>
            <xsl:with-param name="end"><xsl:value-of select="$end"/></xsl:with-param>
            <xsl:with-param name="ref_uri"><xsl:value-of select="$ref_uri"/></xsl:with-param>
            <xsl:with-param name="ref_source"><xsl:value-of select="$ref_source"/></xsl:with-param>
        </xsl:call-template>                    
        
    </xsl:template>
    
    <xsl:template match="vml:ref_seq">

        <xsl:call-template name="DbXRef">
            <xsl:with-param name="type" select="'vmo:Sequence'"/>
            <xsl:with-param name="comment" select="'Reference sequence'"/>
        </xsl:call-template>
    </xsl:template>

    <xsl:template match="vml:phenotype">
        <xsl:call-template name="OntologyTerm">
            <xsl:with-param name="type" select="'dl:Disease'"/>
        </xsl:call-template>
    </xsl:template>



    <!-- Utility templates -->

    <xsl:template name="OneDimensionalRegion">
        
        <xsl:param name="refseq"/>
        <xsl:param name="start"/>
        <xsl:param name="end"/>
        <xsl:param name="ref_source"/>
        <xsl:param name="ref_uri"/>
        
        
        <xsl:element name="dl:OneDimensionalRegion">
            <xsl:attribute name="rdf:about">
                <xsl:value-of select="vmkb:refSeqId($refseq,$start,$end)"/>   
            </xsl:attribute>
            
            <!-- We say that the dimension element is located in reference sequence... cannot say anything about location in gene because these two may not match-->
            <xsl:element name="dl:isSpatialLocationOf">
                <xsl:attribute name="rdf:resource">
                    <xsl:value-of select="concat('&vmkb;',$refseq)"/>
                </xsl:attribute>
            </xsl:element>
            
            <dl:begins rdf:datatype="&xsd;integer">
                <xsl:value-of select="$start"/>
            </dl:begins>

            <xsl:choose>
                <xsl:when test="normalize-space($end) = '' ">                    
                    <dl:ends rdf:datatype="&xsd;integer">
                        <xsl:value-of select="$start"/>
                    </dl:ends>                    
                </xsl:when>
                <xsl:otherwise>
                    <dl:ends rdf:datatype="&xsd;integer">
                        <xsl:value-of select="$end"/>
                    </dl:ends>                    
                </xsl:otherwise>
            </xsl:choose>
            
            <!-- other option... not sure which one is correct..  -->
            <dl:hasValue rdf:datatype="&xsd;string">
                <xsl:value-of select="$start"/>                            
            </dl:hasValue>
        </xsl:element>                    
        
        
    </xsl:template>

    <xsl:template name="Comment">
        <xsl:param name="text"/>
        <xsl:element name="rdfs:comment">
            <xsl:attribute name="rdf:datatype">&xsd;string</xsl:attribute>
            <xsl:value-of select="$text"/>
        </xsl:element>
    </xsl:template>

    <xsl:template name="Label">
        <xsl:param name="text"/>
        <xsl:element name="rdfs:label">
            <xsl:attribute name="rdf:datatype">&xsd;string</xsl:attribute>
            <xsl:value-of select="$text"/>
        </xsl:element>
    </xsl:template>


    <xsl:template name="DbXRef">

        <xsl:param name="type"/>
        <xsl:param name="comment"/>


        <xsl:element name="{$type}">
            <xsl:attribute name="rdf:about">
                <xsl:value-of select="vmkb:dbxref(@accession,@source)"/>
            </xsl:attribute>

            <xsl:if test="$comment">
                <xsl:element name="rdfs:comment">
                    <xsl:attribute name="rdf:datatype">
                        <xsl:value-of select="'&xsd;string'"/>
                    </xsl:attribute>
                    <xsl:value-of select="$comment"/>
                </xsl:element>
            </xsl:if>


            <xsl:if test="@name">
                <xsl:element name="rdfs:label">
                    <xsl:attribute name="rdf:datatype">
                        <xsl:value-of select="'&xsd;string'"/>
                    </xsl:attribute>
                    <xsl:value-of select="@name"/>
                </xsl:element>
            </xsl:if>

            <xsl:if test="@uri">
                <xsl:element name="owl:sameAs">
                    <xsl:attribute name="rdf:resource">
                        <xsl:value-of select="@uri"/>
                    </xsl:attribute>
                </xsl:element>
            </xsl:if>

            <xsl:choose>
                <xsl:when test="upper-case(@source) = 'HGNC'">
                    <xsl:choose>
                        <xsl:when test="number(@accession)">
                            <xsl:element name="owl:sameAs">
                                <xsl:attribute name="rdf:resource">
                                    <xsl:value-of select="concat($hgnc_uri,@accession)"/>
                                </xsl:attribute>
                            </xsl:element>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:element name="owl:sameAs">
                                <xsl:attribute name="rdf:resource">
                                    <xsl:value-of select="concat($hugo_uri,@accession)"/>
                                </xsl:attribute>
                            </xsl:element>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:when>
                <xsl:when test="upper-case(@source) = 'HUGO'">
                    <xsl:element name="owl:sameAs">
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="concat($hugo_uri,@accession)"/>
                        </xsl:attribute>
                    </xsl:element>
                </xsl:when>
                <xsl:when test="upper-case(@source) = 'HGNC.SYMBOL'">
                    <xsl:element name="owl:sameAs">
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="concat($hugo_uri,@accession)"/>
                        </xsl:attribute>
                    </xsl:element>
                </xsl:when>
                <xsl:when test="upper-case(@source) = 'HGNC'">
                    <xsl:element name="owl:sameAs">
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="concat($hgnc_uri,@accession)"/>
                        </xsl:attribute>
                    </xsl:element>
                </xsl:when>
                <xsl:when test="upper-case(@source) = 'UNIPROT'">
                    <xsl:element name="owl:sameAs">
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="concat($uniprot_uri,@accession)"/>
                        </xsl:attribute>
                    </xsl:element>
                </xsl:when>
                <xsl:when test="upper-case(@source) = 'OMIM'">
                    <xsl:element name="owl:sameAs">
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="concat($omim_uri,@accession)"/>
                        </xsl:attribute>
                    </xsl:element>
                </xsl:when>
                <xsl:when test="upper-case(@source) = 'REFSEQ'">
                    <xsl:element name="owl:sameAs">
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="concat($refseq_uri,@accession)"/>
                        </xsl:attribute>
                    </xsl:element>
                </xsl:when>
                <xsl:when test="upper-case(@source) = 'GENBANK'">
                    <xsl:element name="owl:sameAs">
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="concat($genbank_uri,@accession)"/>
                        </xsl:attribute>
                    </xsl:element>
                </xsl:when>
                

            </xsl:choose>

        </xsl:element>



    </xsl:template>

    <xsl:template name="GenerateLocalURI">
        <!--  generate local uris from options -->
        <xsl:param name="primary"/>
        <xsl:param name="secondary"/>
        <xsl:param name="tertiary"/>
        
        <xsl:choose>
            <xsl:when test="primary">
                <xsl:value-of
                    select="concat(primary,':',encode-for-uri($secondary))"/>                                                
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of
                    select="concat('&vmkb;',encode-for-uri($secondary),':',encode-for-uri($tertiary))"/>                        
            </xsl:otherwise>
        </xsl:choose>        
    </xsl:template>
    
    
    <xsl:template name="OntologyTerm">

        <xsl:param name="type"/>
        <xsl:param name="comment"/>


        <xsl:element name="{$type}">
            <xsl:attribute name="rdf:about">
                <xsl:value-of select="vmkb:ontologyterm(@accession,@source,@term)"/>
            </xsl:attribute>

            <xsl:if test="$comment">
                <xsl:element name="rdfs:comment">
                    <xsl:attribute name="rdf:datatype">
                        <xsl:value-of select="'&xsd;string'"/>
                    </xsl:attribute>
                    <xsl:value-of select="$comment"/>
                </xsl:element>
            </xsl:if>

            <xsl:if test="@term">
                <xsl:element name="rdfs:label">
                    <xsl:attribute name="rdf:datatype">
                        <xsl:value-of select="'&xsd;string'"/>
                    </xsl:attribute>
                    <xsl:value-of select="@term"/>
                </xsl:element>
            </xsl:if>

            <xsl:if test="@uri">
                <xsl:element name="owl:sameAs">
                    <xsl:attribute name="rdf:resource">
                        <xsl:value-of select="@uri"/>
                    </xsl:attribute>
                </xsl:element>
            </xsl:if>

            <xsl:if test="@source and @accession"> 

                <xsl:choose>
                    <xsl:when test="upper-case(@source) = 'OMIM'">
                        <xsl:element name="owl:sameAs">
                            <xsl:attribute name="rdf:resource">
                                <xsl:value-of select="concat($omim_uri,@accession)"/>
                            </xsl:attribute>
                        </xsl:element>
                    </xsl:when>
                </xsl:choose>
                
            </xsl:if>
            
        </xsl:element>

    </xsl:template>




    <xsl:function name="vmkb:dbxref">
        <xsl:param name="accession"/>
        <xsl:param name="source"/>
        <xsl:choose>
            <xsl:when test="$source">
                <xsl:value-of select="concat('&vmkb;',$source,':',$accession)"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="concat('&vmkb;',$accession)"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>


    <xsl:function name="vmkb:ontologyterm">
        <xsl:param name="accession"/>
        <xsl:param name="source"/>
        <xsl:param name="term"/>
        <xsl:variable name="fake_uri">
            <xsl:value-of select="encode-for-uri($term)"/>
        </xsl:variable>
        <xsl:choose>
            <xsl:when test="$source and $accession">
                <xsl:value-of select="concat('&vmkb;',$source,':',$accession)"/>
            </xsl:when>
            <xsl:when test="$source and $term">
                <xsl:value-of select="concat('&vmkb;',$source,':',$fake_uri)"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="concat('&vmkb;',$fake_uri)"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <xsl:function name="vmkb:refSeqId">
        <xsl:param name="accession"/>
        <xsl:param name="start"/>
        <xsl:param name="end"/>
        <xsl:choose>
            <xsl:when test="normalize-space($end) != ''">
                <xsl:value-of select="concat('&vmkb;',$accession,':',$start,':',$end)"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="concat('&vmkb;',$accession,':',$start)"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>
    

</xsl:stylesheet>
