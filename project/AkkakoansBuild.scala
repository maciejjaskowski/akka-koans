import sbt._
import sbt.Keys._

object AkkakoansBuild extends Build {

  lazy val akkakoans = Project(
    id = "akka-koans",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "akka-koans",
      organization := "com.jaskowski",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.9.2",
      resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
      libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.5",
      libraryDependencies += "com.typesafe.akka" % "akka-testkit" % "2.0.5",
      libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test",
      libraryDependencies += "junit" % "junit" % "4.11" % "test"
    )
  )
}
