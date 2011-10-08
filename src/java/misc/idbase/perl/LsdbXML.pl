#!/usr/bin/perl -w 

use strict;

use XML::Code;
my $xml_txt = new XML::Code('=');


my $lineCounter = 0;
my $line;
my $report_id;
my @names    = ();
my @relative = ();
my $acc;
my $desc = "";
my $cdate;
my $udate;
my @refs = ();
my $clinicalSymptoms;
my $auto;
my $title     = "";
my $auths     = "";
my $refLoc    = "";
my $refNumber = 0;
my $xref;
my $refNote;
my $dbXref;
my $diag;
my $occur;
my $ocode;
my $comment;
my $errorCode;
my $featureHeader;
my $sex;
my $ethnic;
my $age;
my $symptom;
my $familyHistory;
my $haplotype = "";
my %feature;
my %sexMap;
my $disease;
my $omim;
my $gene;
my $database;
my $address;
my $curator;
my $date;
my $systematicNames;
my $parents;
my @protein=();
my $mrna;

sub sexMap {
	my $s = shift;
	if ( $s eq 'XX' ) {
		return 2;
	}
	elsif ( $s eq 'XY' ) {
		return 1;
	}
	else {
		die "unknown sex $s";
	}
}

sub p() {
	my $a = shift;
	$xml_txt->set_text($a);
	my $code = $xml_txt->code;
	$code =~ s/\'/\&apos\;/g;
	$code =~ s/[^[:ascii:]]//g;
	$code =~ s/\s&\s/ and /g;
	$code;
}

sub init {
	# sorry ..the code is crappy
	#todo: move the record handling hack into subroutine... and remove this
	undef $parents;
	undef $age;
	undef $symptom;
	undef $ethnic;
	undef $sex;
	undef $line;
	undef @names;
	undef @relative;
	undef $acc;
	undef $desc;
	undef $cdate;
	undef $udate;
	undef @refs;
	undef $title;
	undef $auths;
	undef $refLoc;
	$refNumber = 0;
	undef $xref;
	undef $refNote;
	undef $dbXref;
	undef $diag;
	undef $occur;
	undef $ocode;
	undef $comment;
	@protein = ();
	undef $mrna;
	undef $errorCode;
	undef $familyHistory;
	undef $haplotype;
	undef $featureHeader;
	undef %feature;
	undef $systematicNames;
}

sub ccat {
	my $a1  = shift;
	my $a2  = shift;
	my $sep = shift;
	$sep = ' ' unless defined $sep;
	if ( !defined $a1 || $a1 eq "" ) {
		return $a2;
	}
	else {
		return $a1 . $sep . $a2;
	}
}

