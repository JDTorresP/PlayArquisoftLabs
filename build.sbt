name := """MarketPlace"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean,LauncherJarPlugin)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  evolutions,
  javaJdbc,
  cache,
  javaWs,
 "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
)



fork in run := true