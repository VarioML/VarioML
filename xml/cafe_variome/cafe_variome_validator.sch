<?xml version="1.0" encoding="UTF-8"?>
<iso:schema    
    xmlns="http://purl.oclc.org/dsdl/schematron"  
    xmlns:iso="http://purl.oclc.org/dsdl/schematron" 
    xmlns:vml="http://varioml.org/xml/1.0"
    queryBinding='xslt2'
    schemaVersion='ISO19757-3'>                  
    <iso:title>ISO schematron validation rules for the Cafe Variome data submissions</iso:title>
    <iso:ns prefix='vml' uri='http://varioml.org/xml/1.0'/> 
    
    <!-- Cafe Variome constraints  -->            
    
    <iso:pattern id="cafe_variome.submission.checks">
        
        <iso:rule context="vml:cafe_variome">                                  
            <iso:assert  
                test="vml:source">Source is missing</iso:assert> 
            <iso:assert  
                test="vml:variant">Variant is missing</iso:assert> 
        </iso:rule>

        <iso:rule context="vml:cafe_variome/vml:source">                                  
            <iso:assert 
                test="@id">Source identifier is missing</iso:assert>             
            <iso:assert 
                test="vml:name">Name of source is missing</iso:assert>             
        </iso:rule>

        <!-- contact is optional, but if we have then either name or email should be present-->
        <iso:rule context="vml:cafe_variome/vml:source/vml:contact">                                  
            <iso:assert 
                test="vml:name or vml:email">Contact name or email is missing</iso:assert> 
        </iso:rule>

        
    </iso:pattern>
    
    <iso:pattern id="cafe_variome.reporting_variant.checks" >
        
        <iso:rule context="vml:cafe_variome/vml:variant">

            <iso:assert 
                test="vml:gene">Gene sequence is missing</iso:assert> 
            
            <iso:assert 
                test="vml:ref_seq">Reference sequence is missing</iso:assert> 
            
            <iso:assert 
                test="vml:name">Name is missing</iso:assert> 

            <iso:assert 
                test="vml:sharing_policy">Sharing policy of variant is missing</iso:assert> 
            
        </iso:rule>

        <iso:rule context="vml:seq_changes/vml:variant">
            <!-- here we do not need gene anymore  -->           
            <iso:assert 
                test="vml:ref_seq">Reference sequence is missing</iso:assert> 
            
            <iso:assert 
                test="vml:name">Name is missing</iso:assert> 
                       
        </iso:rule>
 
        <iso:rule context="vml:aliases/vml:variant">
            <!-- legacy variants. Only name is needed -->            
            <iso:assert 
                test="vml:name">Name is missing</iso:assert> 
            
        </iso:rule>
        
        <iso:rule context="vml:variant/vml:name">
            <iso:assert test="not(@scheme) or upper-case(@scheme)='HGVS'">HGVS naming scheme should be used. Found: <iso:value-of select="@scheme"/> </iso:assert>
            <!-- Should use HGVS names only...
            <iso:assert test="starts-with(normalize-space(text()),'g.') or starts-with(normalize-space(text()),'c.') or starts-with(normalize-space(text()),'p.') or starts-with(normalize-space(text()),'r.')"></iso:assert>
            -->
        </iso:rule>
  
        <iso:rule context="vml:location">
            <iso:assert test="vml:ref_seq">Location must have reference sequence</iso:assert>
            <!-- start position is given in the relax schema-->
        </iso:rule>
        
        <iso:rule context="vml:sharing_policy">
            <iso:assert test="@type='openAccess' or @type='closedAccess' or @type='embargoedAccess' or @type='restrictedAccess'">
                Sharing policy should be either: 'closedAccess' or 'embargoedAccess' or 'restrictedAccess' or 'openAccess'
            </iso:assert>
            <iso:assert test="not(@type='embargoedAccess') or exists(child::vml:embargo_end_date)">
                Embargo end date is mandatory if sharing policy is set to embargoed 
            </iso:assert>
        </iso:rule>

        <iso:rule context="vml:variant/vml:panel" >
            <iso:assert test="vml:phenotype or vml:individual" >Panel should have at least phenotype or individual</iso:assert>
            <!-- need to delimit use of panel to avoid misuses -->
            <iso:assert test="(count(vml:phenotype)+count(vml:individual)+count(vml:organism)+count(vml:population)) = count(child::*)" >Element contains VarioML terms which are not part of the Cafe Variome spec</iso:assert>
        </iso:rule>
        
        <iso:rule context="vml:variant/vml:panel/vml:individual" >
            <iso:assert test="vml:gender" >Individual should have at least gender</iso:assert>
            <!-- need to delimit use of individual to avoid misuses -->
            <iso:assert test="count(vml:gender) = count(child::*)" >Element contains VarioML terms which are not part of the Cafe Variome spec</iso:assert>
        </iso:rule>

        <iso:rule context="vml:variant/vml:variant_detection" >
            <iso:assert test="@technique" >Technique missing in variant detection</iso:assert>
            <iso:assert test="@template" >Template missing in variant detection</iso:assert>
        </iso:rule>

        <iso:rule context="vml:variant/vml:frequency" >
            <!-- this is just place holder.  we no not have additional validation rules for frequencies  -->
        </iso:rule>
        
    </iso:pattern>

    <iso:pattern id="cafe_variome.xrefs" >
        
        <iso:rule context="vml:ref_seq|vml:gene|vml:db_xref" >
            <iso:assert test="@accession" >Accession number is missing in database xref (gene or ref_seq)</iso:assert>
        </iso:rule>
        
    </iso:pattern>
    
    <iso:pattern id="cafe_variome.ontology_terms" >
        <!-- we should have atleast the term attribute -->
        <iso:rule context="vml:genetic_origin|vml:pathogenicity|vml:phenotype|vml:evidence_code|vml:use_permission|vml:variant_type|vml:consequence" >
            <iso:assert test="@term" >Ontology term (genetic_origin, pathogenicity...) should have term-attribute </iso:assert>
        </iso:rule>
        
    </iso:pattern>
    

    <iso:pattern id="cafe_variome.misc" >
        <!-- comment should have atleast the term attribute or text element-->
        <iso:rule context="vml:comment" >
            <iso:assert test="vml:text or @term" >Comment element should have text or term at least</iso:assert>
        </iso:rule>
        
    </iso:pattern>
    
</iso:schema>    