while ( defined( $line = <> ) ) {

        $lineCounter++;
	chop $line;
	my $curator;
	my $ack;
	my $url;
	my $phone;
	my $address;
	my $email;
	my $version;
	my $date;

	#todo: move the block outsde the main loop
	if ( $line =~ /^Database\s+(.+)$/ ) {
		
		$database = $1;
		do {
			$line = <>;
                        $lineCounter++;
			die unless defined $line;
			if ( $line =~ /^Disease\s+(.+)/ ) {
				$disease = $1;
			}
			elsif ( $line =~ /^Gene\s+(.+)/ ) {
				$gene = $1;
			}
			elsif ( $line =~ /^OMIM\s+(.+)/ ) {
				$omim = $1;
			}
			elsif ( $line =~ /^Curator\s+(.+)$/ ) {
				$curator = $1;
			}
			elsif ( $line =~ /^Funding\s+(.+)$/ ) {
				$acc = ccat( $acc, $1, '|' );
			}
			elsif ( $line =~ /^Email\s+(.+)$/ ) {
				$email = $1;
			}
			elsif ( $line =~ /^Phone\s+(.+)$/ ) {
				$phone = $1;
			}
			elsif ( $line =~ /^URL\s+(.+)$/ ) {
				$url = $1;
			}
			elsif ( $line =~ /^Version\s+(.+)$/ ) {
				$version = $1;
			}
			elsif ( $line =~ /^Date\s+(.+)$/ ) {
				$date = $1;
			}
			elsif ( $line =~ /^Address\s+(.+)$/ ) {
				$address = ccat( $address, $1, ',' );
			}
		} until $line =~ /^ID\s+/;

		print "<?xml version='1.0' encoding='UTF-8'?>\n";
		print
			  "<lsdb id='submission0001' xmlns='http://gen2phen.org/lsdb/2.0'\n  xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
		  .   "\n  xsi:schemaLocation='http://gen2phen.org/lsdb/1.1 \n  file:/Users/muilu/Documents/Dev/SVN_repos/gen2phen/trunk/data_formats/xml/lsdb.xsd'>\n";
		print
			  " <source id='$database' version='$version' uri='$url'>\n";
		print "	<name>$database</name>\n";
		print "	<url>$url</url>\n";
		print "	<contact>\n";
		print "		<name>$curator</name>\n";
		print "		<address>$address</address>\n";
		print "	</contact>\n";

		foreach my $a (split( /\|/, $acc )) {
			print "	<acknowledgement><name>$a</name></acknowledgement>\n";
		}
		print " </source>\n";
	}

	if ( $line =~ /^ID\s+(.+)\;/ ) {

		$report_id = $1;
		if  ( $line !~ /standard; MUTATION/) {
                        # todo: fix line counter
			while ( defined ( $line = <>) && $line !~ /^\/\//  ){				
                          $lineCounter++;
			}
			exit if ! defined $line;
			next;
			
		}
		die "Non standard id line: $line\n"
		  unless $line =~ /standard; MUTATION/;
		init();

	}
	elsif ( $line =~ /^Accession\s+(\S+)/ ) {

		$acc = $1;

	}
	elsif ( $line =~ /^Systematic name\s+(.+)\s*$/ ) {

		#my @n = split /\s*,\s*/, $1;
		#push( @names, @n );
		my $dat = $1;
		$systematicNames = ccat( $systematicNames, $dat );

	}
	elsif ( $line =~ /Description\s+(.+)$/ ) {

		$desc = ccat( $desc, $1 );

	}
	elsif ( $line =~ /Date\s+(\d\d-[A-Za-z]{3}-\d{4})\s+(.+)$/ ) {

		my $date = $1;
		my $c    = $2;
		$cdate = $date if ( $c =~ /Created/ );
		$udate = $date if ( $c =~ /Updated/ );
		die unless defined $cdate || defined $udate;

	}
	elsif ( $line =~ /^DB CrossRef\s+(.+)$/ ) {

		$dbXref = ccat( $dbXref, $1 );

	}
	elsif ( $line =~ /^Diagnosis\s+(.+)/ ) {

		$diag = ccat( $diag, $1 );

	}
	elsif ( $line =~ /^Occurrence\s+(.+)/ ) {

		$occur = ccat( $occur, $1 );

	}
	elsif ( $line =~ /^Original code\s+(.+)$/ ) {

		$ocode = $1;

	}
	elsif ( $line =~ /^Feature\s+(.+)$/ ) {

		my $sep = '*';
		my $dat = $1;
		$sep = '**' if $dat =~ /^aa;/ || $dat =~ /^rna/;
		$feature{$featureHeader} = ccat( $feature{$featureHeader}, $dat, $sep );

	}
	elsif ( $line =~ /^Comment\s+(.+)$/ ) {

		my $txt = $1;
		$txt =~ s/^-\!-//;
		$comment = ccat( $comment, $txt );

	}
	elsif ( $line =~ /^Protein\s+(\S+)\s+(.+)$/ ) {
		my $comment = "<comment term='$1'><text>$2</text></comment>";
		push @protein,$comment;
	}
	
	elsif ( $line =~ /^Family history\s+(.+)$/ ) {

		$familyHistory = $1;

	}
	elsif ( $line =~ / Sorry! HERE!!!\s+(.+)$/ ) {

		$errorCode = ccat( $errorCode, $1 );

	}
	elsif ( $line =~ /^Haplotype\s+(.+)$/ ) {

		$haplotype = ( defined $haplotype ) ? $haplotype . $1 : $haplotype;

	}
	elsif ( $line =~ /^Ref([A-Za-z]+)\s+(.+)$/ ) {

		my $cde = $1;
		my $txt = $2;

		if ( $cde eq "Number" ) {

			if ( defined $refNumber && $refNumber > 0 ) {

				my @refRec = ( $xref, $auths, $title, $refLoc, $refNote );
				push( @refs, \@refRec );

			}

			$refNumber++;

		}
		elsif ( $cde eq "CrossRef" ) {

			$xref = ccat( $xref, $txt );

		}
		elsif ( $cde eq "Authors" ) {

			$auths = ccat( $auths, $txt, "" );

		}
		elsif ( $cde eq "Title" ) {

			$title = ccat( $title, $txt );

		}
		elsif ( $cde eq "Loc" ) {

			$refLoc = ccat( $refLoc, $txt );

		}
		elsif ( $cde eq "Note" ) {

			$refNote = ccat( $refNote, $txt );

		}
		else {

			die(" Unknown line $line \n");

		}

	}
	elsif ( $line =~ /^Sex\s+([XY]+)/ ) {

		$sex = $1;

	}
	elsif ( $line =~ /^Ethnic origin\s+(\S.+)$/ ) {

		$ethnic = $1;

	}
	elsif ( $line =~ /^Symptoms\s+(\S.+)$/ ) {
		next if $1 =~ /^Clinical characteristics/;
		$symptom = ccat($symptom,$1);

	}
	elsif ( $line =~ /^Relative\s+(.*)$/ ) {

		push( @relative, $1 );

	}
	elsif ( $line =~ /^Age\s+(\d+)/ ) {

		$age = $1;

	}
	elsif ( $line =~ "^//" ) {

		&dumpXML();

	}
	elsif ( $line =~ /^FeatureHeader\s+(.+)$/ ) {
		$featureHeader = $1;
	}
	elsif ( $line =~ /^Parents\s+(.+)$/) {
	
		$parents = ccat( $parents,$1);
		
	}
	else {

		print STDERR "Unknown line : $line \n" if $line ne "";

	}

}

