name := "Warthog"

organization := "org.warthog"

version := "0.1-SNAPSHOT"

libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "1.7",
    "org.specs2" %% "specs2-scalaz-core" % "6.0.1" % "test",
    "net.java.dev.jna" % "jna" % "3.3.0")

resolvers += "snapshots" at "http://scala-tools.org/repo-snapshots"

scalaVersion := "2.9.1"

initialCommands in console := """
    import org.warthog.generic._
    import pl._
    import fol._
"""

//scalacOptions ++= Seq("-unchecked", "-deprecation")