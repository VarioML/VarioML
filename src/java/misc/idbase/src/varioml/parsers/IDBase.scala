package varioml.parsers
import scala.util.matching.Regex
import scala.util.matching.Regex
import scala.collection.immutable.Stack
import scala.collection.immutable.{ List, HashMap }
import org.varioml.data._
import org.varioml.util._
import scala.io._

class Feature(val seqType: String, val ref: Int) {
  var line: List[String] = List[String]()
  def add(text: String): Unit = {
    line ::= text
  }
  override def toString(): String = {
    var tmp = "[" + seqType + " " + ref + ": "
    line foreach ((line) => {
      tmp += line + " | "
    })
    return tmp + "]"
  }
}

class FeatureTable(val allele: String) {
  private var seqLevel: HashMap[Int, Feature] = new HashMap[Int, Feature]
  def leves = seqLevel
  var min: Int = 9999
  var max: Int = 0

  def add(feat: Feature): Unit = {
    seqLevel += feat.ref -> feat
    //get number range for sequence features
    if (feat.ref > max) max = feat.ref
    if (feat.ref < min) min = feat.ref
  }
  override def toString(): String = {
    var tmp: String = "[ allele " + allele + ":"
    seqLevel foreach ((tuple) => {
      tmp += tuple._2
    })
    return tmp + "]"
  }
  def get(level: Int) : Feature = {
    val f = seqLevel.get(level)
    if ( f.isDefined ) {
      return f.get
    } else {
      return null
    }
  }
}

class RecordType(val text: String) {

  override def toString(): String = return text

}

class Record() {
  var disease: String = "";
  var propertyMap = HashMap[IDBField, RecordType]();
  var featureTables = List[FeatureTable]()

  def add(key: IDBField, text: String): Unit = {
    val v = propertyMap.get(key)
    propertyMap += key -> (if (!v.isDefined) new RecordType(text) else new RecordType(v.get + " " + text))
  }
  def add(rec: FeatureTable): Unit = {
    featureTables ::= rec
  }

  def getFt( allele: String) : FeatureTable = {
    var all=""
    var _tmp: FeatureTable = null  
    for ( i <- 0 until featureTables.length ; if _tmp == null) {
      if ( featureTables(i).allele.equals(allele)) { _tmp = featureTables(i) } 
      all += featureTables(i).allele
    }
    assert(_tmp!=null,"cannot get feature table for allele >"+allele+"< ("+all+")")
    return _tmp
  }
  
  def get(key: IDBField): String = {
    val o = propertyMap.get(key)
    if (o.isDefined) {
      return o.get.text
    }
    return null
  }
}

class DataBatch {
  var header: Record = null;
  var records: List[Record] = List[Record]()
  def getHeader(f: IDBField): String = {
    var o = header.propertyMap.get(f)
    if (o.isDefined) {
      return o.get.text
    } else {
      return null
    }
  }
}

class IDBField(val name: String) {
  var regex: Regex = (name + """\s+(.+)""").r
  def setRegex(regexStr: String) {
    regex = (name + regexStr).r
  }
  override def toString(): String = {
    return name + " (Regex: " + regex + ")"
  }
}

object IDBase {
  val Database = new IDBField("Database")
  val Version = new IDBField("Version")
  val Curator = new IDBField("Curator")
  val File = new IDBField("File")
  val Phone = new IDBField("Phone")
  val Fax = new IDBField("Fax")
  val Email = new IDBField("Email")
  val URL = new IDBField("URL")
  val FTP = new IDBField("FTP")
  val Disease = new IDBField("Disease")
  val Gene = new IDBField("Gene")
  val Address = new IDBField("Address")
  val Sequence = new IDBField("Sequence")
  val Funding = new IDBField("Funding")
  val Comments = new IDBField("Comments")
  val Description = new IDBField("Description")
  val SystematicName = new IDBField("Systematic name")
  val Accession = new IDBField("Accession")
  val ID = new IDBField("ID")
  val Sex = new IDBField("Sex")
  val OMIM = new IDBField("OMIM")
  val GDB = new IDBField("GDB")
  val OriginalCode = new IDBField("Original code")
  val Date = new IDBField("Date")
  val RefNumber = new IDBField("RefNumber")
  val RefCrossRef = new IDBField("RefCrossRef")
  val RefAuthors = new IDBField("RefAuthors")
  val RefTitle = new IDBField("RefTitle")
  val RefLoc = new IDBField("RefLoc")