print "</lsdb>\n";

sub dumpXML() {

	return if ( !defined $systematicNames );
	return if ( $systematicNames =~ /;/ );

	print " <individual id='$acc'>\n";
	print "	<gender code='" . &sexMap($sex) . "'/>\n" if defined $sex;
	print "	<gender code='9'/>\n" if !defined $sex;
	$diag =~ s/'//g if defined $diag;

	if ( defined $diag ) { 
		print "	<phenotype term='$diag'>\n" 
	} else {
		die unless $disease;
		print "	<phenotype term='$disease'>\n";
		print "		<comment term='data quality'><text>Information is based on database description. Actual diagnose is missing</text></comment>\n"; 		
	} 
	if ( defined $symptom) {
		my @sys = split /\s*;\s*/,$symptom;
		
		if ( scalar(@sys) > 0 ) {

			foreach my $ix (@sys) {
				print "		<comment term='symptom'><text>".&p($ix)."</text></comment>\n";			
			}

			
		} else {

			print "		<comment term='symptom'><text>".&p($symptom)."</text></comment>\n";
			
		}
		print "	</phenotype>\n";
	} else {
		print "	</phenotype>\n";		
	}
	
	if ( defined $ethnic ) {
		my $eth;
		my $regin;
		if ( $ethnic =~ /([A-Za-z ]+);\s*([A-Za-z ]*)/ ) {
			$eth   = $1;
			$regin = $2;

			#print "ABABA" .$ethnic."\n";
			if ( defined $regin && length($regin) == 0 ) {
				undef $regin;
			}
		}
		elsif ( $ethnic =~ /([A-Za-z ]+)\s*$/ ) {
			$regin = $1;
		}
		print "	<population term='$eth' type='ethnic' />\n"   if defined $eth;
		print "	<population term='$regin' type='region' />\n" if defined $regin;
	}

	my $diploidcount = 1.0;
	my $allele1;
	my $allele2;
	if ( $systematicNames =~ /^Allele 1 and 2[;:]\s+(.+)/ ) {

		$allele1      = $1;
		$diploidcount = 2.0;
		$allele1 =~ s/, / /g;

		my ($descript) = $desc =~ /Allele 1 and 2[;:]\s+(.+)$/;
		die $desc unless defined $descript;
		&printVariant( $allele1, $diploidcount, $feature{'allele; 1'},
			$descript );

	}
	elsif ( $systematicNames =~ /^Allele 1[:;]\s+(.+)/ ) {

		$allele1 = $1;
		undef $allele2;
		$allele1 =~ s/, / /g;
		if ( $allele1 =~ /Allele 2[;:]\s+(.+)/ ) {

			$allele2 = $1;
			$allele2 =~ s/, / /g;
			$allele1 =~ s/Allele 2[:;].+$//;
		}

		my ($descript) = $desc =~ /Allele 1[;:]\s+(.+)$/;
		if ( ! defined $descript) {
			die "Unknown description: $desc\n";
		}
		$descript =~ s/Allele 2[;:].+//;
		die $desc unless defined $descript;
		&printVariant( $allele1, $diploidcount, $feature{'allele; 1'},
			$descript );

		if ( defined $allele2 ) {
			$allele1 =~ s/, / /g;

			my ($descript) = $desc =~ /Allele 2[;:]\s+(.+)$/;
			die $desc unless defined $descript;
			&printVariant( $allele2, $diploidcount, $feature{'allele; 2'},
				$descript );

		}

	}
	else {

		die "unknown sysName line A: $systematicNames\n";

	}
	die if !defined $allele1;
	print "	<db_xref  source='OMIM' accession='$omim' />\n" if defined $omim;
	print " </individual>\n"

}

