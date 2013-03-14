<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:saxon="http://saxon.sf.net/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:schold="http://www.ascc.net/xml/schematron"
                xmlns:iso="http://purl.oclc.org/dsdl/schematron"
                xmlns:xhtml="http://www.w3.org/1999/xhtml"
                xmlns:vml="http://varioml.org/xml/1.0"
                version="2.0"><!--Implementers: please note that overriding process-prolog or process-root is 
    the preferred method for meta-stylesheets to use where possible. -->
<xsl:param name="archiveDirParameter"/>
   <xsl:param name="archiveNameParameter"/>
   <xsl:param name="fileNameParameter"/>
   <xsl:param name="fileDirParameter"/>
   <xsl:variable name="document-uri">
      <xsl:value-of select="document-uri(/)"/>
   </xsl:variable>

   <!--PHASES-->


<!--PROLOG-->
<xsl:output xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
               method="xml"
               omit-xml-declaration="no"
               standalone="yes"
               indent="yes"/>

   <!--XSD TYPES FOR XSLT2-->


<!--KEYS AND FUNCTIONS-->


<!--DEFAULT RULES-->


<!--MODE: SCHEMATRON-SELECT-FULL-PATH-->
<!--This mode can be used to generate an ugly though full XPath for locators-->
<xsl:template match="*" mode="schematron-select-full-path">
      <xsl:apply-templates select="." mode="schematron-get-full-path"/>
   </xsl:template>

   <!--MODE: SCHEMATRON-FULL-PATH-->