  val Numbering = new IDBField("Numbering")
  val EthnicOrigin = new IDBField("Ethnic origin")
  val Relative = new IDBField("Relative")
  val Diagnosis = new IDBField("Diagnosis")
  val Symptoms = new IDBField("Symptoms")
  val IDRfactfile = new IDBField("IDR factfile")
  val FeatureHeader = new IDBField("FeatureHeader"); FeatureHeader.setRegex("""\s+allele;?\s+(\d+)""")
  val Feature = new IDBField("Feature"); Feature.setRegex("""\s+(rna|dna|aa);?\s+(\d+)""")
  val FeatureElems = new IDBField("Feature"); FeatureElems.setRegex("""\s+/(.+)""")

  val EOR = new IDBField("//"); EOR.setRegex("")

  val fields = List(Database, Version, File, Curator, Address, Phone, Fax,
    Email, URL, FTP, IDRfactfile, Gene, Disease,
    OMIM, GDB, Sequence, Numbering, Funding, Comments,
    ID, Accession, SystematicName, OriginalCode, Description, Date, RefNumber, RefCrossRef,
    RefAuthors, RefTitle, RefLoc, Sex, Diagnosis, EthnicOrigin, Relative, Symptoms);

  def handleRecord(lines: List[String]): Record = {

    val vml = new Record();
    for (i <- 0 until lines.length) {
      val line = lines(i)
      //println("LINE "+i+" "+line)

      line match {

        case FeatureHeader.regex(allele) => {
          var ft = new FeatureTable(allele)
          var j = i + 1;
          var inLoop = true
          while (inLoop) {
            lines(j) match {
              case Feature.regex(mol, num) => {
                var feat = new Feature(mol, num.toInt)
                ft.add(feat)
                var k = j + 1
                var inLoop = true
                while (inLoop) {
                  lines(k) match {
                    case FeatureElems.regex(text) => {
                      feat.add(text.trim())
                    }
                    case _ => {
                      assert(k > (j + 1))
                      inLoop = false
                    }
                  }
                  k += 1
                }
                j = k - 2
              }

              case _ => {
                assert(j > (i + 10))
                inLoop = false
              }
            }
            j += 1
          }
          vml.add(ft)
        }
        case _ => {

          fields foreach ((field) => {
            //todo: optimize... now unnecessary comparisons
            line match {
              case field.regex(text) => {
                vml.add(field, text.trim())
              }

              case _ => {

              }
            }

          })
        }
      }

    }

    return vml;
  }

  def parse(source: scala.io.Source): DataBatch = {

    var lines = List[String]()
    var batch = new DataBatch();

    source.getLines.foreach((line) => {

      line match {

        case EOR.regex() => {
          val rec: Record = handleRecord(lines.reverse)
          lines = List[String]()
          if (batch.header == null) {
            batch.header = rec
          } else batch.records ::= rec
        }

        case _ => {
          lines ::= line
        }
      }

    })

    return batch
  }

  def createVariant( name: String , tpe : String ) : Variant = {
    
    val v = new Variant()
    val vn = new VariantName()
    vn.setString(name)
    v.setTypeAttr(tpe)
    v.setName(vn)
    return v
  }

  //sequence consequence rDNA or AA
  def createConsVariant( name: String , tpe : String ) : ConsVariant = {
    
    val v = new ConsVariant()
    val vn = new VariantName()
    vn.setString(name)
    v.setTypeAttr(tpe)
    v.setName(vn)
    return v
  }

 
  def getFeatureLines(  mol: String , num: Int, ft: FeatureTable ) : List[String]= {
	  ft.leves foreach ( ( l) => {
	    if ( l._1.equals(num) && l._2.seqType.equals(mol)) {
	    	return l._2.line
	    }
	  })  
	  assert( false,"feature "+mol+" "+num+" not found. Feature table="+ft)
	  return null  	    	  
  }
  
