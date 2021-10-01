name := "learning-cats-typeclasses"

ThisBuild / scalaVersion := "2.13.6"

ThisBuild / githubWorkflowPublishTargetBranches := Seq()

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.3.0",
  "org.scalactic" %% "scalactic" % "3.2.10",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test",
)