<!--This mode can be used to generate an ugly though full XPath for locators-->
<xsl:template match="*" mode="schematron-get-full-path">
      <xsl:apply-templates select="parent::*" mode="schematron-get-full-path"/>
      <xsl:text>/</xsl:text>
      <xsl:choose>
         <xsl:when test="namespace-uri()=''">
            <xsl:value-of select="name()"/>
         </xsl:when>
         <xsl:otherwise>
            <xsl:text>*:</xsl:text>
            <xsl:value-of select="local-name()"/>
            <xsl:text>[namespace-uri()='</xsl:text>
            <xsl:value-of select="namespace-uri()"/>
            <xsl:text>']</xsl:text>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:variable name="preceding"
                    select="count(preceding-sibling::*[local-name()=local-name(current())                                   and namespace-uri() = namespace-uri(current())])"/>
      <xsl:text>[</xsl:text>
      <xsl:value-of select="1+ $preceding"/>
      <xsl:text>]</xsl:text>
   </xsl:template>
   <xsl:template match="@*" mode="schematron-get-full-path">
      <xsl:apply-templates select="parent::*" mode="schematron-get-full-path"/>
      <xsl:text>/</xsl:text>
      <xsl:choose>
         <xsl:when test="namespace-uri()=''">@<xsl:value-of select="name()"/>
         </xsl:when>
         <xsl:otherwise>
            <xsl:text>@*[local-name()='</xsl:text>
            <xsl:value-of select="local-name()"/>
            <xsl:text>' and namespace-uri()='</xsl:text>
            <xsl:value-of select="namespace-uri()"/>
            <xsl:text>']</xsl:text>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>

   <!--MODE: SCHEMATRON-FULL-PATH-2-->
<!--This mode can be used to generate prefixed XPath for humans-->
<xsl:template match="node() | @*" mode="schematron-get-full-path-2">
      <xsl:for-each select="ancestor-or-self::*">
         <xsl:text>/</xsl:text>
         <xsl:value-of select="name(.)"/>
         <xsl:if test="preceding-sibling::*[name(.)=name(current())]">
            <xsl:text>[</xsl:text>
            <xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
            <xsl:text>]</xsl:text>
         </xsl:if>
      </xsl:for-each>
      <xsl:if test="not(self::*)">
         <xsl:text/>/@<xsl:value-of select="name(.)"/>
      </xsl:if>
   </xsl:template>
   <!--MODE: SCHEMATRON-FULL-PATH-3-->
<!--This mode can be used to generate prefixed XPath for humans 
  (Top-level element has index)-->
<xsl:template match="node() | @*" mode="schematron-get-full-path-3">
      <xsl:for-each select="ancestor-or-self::*">
         <xsl:text>/</xsl:text>
         <xsl:value-of select="name(.)"/>
         <xsl:if test="parent::*">
            <xsl:text>[</xsl:text>
            <xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
            <xsl:text>]</xsl:text>
         </xsl:if>
      </xsl:for-each>
      <xsl:if test="not(self::*)">
         <xsl:text/>/@<xsl:value-of select="name(.)"/>
      </xsl:if>
   </xsl:template>

   <!--MODE: GENERATE-ID-FROM-PATH -->
<xsl:template match="/" mode="generate-id-from-path"/>
   <xsl:template match="text()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.text-', 1+count(preceding-sibling::text()), '-')"/>
   </xsl:template>
   <xsl:template match="comment()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.comment-', 1+count(preceding-sibling::comment()), '-')"/>
   </xsl:template>
   <xsl:template match="processing-instruction()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.processing-instruction-', 1+count(preceding-sibling::processing-instruction()), '-')"/>
   </xsl:template>
   <xsl:template match="@*" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.@', name())"/>
   </xsl:template>
   <xsl:template match="*" mode="generate-id-from-path" priority="-0.5">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:text>.</xsl:text>
      <xsl:value-of select="concat('.',name(),'-',1+count(preceding-sibling::*[name()=name(current())]),'-')"/>
   </xsl:template>

   <!--MODE: GENERATE-ID-2 -->
<xsl:template match="/" mode="generate-id-2">U</xsl:template>
   <xsl:template match="*" mode="generate-id-2" priority="2">
      <xsl:text>U</xsl:text>
      <xsl:number level="multiple" count="*"/>
   </xsl:template>
   <xsl:template match="node()" mode="generate-id-2">
      <xsl:text>U.</xsl:text>
      <xsl:number level="multiple" count="*"/>
      <xsl:text>n</xsl:text>
      <xsl:number count="node()"/>
   </xsl:template>
   <xsl:template match="@*" mode="generate-id-2">
      <xsl:text>U.</xsl:text>
      <xsl:number level="multiple" count="*"/>
      <xsl:text>_</xsl:text>
      <xsl:value-of select="string-length(local-name(.))"/>
      <xsl:text>_</xsl:text>
      <xsl:value-of select="translate(name(),':','.')"/>
   </xsl:template>
   <!--Strip characters--><xsl:template match="text()" priority="-1"/>

   <!--SCHEMA SETUP-->
<xsl:template match="/">
      <svrl:schematron-output xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                              title="ISO schematron validation rules for the Cafe Variome data submissions"
                              schemaVersion="ISO19757-3">
         <xsl:comment>
            <xsl:value-of select="$archiveDirParameter"/>   
     <xsl:value-of select="$archiveNameParameter"/>  
     <xsl:value-of select="$fileNameParameter"/>  
     <xsl:value-of select="$fileDirParameter"/>
         </xsl:comment>
         <svrl:ns-prefix-in-attribute-values uri="http://varioml.org/xml/1.0" prefix="vml"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">cafe_variome.submission.checks</xsl:attribute>
            <xsl:attribute name="name">cafe_variome.submission.checks</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M3"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">cafe_variome.reporting_variant.checks</xsl:attribute>
            <xsl:attribute name="name">cafe_variome.reporting_variant.checks</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M4"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">cafe_variome.xrefs</xsl:attribute>
            <xsl:attribute name="name">cafe_variome.xrefs</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M5"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">cafe_variome.ontology_terms</xsl:attribute>
            <xsl:attribute name="name">cafe_variome.ontology_terms</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M6"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">cafe_variome.misc</xsl:attribute>
            <xsl:attribute name="name">cafe_variome.misc</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M7"/>
      </svrl:schematron-output>
   </xsl:template>

   <!--SCHEMATRON PATTERNS-->
<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">ISO schematron validation rules for the Cafe Variome data submissions</svrl:text>
   <xsl:param name="V2" select="number(//vml:cafe_variome/@schema_version) ge 2.0"/>

   <!--PATTERN cafe_variome.submission.checks-->


  <!--RULE -->
<xsl:template match="vml:cafe_variome" priority="1003" mode="M3">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:cafe_variome"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:source"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:source">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Source is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:source">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:source">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Source ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:variant"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:variant">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Variant is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:variant">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:variant">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Variant ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M3"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:cafe_variome/vml:source" priority="1002" mode="M3">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:cafe_variome/vml:source"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@id"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@id">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Source identifier is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:name"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Name of source is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:name">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Name of source ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M3"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:cafe_variome/vml:source/vml:contact"
                 priority="1001"
                 mode="M3">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:cafe_variome/vml:source/vml:contact"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:name or vml:email"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name or vml:email">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Contact name or email is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@role) or @role='curator' or @role='producer' or @role='submitter'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@role) or @role='curator' or @role='producer' or @role='submitter'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Role of contact is not recognized</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:name or vml:email">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name or vml:email">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Contact ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M3"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:cafe_variome/vml:source/vml:comment"
                 priority="1000"
                 mode="M3">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:cafe_variome/vml:source/vml:comment"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@term='wasDerivedFrom') or @source='opmv'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@term='wasDerivedFrom') or @source='opmv'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>'wasDefinedFrom' cannot exist without 'opmv' name space (source)</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@source='opmv') or @term='wasDerivedFrom'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@source='opmv') or @term='wasDerivedFrom'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>opmv (open provenance mode) must have at least one predicate defined (e.g. 'wasDerivedFrom')</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@source='opmv' and @term='wasDerivedFrom') or not(@uri) or @uri='http://purl.org/net/opmv/ns#wasDerivedFrom'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@source='opmv' and @term='wasDerivedFrom') or not(@uri) or @uri='http://purl.org/net/opmv/ns#wasDerivedFrom'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>URI of wasDerivedFrom predicate was wrong</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@source='opmv') or vml:db_xref"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@source='opmv') or vml:db_xref">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Database xref is misisng in provenace content (source/comment/@source='opmv') element)</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@term='disclaimer') or @source='g2p'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@term='disclaimer') or @source='g2p'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>'disclaimer cannot exist without 'g2p' name space (source)</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@term='disclaimer') or vml:text"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@term='disclaimer') or vml:text">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>'disclaimer cannot exist without text element </svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M3"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M3"/>
   <xsl:template match="@*|node()" priority="-2" mode="M3">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M3"/>
   </xsl:template>

   <!--PATTERN cafe_variome.reporting_variant.checks-->


  <!--RULE -->