  def getFeatureProperty(prop: String, mol: String, num: Int, ft: FeatureTable ) : String = { 
    val r = (prop+":"+"""\s+(.+)""").r
    getFeatureLines( mol,num, ft) foreach ( (line) =>{
      line match { 
        case r(text) => { 
          return text
        }
        case _ =>
      }      
    })
    return null
  }
  
  def getLocations( mol: String, num: Int, ft: FeatureTable) : List[(String,(Int,Int))] = {
    var locations = List[(String,(Int,Int))]()
    val loc = getFeatureProperty("loc",mol,num,ft)
    assert( loc != null, "Cannot find location entry for: " +mol+" "+num)
    val acc = loc split """\s*;\s*"""
    val IntRange = """([\S^:]+):\s+(\d+)\.+(\d+)\s*""".r;
    val IntStart = """([\S^:]+):\s+(\d+)\s*""".r;
    val DbIntRange = """([\S^:]+):\s+([\S^:]+):\s+(\d+)\.+(\d+)\s*""".r;
    val DbIntStart = """([\S^:]+):\s+([\S^:]+):\s+(\d+)\s*""".r;
    val DbAcc = """([\S^:]+):\s*([\S^:]+)\s*""".r;
    
    var _start: Int = -1;
    var _end: Int = -1;
    acc.reverse foreach ((a) =>{
      a match  { 
        case IntRange(acc,start,end) => {
          assert( _start == -1)
          _start = start.toInt
          _end = end.toInt
          locations ::=  (acc,(_start,_end))
        }
        case IntStart(acc,start) => {
          assert( _start == -1)
          _start = start.toInt
          _end = start.toInt
          locations ::=  (acc,(_start,_end))
          
        }
        case DbIntRange(db,acc,start,end) => {
          assert( _start == -1)
          _start = start.toInt
          _end = end.toInt
          locations ::=  (db+":"+acc,(_start,_end))
          
        }
        case DbIntStart(db,acc,start) => {
          assert( _start == -1)
          _start = start.toInt
          _end = start.toInt          
          locations ::=  (db+":"+acc,(_start,_end))
        }
        case DbAcc(db,acc) => {
          assert( _start > -1)
          locations ::=  (db+":"+acc,(_start,_end))
        }
        case _ => {
          assert(false,"unknown location entry: "+a)
        }
        
      }
    } ) 
    return locations
  }
  def toXML(batch: DataBatch): CafeVariome = {

    val lsdb = new CafeVariome()
    val source = new org.varioml.data.Source()
    val contact = new Contact()
    contact.setName(batch.getHeader(Curator))
    contact.setAddress(batch.getHeader(Address))
    source.setName(batch.getHeader(Database))
    source.addContact(contact)
    var ack = new Acknowledgement()
    ack.setName(batch.getHeader(Funding))
    source.addAcknowledgement(ack)

    lsdb.addSource(source)

    batch.records foreach ((rec) => {

      val inv = new Individual()
      val panel = new Panel()
      val vari = new Variant()
      lsdb.addVariant( vari)
      panel.addIndividual(inv)
      vari.addPanel(panel)
      inv.setIdAttr(rec.get(Accession))
      val pheno = new Phenotype()
      pheno.setTermAttr(rec.get(Disease)) //NOTE THIS IS FENOTYPE TESTED.. 
      panel.addPhenotype(pheno)
      if (rec.get(OMIM) != null) {
        pheno.setAccessionAttr(rec.get(OMIM))
        pheno.setSourceAttr("OMIM")
      }
      val symptoms = rec.get(Symptoms)
      if  ( symptoms != null ) {
	      symptoms.split(";") foreach ((s) => {
	        val comment = new Comment()
	        comment.setTermAttr("symptom")
	        val text = new CommentText()
	        text.setString(s.trim)
	        comment.addText(text)
	        pheno.addComment(comment)
	      })
        
      }

      val sysName = rec.get(SystematicName)
      if (sysName != null ) {

        val alleles =  sysName.split("""Allele\s+""") ;
        //two alleles
        val v1  = new Variant()
        val aliases = new Aliases()
        val seqChanges = new SeqChanges()
        
        val Dna = """^g\..+""".r
        val cDna = """^c\..+""".r
        val rDna = """^r\..+""".r
        val AA = """^p\..+""".r
        
        //see also prf1pub.dat
        alleles foreach ( (a)=>{
          println(a)
        	if (  a.matches( """1:\s+.+""")) {
        	  
        	  val sl = a.replaceFirst("""1:?\s+""","").split("""\s*,\s*""")
        	  val ft = rec.getFt("1")
        	  println(">~~~"+ft.allele)
        	  assert(sl.length>0)
        	  
        	  //testing
        	  var counter= 0;
        	  sl foreach ( (s) => {
        		  s match  { 
        		    case Dna() => { 
        		      counter += 1 ;
        		    	println("  DNA")
        		      val locs = getLocations("dna",counter,ft)
        		      if ( locs != null ) {
	        		      locs foreach ( (loc)=>{
	        		        println (loc._1 +" || "+loc._2._1+"..."+loc._2._1)
	        		      }) 
	        		        
        		      }
        		    }
        		    case cDna() => { 
        		    	println("  CDNA")
        		    }
        		    case rDna() => { 
        		    	println("  RNA")
        		       counter += 1 
        		      val locs = getLocations("rna",counter,ft)
        		      if ( locs != null ) {
	        		      locs foreach ( (loc)=>{
	        		        println (loc._1 +" || "+loc._2._1+"..."+loc._2._1)
	        		      }) 
	        		        
        		      }
        		    }        		    
        		    case AA() => { 
        		      counter += 1 
        		    	println("  RNA")
        		      val locs = getLocations("aa",counter,ft)
        		      if ( locs != null ) {
	        		      locs foreach ( (loc)=>{
	        		        println (loc._1 +" || "+loc._2._1+"..."+loc._2._1)
	        		      }) 
	        		        
        		      }
        		    }
        		  }
        	  } )
//        	  
//        	  if ( sl(0).matches(Dna) ) {
//        		    val name = new VariantName()
//        		    name.setString(sl(0).trim())
//        			v1.setTypeAttr("DNA") ;
//        			v1.setName( name )
//        			var notFound = true 
//        			for( i <- 1 until sl.length ; if notFound) {
//        			  if ( sl(i).matches(cDna)) {
//        				  val v = createVariant( sl(i).trim(),"cDNA")        			    
//		        	        aliases.addVariant( v)
//		        	        v1.setAliases(aliases)
//		        	        notFound = false
//	                      }
//        			}
//        			
//        			
//        			notFound = true; 
//
//        			for( i <- 1 until sl.length ; if notFound) {
//        			  if ( sl(i).matches(rDna)) {
//        				  println(sl(i))
//        				    val v = createConsVariant( sl(i).trim(),"rDNA")        			    
//		        	        seqChanges.addVariant( v)
//		        	        
//		        	        v1.setAliases(aliases)
//		        	        notFound = false		        	        
//	                      }
//        			}
//
//
//        	  } else {
//        	    assert(false,"check entry "+rec.get(Accession))
//        	  }
        	}
        })
            
      }

    })

    XMLUtil.write(lsdb, "tmp.xml")
    return lsdb
  }

  
  def main(args: Array[String]) {
    assert(args.length > 0)

    val file = args(0);
    val s = scala.io.Source.fromFile(file)
    val batch = parse(s)

    toXML(batch)
    batch.header.propertyMap foreach ((tuple) => {
      //println(tuple._1 + " Value= " + tuple._2)
    })
    batch.records.foreach((rec) => {
      rec.featureTables foreach ((ft) => {
        //println(ft)
      })
    })
  }
}