name := """proyecto-encomiendas"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
  "org.postgis" % "postgis-jdbc" % "1.3.1",
  "be.objectify"  %% "deadbolt-java"     % "2.3.2",
  "com.feth" %% "play-authenticate" % "0.6.8"
)

resolvers += Resolver.url("Objectify Play Repository", url("http://deadbolt.ws/releases/"))(Resolver.ivyStylePatterns)