<xsl:template match="vml:cafe_variome/vml:variant" priority="1009" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:cafe_variome/vml:variant"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:gene"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:gene">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Gene sequence is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:gene">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:gene">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Gene sequence ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:ref_seq"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:ref_seq">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Reference sequence is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:ref_seq">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:ref_seq">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Reference sequence ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:name"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Name is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:name">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Name ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:sharing_policy"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:sharing_policy">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Sharing policy of variant is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:sharing_policy">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:sharing_policy">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Sharing policy of variant ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not($V2) or vml:panel"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="not($V2) or vml:panel">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Panel is missing. Need to identify observation target (individual or panel)</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="not($V2) or vml:panel">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="not($V2) or vml:panel">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Panel ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:seq_changes/vml:variant" priority="1008" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:seq_changes/vml:variant"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:ref_seq"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:ref_seq">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Reference sequence is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:ref_seq">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:ref_seq">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Reference sequence ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:name"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Name is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:name">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Name ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:aliases/vml:variant" priority="1007" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:aliases/vml:variant"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:name"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Name is missing</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:name">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:name">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Name ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:variant/vml:name" priority="1006" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:variant/vml:name"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@scheme) or upper-case(@scheme)='HGVS'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@scheme) or upper-case(@scheme)='HGVS'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>HGVS naming scheme should be used. Found: <xsl:text/>
                  <xsl:value-of select="@scheme"/>
                  <xsl:text/> 
               </svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="not(@scheme) or upper-case(@scheme)='HGVS'">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                 test="not(@scheme) or upper-case(@scheme)='HGVS'">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>HGVS naming scheme correctly used: <xsl:text/>
               <xsl:value-of select="@scheme"/>
               <xsl:text/> 
            </svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:location" priority="1005" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:location"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:ref_seq"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:ref_seq">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Location must have reference sequence</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="normalize-space(vml:end) ge normalize-space(vml:start) "/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="normalize-space(vml:end) ge normalize-space(vml:start)">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Incorrect location. End should be larger or equal than start position</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:ref_seq">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:ref_seq">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Location has reference sequence</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--REPORT -->
