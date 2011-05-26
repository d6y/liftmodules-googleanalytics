import sbt._
import de.element34.sbteclipsify._

class HelloWorldProject(info: ProjectInfo) extends DefaultProject(info) with Eclipsify {
  
  val liftVersion = "2.3"

  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile" withSources(),
    
    // These dependencies listed only to fetch all the source JARs, so we can 
    // click through to the source inside Eclipse:
    "net.liftweb" %% "lift-common" % liftVersion % "compile" withSources(),
    "net.liftweb" %% "lift-util" % liftVersion % "compile" withSources()
    
  ) ++ super.libraryDependencies

  // To publish to the cloudbees repos:
  override def managedStyle = ManagedStyle.Maven
  val publishTo = "liftmodules repository" at "https://repository-liftmodules.forge.cloudbees.com/release/"

  lazy val repo_user = system[String]("repo.user")
  lazy val repo_password = system[String]("repo.password")

  // The name and domain format here are not arbitrary:
  Credentials.add("liftmodules repository", "repository-liftmodules.forge.cloudbees.com", repo_user.value, repo_password.value)
 
  // Had to manually create directory structure using webdav :-(
  // otherwise I received a Forbidden response from the repository

}
