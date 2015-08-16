import com.lihaoyi.workbench.Plugin._

enablePlugins(ScalaJSPlugin)

workbenchSettings

name := "albert-bernard-cheryl"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.8.0"
)

bootSnippet := "puzzle.CherylsBirthday().main(document.getElementById('canvas'));"

refreshBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)

