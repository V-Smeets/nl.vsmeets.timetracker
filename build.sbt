lazy val commonSettings = Seq(
	organization := "nl.vsmeets",
	version := "0.1",
	scalaVersion := "2.11.8",
	scalacOptions += "-deprecation")

lazy val comH2databaseVersion = "1.4.192"
lazy val h2 = "com.h2database" % "h2" % comH2databaseVersion

lazy val comTypesafeAkkaVersion = "2.4.11"
lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % comTypesafeAkkaVersion
lazy val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % comTypesafeAkkaVersion

lazy val orgFlywaydbVersion = "4.0.3"
lazy val flywayCore = "org.flywaydb" % "flyway-core" % orgFlywaydbVersion

lazy val orgScalatestVersion = "3.0.0"
lazy val scalatest = "org.scalatest" %% "scalatest" % orgScalatestVersion

lazy val orgSlf4jVersion = "1.7.10"
lazy val slf4jSimple = "org.slf4j" % "slf4j-simple" % orgSlf4jVersion

lazy val timetracker = (project in file(".")).
	settings(commonSettings: _*).
	settings(
		libraryDependencies ++= Seq(
			akkaActor,
			flywayCore,
			h2,
			slf4jSimple,
			akkaTestkit % "test",
			scalatest % "test"))
