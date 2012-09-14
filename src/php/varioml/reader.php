<?php
// (Currenly experimental) PHP code for parsing VarioML data
// 
// Contact: Juha M.
//
//
// error_reporting(E_NOTICE);
//
//

/*
  Iter
  
  Simple iertator for looping child elements (could probably be made by 
  implementing PHP's Iterator interface
 */
class Iter {
    private $_reader=null;
    private $_current_level;
    private $_hasData ;
    public function __construct($node) {
        if (  $node->nodeType == XMLReader::END_ELEMENT ) {
            throw new Exception("Should not initialize XML iterator on END elememnt");
        }
        $this->_reader = $node;
        $this->_current_level = $node->depth;
        $this->_hasData = true;
    }

    public function hasChild() {
        //print "!! ". $this->_reader->depth ." ". $this->_current_level;
        return   $this->_hasData && 
            ( $this->_reader->depth > $this->_current_level) ;
    }

    public function next() {
        $this->_hasData = $this->_reader->read() ;
        return $this->hasChild();
    }

    public function current() {
        return $this->_reader;
    }
}

/*
  XOBJ

  Super class for all XML elements, which should implement methods for populating
  content from XML attributes and sub elements.

  Note that classes inhering properties from their parents must forward the unhandled
  properties up in class hierachy. (using parent::populateFrom calls)
 */
abstract class XOBJ { 

    abstract protected function populateFromElements( $node);
    abstract protected function populateFromAttribs($node);
    
    public function __construct($node) {
        $this->init($node);
    }
    
    /* init method is called from constructor */
    protected function init ($node) {
        /* Attributes and text node (supports only one text node,
           if more then populateElements method should be used (name fo text node is #text) */
        $this->populateFromAttribs($node);
        /* Subelements */
        $iter = new Iter( $node);
        while ( $iter->next()) {
            $this->populateFromElements($iter->current());
        }
        return $this;
    }
    
}

/*
  Annotatable

  Most VarioML elements are annotatables i.e. they can have DbXRefs and Comments 
 */
abstract class Annotatable extends XOBJ { 
    public $comments = array();
    public $db_xrefs = array();

    protected function populateFromElements( $node) {
        switch( $node->localName) {
        case "db_xref":
            array_push($this->db_xrefs , new DbXRef($node));
            break;
        case "comment":
            array_push($this->comments , new Comment($node));
            break;
        default: break;
            // no need to forward properties...
        }
        
    }
}


abstract class Referenceable extends Annotatable {
    public $accession="";
    public $source="";
    public $uri;
    protected function populateFromAttribs($node) {
        $this->accession = $node->getAttribute("accession");
        $this->source = $node->getAttribute("source");
        $this->uri = $node->getAttribute("uri");
    }
    protected function populateFromElements($node) {
        parent::populateFromElements($node);
    }
}

abstract class DbReferenceable extends Referenceable {
    public $name ; // display name
    protected function populateFromAttribs($node) {
        $this->name = $node->getAttribute("name");
        parent::populateFromAttribs($node); //take the rest using parents method
    }
}

abstract class OntologyTerm extends Referenceable {
    public $term ; // CV term
    public $description;
    protected function populateFromAttribs($node) {
        $this->term = $node->getAttribute("term");
        parent::populateFromAttribs($node); //take the rest using parents method
    }
    protected function populateFromElements($node) {        
        switch( $node->localName) {
        case "description":
            $this->description = new Description($node);          
            break;
        default:break;
            //Note that plain ontology terms do not have annotations
            // we comment this out: parent::populateFromElements($node);
            //todo: improve
        }
    }
}

abstract class Observable extends Referenceable {
    public $values = array();
    public $evidence_codes = array();
    public $protocol_ids = array();
    public $observation_date;
    public $term ; // CV term (Observable is-a OntologyTerm, but methods implemented here since otherwise multiple inheritance is needed)
    public $description;
    protected function populateFromAttribs($node) {
        $this->term = $node->getAttribute("term");
        parent::populateFromAttribs($node); //take the rest using parents method
    }
    protected function populateFromElements($node) {
        switch( $node->localName) {
        case "protocol_id":
            array_push($this->protocol_ids , new ProtocolId($node));          
            break;
        case "description":
            $this->description = new Description($node);          
            break;
        default:break;
            //comment can have annotations and observation stuff like other comments, db_xrefs, protocols
            parent::populateFromElements($node);
        }
    }
}

class EvidenceCode extends Referenceable {
    public $term;
    public $string ; //we call text nodes as strings to maintain compatibility with the Java API
    public function populateFromAttribs($node) {
        $this->term = $node->getAttribute("term");
        $this->string = $node->readString(); // only one text node
    }
    protected function populateFromElements($node) {
        //no sub-elements (only text node)
    }
}

class ProtocolId extends Referenceable {
    
}
class DbXRef extends Referenceable {
}