sub printVariant() {

	my $allele       = shift;
	my $diploidcount = shift;
	my $d1           = shift;
	my $desc         = shift;
	die unless defined $desc && defined $d1;

	# uses global varaibles: gene ..

	my @names = split( / /, $allele );
	my $rnaORaa = 0;
	for ( my $i = 0 ; $i < scalar(@names) ; $i++ ) {
		$names[$i] =~ s/\>/&gt;/g;

		#$names[$1] =~ s/,\s*$//;
		$rnaORaa = ( $names[$i] =~ /^[ra]\./ || $rnaORaa );
	}
	die("unknown sysName line B: $systematicNames") if scalar(@names) == 0;

	print "	<variant diploid_count='$diploidcount'>\n";
	print "		<gene source='HGNC' accession='" . $gene . "'/>\n";

	die("no g dna name $names[0]") if $names[0] !~ /^g\./;

	my ( $name, $source, $refseq, $note ) = &parseFeature( $d1, 'dna' );

	print "		<ref_seq source='$source' accession='$refseq' />\n";

	print "		<name scheme='HGVS'>" . $names[0] . "</name>\n";

	die if !defined $d1;
	my ( $exon, $n ) = $d1 =~ /genomic_region:\s+(\S+);\s+(\d)/;
	if ( $exon eq "exon" && $n > 0 ) {
		print "		<exon>e$n</exon>\n";
	}
	elsif ( $exon eq "intron" ) {
		print "		<exon>i$n</exon>\n";
	}

	if ( defined $desc ) {
		print "		<consequence term='".&p($desc)."'/\>\n" if defined $desc;
	}
	
	print "		<consequence term='".&p($note)."'/>\n" if defined $note;

	if ($rnaORaa && $d1 !~ /inexloc/) {
		print "		<seq_changes>\n";
		for ( my $i = 1 ; $i < scalar(@names) ; $i++ ) {
			my $aaDone = 0;
			if ( $names[$i] =~ /^r\./ ) {
				if ( $d1 !~ /rna;\s+\d/ ) {
					print STDERR
"RNA details not found for $names[$i] . Feature line = $d1\n";
					next;
				}
				my ( $name, $source, $refseq, $note ) =
				  &parseFeature( $d1, 'rna' );
				print "			<variant type='RNA'>\n";
				print "				<ref_seq source='$source' accession='$refseq' />\n";
				print "				<name scheme='HGVS'>" . $names[$i] . "</name>\n";
				print "				<consequence term='".&p($note)."'/>\n" if defined $note;

				if ( scalar(@names) - 1 > $i ) {
					if ( $names[ $i + 1 ] =~ /^p./ ) {
						my ( $name, $source, $refseq, $note ) =
						  &parseFeature( $d1, 'aa' );
						print "				<seq_changes>\n";
						print "					<variant type='AA'>\n";
						print
"						<ref_seq source='$source' accession='$refseq' />\n";
						print "						<name scheme='HGVS'>"
						  . $names[ $i + 1 ]
						  . "</name>\n";
						print "						<consequence term='".&p($note)."'/>\n"
						  if defined $note;
						print "					</variant>\n";
						print "				</seq_changes>\n"

					}
					else {
						
						print STDERR "Aminoacid expected: $names[$i+1]  $names[$i]";

					}
				}

				print "			</variant>\n";
			}
		}
		print "		</seq_changes>\n";
	}

	print "	</variant>\n";

}

