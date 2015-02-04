name := """varioml"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

resolvers ++= Seq(
        "Typesafe Repository" at "http://repo.typesafe.com/repo/typesafe",
        "Typesafe" at "http://repo.typesafe.com/typesafe/repo",
        "repo.codahale.com" at "http://repo.codahale.com",
        "Sonatype releases"  at "http://oss.sonatype.org/content/repositories/releases"
)

resolvers += "Local Maven Repository" at "file:///"+Path.userHome+"/.m2/repository"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.0"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.7"

libraryDependencies += "com.typesafe.scala-logging" % "scala-logging-slf4j_2.11" % "2.1.2"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

libraryDependencies += "com.typesafe.slick" % "slick-codegen_2.11" % "2.1.0"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.33"

libraryDependencies += "org.apache.poi" % "poi" % "3.10.1"

libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.10.1"

