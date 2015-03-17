name := "storm-scala"

version := "1.0"

scalaVersion := "2.11.6"


crossScalaVersions := Seq("2.10.4", "2.11.6")

// libraryDependencies := Seq(
//  "org.scala-lang" % "scala-library" % "2.11.6")

//libraryDependencies += "org.apache.kafka" % "kafka_2.9.2" % "0.8.1.1" % "provided" //exclude("jmxri", "com.sun.jmx")

//libraryDependencies += "org.apache.kafka" % "kafka_2.10" % "0.8.1.1" % "provided" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri")
//exclude("org.slf4j", "slf4j-simple")
//exclude("log4j", "log4j")
//exclude("org.apache.zookeeper", "zookeeper")
//exclude("com.101tec", "zkclient")

//libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"

//libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.6"

//libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.5"

//libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "org.apache.storm" % "storm-core" % "0.9.3" % "provided" exclude("junit", "junit")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "org.apache.storm" % "storm-core" % "0.9.2-incubating" % "provided" exclude("junit", "junit")

scalacOptions ++= Seq("-feature", "-deprecation", "-Yresolve-term-conflict:package")

// When doing sbt run, fork a separate process.  This is apparently needed by storm.
fork := true

resolvers += "clojars" at "https://clojars.org/repo"