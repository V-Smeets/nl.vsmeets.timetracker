lazy val commonSettings = Seq(
	organization := "nl.vsmeets",
	version := "0.1",
	scalaVersion := "2.11.8"
)

lazy val timetracker = (project in file(".")).
	settings(commonSettings: _*)