class Text extends XOBJ {
    public $content_type;
    public $lang;
    public $encoding;
    public $string;
    protected function populateFromAttribs($node) {
        $this->content_type = $node->getAttribute("content_type");
        $this->lang = $node->getAttribute("lang");
        $this->encoding = $node->getAttribute("encoding");
        $this->string = $node->readString();
    }
    protected function populateFromElements($node) {
        //no sub-elements (only the text node.. see above)
    }

}

class Comment extends Observable {
    public $texts = array(); 
    protected function populateFromElements($node) {
        switch( $node->localName) {
        case "text":
            array_push($this->texts , new Text($node));          
            break;
        default:break;
            //comment can have annotations and observation stuff like other comments, db_xrefs, protocols
            parent::populateFromElements($node);
        }
    }
}

class Name extends XOBJ {
    public $scheme;
    public $string ; //we call text nodes as strings to maintain comatibility with the Java API
    public function populateFromAttribs($node) {
        $this->scheme = $node->getAttribute("scheme");
        $this->string = $node->readString(); // only one text node
    }
    protected function populateFromElements($node) {
        //no sub-elements (only text node)
    }
}

class VariantEvent extends Annotatable {
    public $ref_seq;
    public $name;
    public $genes = array();
    public $pathogenicities = array();
    public $seq_changes = array();

    protected function populateFromAttribs($node) {
        $this->type = $node->getAttribute("type");
    }

    protected function populateFromElements( $node) {
        switch( $node->localName) {
        case "name":
            $this->name = new Name($node);
            break;
        case "ref_seq":
            $this->ref_seq =  new DbXRef($node);
            break;
        case "gene":
            array_push($this->genes, new DbXRef($node));
            break;
        case "pathogenicity":
            array_push($this->pathogenicities, new Pathogenicity($node));
            break;
        case "seq_changes":
            array_push($this->seq_changes , new SeqChg($node));
            break;
        default:
            parent::populateFromElements($node);
            break;
        }      
    }
}


class Pathogenicity extends Observable {
    public $phenotype;
    public $evidence_code;
    public $scope;
    
    protected function populateFromAttribs($node) {
        $this->term = $node->getAttribute("term");
        $this->scope = $node->getAttribute("scope");
    }
    
    protected function populateFromElements($node) {
        switch( $node->localName) {

        case "phenotype":
            $this->phenotype = new Phenotype($node);
            break;
        case "evidence_code":
            $this->evidence_code =  new EvidenceCode($node);
            break;
        default:
            parent::populateFromElements($node);
            break;
        }  
    }
}

//todo: implement. The main variant
class Variant extends VariantEvent {

    protected function populateFromAttribs($node) {
        $this->type = $node->getAttribute("type");
    }

}


class SeqChg extends Observable {
    public $consequence;

    protected function populateFromAttribs($node) {
        
    }

    protected function populateFromElements($node) {
        switch( $node->localName) {
        case "name":
            $this->name = new Name($node);
            break;
        case "ref_seq":
            $this->ref_seq =  new DbXRef($node);
            break;
        case "consequence":
            $this->consequence = new EvidenceCode($node);
            break;
        case "seq_changes":
            array_push($this->seq_changes , new SeqChg($node));
            break;
        default:break;
            //comment can have annotations and observation stuff like other comments, db_xrefs, protocols
            parent::populateFromElements($node);
        }
    }
}

class Phenotype extends EvidenceCode {

}


$reader = new XMLReader();
$reader->open('../data/cafe_variome_example.xml');

while ($reader->read()) {
   if ($reader->nodeType == XMLREADER::ELEMENT) {
       //print $reader->name." ";
       switch ( $reader->localName ) {
       case "variant" : 
           $var = new Variant($reader);
           print " ===============\n";
           print "      VARIANT TYPE=".$var->type."\n";
           print $var->name->scheme." ".$var->name->string." 
           ACC=".$var->ref_seq->accession."\n";
           foreach ( $var->genes as $dbx ) {
               print "  GENE=".$dbx->accession."\n";
           }
           foreach ( $var->db_xrefs as $dbx ) {
               print "  DBXREF=".$dbx->accession."\n";
           }

           foreach ( $var->pathogenicities as $patho ) {
            print "  PATHOGENICITY=".$patho->term."\n";
            print "  SCOPE=".$patho->scope."\n";
            print "  PHENOTYPE=".$patho->phenotype->term."\n";
            print "  EVIDENCE CODE=".$patho->evidence_code->term."\n";
           }

           foreach ( $var->seq_changes as $seqch ) {
            print "  CONSEQUENCES:\n";
            print "    ".$seqch->name->scheme." ".$seqch->name->string."\n";
            print "      VARIANT TYPE=".$seqch->variant->type."\n";
            print "      ACC=".$seqch->ref_seq->accession."\n";
            print "      CONSEQUENCE=".$seqch->consequence->term."\n";
           }

           foreach ( $var->comments as $comm ) {
               foreach ( $comm->texts as $txt) {
                   print "  COMMENT=".$txt->string."\n";           
               }
           }

        break;

       case "name":
           break;
       default: break;
       }
   }
}
?>

