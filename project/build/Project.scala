import sbt._
import de.element34.sbteclipsify._

class HelloWorldProject(info: ProjectInfo) extends DefaultProject(info) with Eclipsify {
  
  lazy val liftVersion = "2.3"

  // E.g., google-analytics_2.8.1-2.3-1.0.jar
  //       project name _ Scala verison - Lift version - module version
  override def artifactBaseName = artifactID + "-" + liftVersion + "-" +  version.toString

  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile" withSources(),
    
    // These dependencies listed only to fetch all the source JARs, so we can 
    // click through to the source inside Eclipse:
    "net.liftweb" %% "lift-common" % liftVersion % "compile" withSources(),
    "net.liftweb" %% "lift-util" % liftVersion % "compile" withSources()
    
  ) ++ super.libraryDependencies
}