sub parseFeature( ) {
	my $f   = shift;
	my $seq = shift;

#my ($name, $source, $refseq) = $f =~ /$seq;\s+\d+.+\/name: +(\S+.+\S+)\*\/loc:\s+(\S+):\s+(\S+)[:;].+\*\*/ ;
	my ( $name1, $source1, $refseq1 );
	my $found = 0;
	my $note;
	my @xxx = split( /\*\*/, $f );
	die if scalar(@xxx) == 0;
	foreach my $x (@xxx) {
		if ( $x =~ /^${seq};/ ) {
			my @u = split( /\*/, $x );
			die if scalar(@u) == 0;
			foreach my $y (@u) {
				if ( $y =~ /^\/loc/ ) {
					#die("Already found! Seq $seq X=$x Y=$y:") if $found
					if ( $found) {
						print STDERR "Duplicated entry: $seq on line $lineCounter";
						next;					
					}
					$found = 1;
					( $source1, $refseq1 ) =
					  $y =~ /\/loc:\s+(\S+)[;:]\s+(\S+)[:;]/;
					die("check $y") unless defined $source1 && defined $refseq1;
				}
				elsif ( $y =~ /^\/inexloc:/ ) {
					$found   = 1;
					$source1 = "unknown";
					$refseq1 = "unknown";
				}
				elsif ( $y =~ /\/note:\s+(.+)$/ ) {
					$note = ccat( $note, $1, ' ' );
				}
			}
		}
	}
	#die("not found: $seq from: $f") unless $found;
	$name1 = "unknown" if !defined $name1;
	$source1 = "unknown" if !defined $source1;
	$refseq1 = "unknown" if !defined $refseq1;
	return ( $name1, $source1, $refseq1, $note );
}

sub escape() {
	my $a = shift;
	my $re = $a =~ s/\>/&gt;/g;
	$re;
}

sub dump() {

	my @refRec = ( $xref, $auths, $title, $refLoc, $refNote );
	push( @refs, \@refRec );
	print "\n";
	print "Report ID:  $report_id\n";
	print "Names       ";
	foreach my $i (@names) { print "$i "; }
	print "\n";
	print "Accession   $acc\n"   if defined $acc;
	print "Description $desc\n"  if defined $desc;
	print "Cretaed     $cdate\n" if defined $cdate;
	print "Updated     $udate\n" if defined $udate;

	my $ix = 1;

	foreach my $i (@refs) {
		print "Referrences\n";
		print "  Xrefs       $$i[0]\n" if defined $$i[0];
		print "  Authors     $$i[1]\n" if defined $$i[1];
		print "  Title       $$i[2]\n" if defined $$i[2];
		print "  Refloc      $$i[3]\n" if defined $$i[3];
		print "  Note        $$i[4]\n" if defined $$i[4];
	}

	print "Crossref     $dbXref\n"  if defined $dbXref;
	print "Diagnose     $diag\n"    if defined $diag;
	print "Occurence    $occur\n"   if defined $occur;
	print "Patient id   $ocode\n"   if defined $ocode;
	print "Age          $age\n"     if defined $age;
	print "Symptom      $symptom\n" if defined $symptom;
	print "Sex          $sex\n"     if defined $sex;

	print "Relatives\n";
	foreach my $i (@relative) {
		print "  $i\n";
	}

	print "Comment      $comment\n"         if defined $comment;
	#print "Protein      $protein\n"   		;
	print "Error        $errorCode\n"       if defined $errorCode;
	print "Family history $familyHistory\n" if defined $familyHistory;
	print "Haplotype	$haplotype\n"          if defined $haplotype;

	foreach my $i ( keys %feature ) {
		print "Feature: $i : $feature{$i}\n";
		if ( $feature{$i} =~ /rna; (\d+)(.+)aa; (\d+)/ ) {
			my ($name) = $2 =~ /name: (\S+)/;
			print "DNA NAME $name\n";
		}
	}
}
