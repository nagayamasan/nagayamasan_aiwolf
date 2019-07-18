name := "nagayamasan_aiwolf"

scalaVersion := "2.12.6"

resolvers += "LiCOS-JSON4Scala-snapshots-repository" at "https://github.com/ktr-skmt/LiCOS-JSON4Scala/raw/master/maven-repo/snapshots"
libraryDependencies ++= Seq("us.feliscat" % "feliscatuszerolibraries_2.12" % "0.0.1",
  // https://mvnrepository.com/artifact/org.apache.spark/spark-core
  "org.apache.spark" %% "spark-core" % "2.4.3",
  //"org.apache.spark" %% "spark-mllib" % "2.4.3" % "runtime"
  "org.apache.spark" %% "spark-mllib" % "2.4.3",
  "online.licos" % "licos-json4scala_2.12" % "0.0.1",
  "joda-time" % "joda-time" % "2.9.2",
  "org.joda" % "joda-convert" % "1.8" // http://www.joda.org/joda-convert/

)

