lazy val commonSettings = Seq(
	organization := "nl.vsmeets",
	version := "0.1",
	scalaVersion := "2.11.8",
	scalacOptions += "-deprecation")

lazy val comTypesafeAkkaVersion = "2.4.11"
lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % comTypesafeAkkaVersion

lazy val orgSlf4jVersion = "1.7.10"
lazy val slf4jSimple = "org.slf4j" % "slf4j-simple" % orgSlf4jVersion

lazy val timetracker = (project in file(".")).
	settings(commonSettings: _*).
	settings(
		libraryDependencies ++= Seq(
			akkaActor,
			slf4jSimple))
