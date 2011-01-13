import sbt._
import de.element34.sbteclipsify._

class HelloWorldProject(info: ProjectInfo) extends DefaultProject(info) with Eclipsify {
  val liftVersion = "2.2"
  
  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile" withSources(),
    
    // These dependencies listed only to fetch all the source JARs, so we can 
    // click through to the source inside Eclipse:
    "net.liftweb" %% "lift-common" % liftVersion % "compile" withSources(),
    "net.liftweb" %% "lift-util" % liftVersion % "compile" withSources()
    
  ) ++ super.libraryDependencies
}