<xsl:if test="not(vml:chr)">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="not(vml:chr)">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Location entry has Chromosome reference sequence</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--REPORT -->
<xsl:if test="normalize-space(vml:end) ge normalize-space(vml:start) ">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                 test="normalize-space(vml:end) ge normalize-space(vml:start)">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Location End is correctly larger or equal to start position</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:sharing_policy" priority="1004" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:sharing_policy"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@type='openAccess' or @type='closedAccess' or @type='embargoedAccess' or @type='restrictedAccess'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="@type='openAccess' or @type='closedAccess' or @type='embargoedAccess' or @type='restrictedAccess'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>
                Sharing policy should be either: 'closedAccess' or 'embargoedAccess' or 'restrictedAccess' or 'openAccess'
            </svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not(@type='embargoedAccess') or exists(child::vml:embargo_end_date)"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not(@type='embargoedAccess') or exists(child::vml:embargo_end_date)">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>
                Embargo end date is mandatory if sharing policy is set to embargoed 
            </svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="@type='openAccess' or @type='closedAccess' or @type='embargoedAccess' or @type='restrictedAccess'">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                 test="@type='openAccess' or @type='closedAccess' or @type='embargoedAccess' or @type='restrictedAccess'">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>
                Sharing policy matches accepted terms ( 'closedAccess' or 'embargoedAccess' or 'restrictedAccess' or 'openAccess')
            </svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--REPORT -->
<xsl:if test="not(@type='embargoedAccess') or exists(child::vml:embargo_end_date)">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                 test="not(@type='embargoedAccess') or exists(child::vml:embargo_end_date)">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>
                Sharing policy is set to embargoed; required Embargo end date is present.  
            </svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:variant/vml:panel" priority="1003" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:variant/vml:panel"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="(count(vml:phenotype)+count(vml:individual)+count(vml:organism)+count(vml:population)+count(vml:comment)) = count(child::*)"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="(count(vml:phenotype)+count(vml:individual)+count(vml:organism)+count(vml:population)+count(vml:comment)) = count(child::*)">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>This element contains VarioML terms which are not part of the Cafe Variome spec</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not($V2) or @id"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="not($V2) or @id">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text/>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:variant/vml:panel/vml:individual"
                 priority="1002"
                 mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:variant/vml:panel/vml:individual"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="(count(vml:gender)) = count(child::*)"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="(count(vml:gender)) = count(child::*)">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Element panel/individual contains VarioML terms which are not part of the Cafe Variome spec</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:variant/vml:variant_detection" priority="1001" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:variant/vml:variant_detection"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@technique"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@technique">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Technique missing in variant detection</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@template"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@template">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Template missing in variant detection</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="@technique">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@technique">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Variant detection has Technique element</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--REPORT -->
<xsl:if test="@template">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@template">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Variant detection has Template element</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:variant/vml:frequency" priority="1000" mode="M4">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:variant/vml:frequency"/>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M4"/>
   <xsl:template match="@*|node()" priority="-2" mode="M4">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M4"/>
   </xsl:template>

   <!--PATTERN cafe_variome.xrefs-->


  <!--RULE -->
