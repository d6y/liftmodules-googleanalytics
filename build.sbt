val liftVersion = SettingKey[String]("liftVersion", "Full version number of the Lift Web Framework")
val liftEdition = SettingKey[String]("liftEdition", "Lift Edition (short version number to append to artifact name)")

name := "google-analytics"

organization := "net.liftmodules"

version := "1.2.0-SNAPSHOT"

liftVersion := "3.2.0"

liftEdition := liftVersion.value.replaceAllLiterally("-SNAPSHOT", "").split('.').take(2).mkString(".")

moduleName := name.value + "_" + liftEdition.value

crossScalaVersions := Seq("2.12.2", "2.11.11")

scalaVersion := crossScalaVersions.value.head

scalacOptions ++= Seq("-unchecked", "-deprecation")

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

resolvers +=  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"


libraryDependencies +=
  "net.liftweb" %% "lift-webkit" % liftVersion.value % "provided"

publishTo := (version.value.endsWith("SNAPSHOT") match {
  case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
  case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
)
credentials ++= (for {
  username <- Option(System.getenv().get("SONATYPE_USERNAME"))
  password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
} yield Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", username, password)).toSeq

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com:d6y/liftmodules-googleanalytics</url>
  <licenses>
    <license>
        <name>Apache 2.0 License</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
   </licenses>
   <scm>
      <url>git@github.com:d6y/liftmodules-googleanalytics.git</url>
      <connection>scm:git:git@github.com:d6y/liftmodules-googleanalytics.git</connection>
   </scm>
   <developers>
      <developer>
        <id>d6y</id>
        <name>Richard Dallaway</name>
        <url>http://richard.dallaway.com</url>
     </developer>
   </developers>
 )


// Thank you:
// https://github.com/sbt/sbt.github.com/blob/gen-master/src/jekyll/using_sonatype.md
// https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide
