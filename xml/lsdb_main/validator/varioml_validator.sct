<schema xmlns="http://purl.oclc.org/dsdl/schematron">
 <title>VarioML VREPORT-XML Validator</title>
<ns prefix="vml" uri="http://gen2phen.org/varioml/2.0" />

<pattern name="individual">
  <rule context="vml:lsdb">        
     <assert test="vml:source">
     Top-level Source element is not present.
     </assert>
     <report test="vml:source">
     Top-level Source element is present.
     </report> 
     
	<assert test="vml:individual">
     Individual element is not present.
     </assert>
     	<report test="vml:individual">
     One or more Individual elements are present.
     </report>
     
   </rule>
   
   <rule context="vml:individual">
     
     <report test="@id">Individual id:<value-of select="@id"/></report>
         
     <assert test="vml:gender">
     Gender element is not present.
     </assert>
     	<report test="vml:gender">
     Gender element is present.
     </report>
     
     <assert test="vml:phenotype">
     Individual:Phenotype element is not present.
     </assert>
     	<report test="vml:phenotype">
     Individual:Phenotype element is present.
     </report>
     
     <assert test="vml:population">
     Individual:Population element is not present.
     </assert>
     	<report test="vml:population">
     Individual:Population element is present.
     </report>
     
  </rule>

</pattern>

<pattern name="variant">
  
  <rule context="vml:variant">
  
    <report test="@id">Variant id:<value-of select="@id"/></report>
     
    <report test="parent::vml:individual or parent::vml:seq_changes or parent::vml:panel" diagnostics="var_name"> Variant <value-of select="@id"/> is a child of <name path="parent::*"/> id <value-of select="../@id"/>
    </report>   
     
    <diagnostic id="var_name">
<value-of select="parent::vml:variant[@id]"/>
</diagnostic>

parent::node/@nodeName

    <assert test="@VmlId">variant:VmlId attribute is not present</assert> 
    <assert test="@type">variant:type attribute is not present</assert> 
    <assert test="@genotypic">variant:genotypic attribute is not present</assert> 
    <report test="@genotypic">variant:genotypic attribute is present</report> 
    <assert test="@subcellular_part">variant:subcellular_part attribute is not present</assert> 
    <report test="@subcellular_part">variant:subcellular_part attribute is present</report> 
    <assert test="@diploid_count">variant:diploid_count attribute is not present</assert> 
    <report test="@diploid_count">variant:diploid_count attribute is present</report> 
  
 	<assert test="vml:gene">
     Gene element is not present.
     </assert>

	<assert test="vml:ref_seq">
     Ref_Seq element is not present.
     </assert>
     
     <assert test="vml:name">
     Variant name element is not present.
     </assert>
     
     <assert test="vml:variant_type">
     Variant_Type element is not present.
     </assert>
     
     <assert test="vml:original_id">
     Original_ID element is not present.
     </assert>
     
     <assert test="vml:exon">
     Exon element is not present.
     </assert>
     
     <assert test="vml:sequence">
     Sequence element is not present.
     </assert>

     <assert test="vml:genotype">
     Genotype element is not present.
     </assert>

     <assert test="vml:consequence">
     Consequence element is not present.
     </assert>

     <assert test="vml:pathogenicity">
     Pathogenicity element is not present.
     </assert>

     <assert test="vml:phenotype">
     Variant:Phenotype element is not present.
     </assert>

     <assert test="vml:variant_detection">
     Variant_Detection element is not present.
     </assert>

     <assert test="vml:restriction_site">
     Restriction_Site element is not present.
     </assert>

     <assert test="vml:tissue_distribution">
     Tissue_Distribution element is not present.
     </assert>

     <assert test="vml:genetic_origin">
     Genetic_Origin element is not present.
     </assert>

     <assert test="vml:frequency">
     Frequency element is not present.
     </assert>

     <assert test="vml:seq_changes">
     Seq_Changes element is not present.
     </assert>

     <assert test="vml:aliases">
     Aliases element is not present.
     </assert>

     <assert test="vml:haplotype_group">
     Haplotype_Group element is not present.
     </assert>

     <assert test="vml:location">
     Variant:location element is not present.
     </assert>

     <assert test="vml:sharing_policy">
     Sharing_policy element is not present.
     </assert>

     <assert test="vml:creation_date">
     Creation_date element is not present.
     </assert>

     <assert test="vml:modification_date">
     Modification_date element is not present.
     </assert>

     <assert test="vml:evidence_code">
     Evidence_code element is not present.
     </assert>

     <assert test="vml:protocol_id">
     Protocol_id element is not present.
     </assert>

     <assert test="vml:db_xref">
     Db_xref element is not present.
     </assert>

     <assert test="vml:comment">
     Comment element is not present.
     </assert>

	</rule>
</pattern>

</schema>
