#!/bin/bash
if [ ! -e $1.sch ] ; then
  Schematron file was missing (give file name without .sch prefix)
  exit 
fi
if [ ! -e $2.xml ] ; then
  echo xml file was missing (give file name without .xml prefix)
  exit 
fi

#XSLT=xsltproc
#We are using Saxon and xslt 2.0
XSLT='java -jar ../lib/saxon9he.jar'
SCH=../lib/iso-schematron-xslt2
TMP=/tmp
$XSLT -o:$TMP/$1_tmp1.sch  -xsl:$SCH/iso_dsdl_include.xsl    -s:$1.sch  
$XSLT -o:$TMP/$1_tmp2.sch  -xsl:$SCH/iso_abstract_expand.xsl -s:$TMP/$1_tmp1.sch  
$XSLT -o:$1.xsl       -xsl:$SCH/iso_svrl_for_xslt2.xsl  -s:$TMP/$1_tmp2.sch
$XSLT -o:$1-document.svrl  -xsl:$1.xsl  -s:$2.xml 
#$XSLT -o:$1-document.txt  -xsl:$SCH/iso_schematron_text.xsl  -s:$1-document.svrl 
