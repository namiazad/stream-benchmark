name := "functional-programming-in-scala"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies += "org.openjdk.jmh" % "jmh-core" % "1.5.2"

jmhSettings

mainClass in Compile := Some("com.nami.fpinscala.MemoryUsage")