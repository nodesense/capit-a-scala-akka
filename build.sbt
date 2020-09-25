version := "0.1"

scalaVersion := "2.13.1"

name := "capita-scala-workshop"

lazy val akkaVersion = "2.6.5"
lazy val akkaHttpVersion  = "10.2.0-M1"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion

libraryDependencies += "com.typesafe" % "config" % "1.3.3"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.2.0-M1"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.0-M1"
libraryDependencies += "com.typesafe.akka" %% "akka-stream"     % akkaVersion
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-cluster
libraryDependencies += "com.typesafe.akka" %% "akka-cluster" % akkaVersion



libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.9.5" % "test")



// libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"

// https://mvnrepository.com/artifact/com.typesafe.slick/slick
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3"

// https://mvnrepository.com/artifact/com.h2database/h2
libraryDependencies += "com.h2database" % "h2" % "1.4.200"

// https://mvnrepository.com/artifact/com.typesafe.slick/slick-hikaricp
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3"