<xsl:template match="vml:db_xref" priority="1002" mode="M5">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:db_xref"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@accession or @uri"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@accession or @uri">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Accession number or URI is missing in database xref (gene or ref_seq)</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="@accession or @uri">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@accession or @uri">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Database xref ok</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M5"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:gene" priority="1001" mode="M5">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:gene"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@accession or @uri"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@accession or @uri">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Accession number is missing in database xref (gene or ref_seq)</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not($V2)  or upper-case(@source)='HGNC.SYMBOL' or upper-case(@source)='HGNC'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not($V2) or upper-case(@source)='HGNC.SYMBOL' or upper-case(@source)='HGNC'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Source of gene should be HGNC_Symbol or HGNC</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="@accession or @uri">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@accession or @uri">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Database xref (gene or ref_seq) contains required accession number.</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--REPORT -->
<xsl:if test="not($V2)  or upper-case(@source)='HGNC.SYMBOL' or upper-case(@source)='HGNC'">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                 test="not($V2) or upper-case(@source)='HGNC.SYMBOL' or upper-case(@source)='HGNC'">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Source of gene is HGNC_Symbol or HGNC</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M5"/>
   </xsl:template>

    <!--RULE -->
<xsl:template match="vml:ref_seq" priority="1000" mode="M5">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:ref_seq"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@accession"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@accession">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Accession number is missing in database xref (gene or ref_seq)</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="not($V2) or upper-case(@source)='GENBANK' or upper-case(@source)='REFSEQ' or upper-case(@source)='ENSEMBL' or upper-case(@source)='UCSC'"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="not($V2) or upper-case(@source)='GENBANK' or upper-case(@source)='REFSEQ' or upper-case(@source)='ENSEMBL' or upper-case(@source)='UCSC'">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Source of ref sequence is wrong</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="@accession">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@accession">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Database xref (gene or ref_seq) contains required accession number.</svrl:text>
         </svrl:successful-report>
      </xsl:if>

        <!--REPORT -->
<xsl:if test="not($V2) or upper-case(@source)='GENBANK' or upper-case(@source)='REFSEQ' or upper-case(@source)='ENSEMBL' or upper-case(@source)='UCSC'">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                 test="not($V2) or upper-case(@source)='GENBANK' or upper-case(@source)='REFSEQ' or upper-case(@source)='ENSEMBL' or upper-case(@source)='UCSC'">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Ref sequence Source is correctly formatted.</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M5"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M5"/>
   <xsl:template match="@*|node()" priority="-2" mode="M5">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M5"/>
   </xsl:template>

   <!--PATTERN cafe_variome.ontology_terms-->


  <!--RULE -->
<xsl:template match="vml:genetic_origin|vml:pathogenicity|vml:phenotype|vml:evidence_code|vml:use_permission|vml:variant_type|vml:consequence"
                 priority="1000"
                 mode="M6">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="vml:genetic_origin|vml:pathogenicity|vml:phenotype|vml:evidence_code|vml:use_permission|vml:variant_type|vml:consequence"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="@term"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@term">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Ontology term (genetic_origin, pathogenicity..) should have term-attribute </svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="@term">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="@term">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Ontology term ok. Ontology term (genetic_origin, pathogenicity..) should always have term-attribute </svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M6"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M6"/>
   <xsl:template match="@*|node()" priority="-2" mode="M6">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M6"/>
   </xsl:template>

   <!--PATTERN cafe_variome.misc-->


  <!--RULE -->
<xsl:template match="vml:comment" priority="1000" mode="M7">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="vml:comment"/>

        <!--ASSERT -->
<xsl:choose>
         <xsl:when test="vml:text or @term"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:text or @term">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Comment element should have text (preferred) or term</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

        <!--REPORT -->
<xsl:if test="vml:text or @term">
         <svrl:successful-report xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="vml:text or @term">
            <xsl:attribute name="location">
               <xsl:apply-templates select="." mode="schematron-select-full-path"/>
            </xsl:attribute>
            <svrl:text>Comment element ok.Comment element should have text (preferred) or term.</svrl:text>
         </svrl:successful-report>
      </xsl:if>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M7"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M7"/>
   <xsl:template match="@*|node()" priority="-2" mode="M7">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M7"/>
   </xsl:template>
</xsl:stylesheet>
