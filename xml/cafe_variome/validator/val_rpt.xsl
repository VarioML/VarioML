<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:dc="http://purl.org/dc/elements/1.1/"
  xmlns:svrl="http://purl.oclc.org/dsdl/svrl">	
<xsl:output method="html" indent="yes" omit-xml-declaration="yes" encoding="utf-8" />
<!-- root node-->
<xsl:key name="fired" match="svrl:successful-report"
use="generate-id(preceding-sibling::svrl:fired-rule[1])"/>
<xsl:key name="fired2" match="svrl:failed-assert"
use="generate-id(preceding-sibling::svrl:fired-rule[1])"/>
<xsl:template match="/">
<html>
<head>
<link rel="stylesheet" type="text/css" href="val_rpt.css" />
<style type="text/css" media="screen">@import "val_rpt.css";</style>					
<style type="text/css" media="screen">@import "http://twitter.github.com/bootstrap/assets/css/bootstrap.css";</style>
<style type="text/css" media="screen">@import "http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css;</style>

			<title>VarioML Schema Validation Report</title>
		</head>
		<body>
		<h2>VarioML Schema Validation Report</h2>

		 <span style="font-size: 0.8em; background-color: #E6E6E6;">
  Key:
 <ul>
  <li> <b>Black</b> text outlines structure of your Cafe Variome XML document. <em>Absence of red text under a heading indicates it is error-free.</em></li>
  <li> <b style="color: red;">Red</b> text reports errors. File will not import until fixed. </li>
   <li> <b style="color:#4CC417;">Green</b> text provides optional helpful info.</li>
</ul>
</span>

<ul id="val_rpt">  
 
		<xsl:apply-templates select="svrl:schematron-output"/>
		
		</ul>
		
		</body>
	</html>
	</xsl:template>
	

<!-- into the svrl node -->

<!-- into text nodes -->

<xsl:template match="svrl:schematron-output">
<ol>
<xsl:copy>
<xsl:apply-templates select="svrl:fired-rule"/>
</xsl:copy>
</ol>
</xsl:template>

<xsl:template match="svrl:fired-rule">
<strong><li><xsl:value-of select="@context"/></li></strong>
<xsl:copy>
<xsl:apply-templates/>
<ol>
<xsl:apply-templates
select="key('fired',generate-id(.))"/>
<xsl:apply-templates
select="key('fired2',generate-id(.))"/>
</ol>
</xsl:copy>
</xsl:template>

<xsl:template match="svrl:successful-report"> 
<li class="success"><xsl:value-of select="svrl:text" /></li>
<xsl:apply-templates select="strong"/>
</xsl:template>

<xsl:template match="svrl:failed-assert"> 
<li class="failure"><xsl:value-of select="svrl:text" /></li>
<xsl:apply-templates select="strong"/>
</xsl:template>

<xsl:template match="strong">
	<strong>
		<xsl:apply-templates select="@*|node()"/>
		<xsl:value-of select="replace(current(),'vml:','')"/>
	</strong>
</xsl:template>

<xsl:template match="@*|node()">
	<xsl:copy>
		<xsl:apply-templates select="@*|node()"/>
	</xsl:copy>
</xsl:template>

<!-- NEXT: - remove vml: prefix from display; add top-level section icons; add indentation -->

</xsl:stylesheet>