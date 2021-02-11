import sbt.Keys._

name := "getting-dressed"

lazy val versionNameProperties = Seq(
  organization := "org.learn.task",
  version := "0.0.1"
)

lazy val scalaProperties = Seq(scalaVersion := "2.11.8")

val Slf4jVersion = "1.7.18"

lazy val commonSettings =
  scalaProperties ++
    versionNameProperties

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % Slf4jVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.1.0",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

lazy val `getting-dressed` = (project in file("."))
  .settings(commonSettings: _*)
  .enablePlugins(JavaAppPackaging, JavaServerAppPackaging, UniversalDeployPlugin, UniversalPlugin)
	



