import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._

object ScalaJSReactMdl {
  object Versions {
    val scala = "2.11.8"
    val scalatest = "3.0.1"
    val htmlWebpackPlugin = "~2.26.0"
    val htmlLoader = "~0.4.3"
    val react = "~15.4.2"
    val scalaJsReact = "0.1.0-SNAPSHOT"
  }

  object Dependencies {
    lazy val scalaJsReact = "com.github.eldis" %%%! "scalajs-react" % Versions.scalaJsReact

    lazy val scalatest = "org.scalatest" %%%! "scalatest" % Versions.scalatest % "test"

    lazy val jsReact = Seq(
      "react" -> Versions.react,
      "react-dom" -> Versions.react,
      "react-mdl" -> "^1.7.2"
    )
  }

  object Settings {
    type PC = Project => Project

    def commonProject: PC =
      _.settings(
        scalaVersion := Versions.scala,
        organization := "com.github.eldis"
      )

    def scalajsProject: PC =
      _.configure(commonProject)
        .enablePlugins(ScalaJSPlugin)
        .settings(
          requiresDOM in Test := true
        )

    def jsBundler: PC =
      _.enablePlugins(ScalaJSBundlerPlugin)
        .settings(
          enableReloadWorkflow := false,
          libraryDependencies += Dependencies.scalatest
        )

    def react: PC =
      _.settings(
        libraryDependencies ++= Seq(Dependencies.scalaJsReact),
        npmDevDependencies in Compile ++= Dependencies.jsReact,
        npmDependencies in Compile ++= Dependencies.jsReact
      )


    def exampleProject(prjName: String): PC = { p: Project =>
      p.in(file("examples") / prjName)
        .configure(scalajsProject, jsBundler, react)
        .settings(
          name := prjName,

          npmDevDependencies in Compile ++= Seq(
            "html-webpack-plugin" -> Versions.htmlWebpackPlugin,
            "html-loader" -> Versions.htmlLoader
          ),

          webpackConfigFile in fastOptJS := Some(baseDirectory.value / "config" / "webpack.config.js"),
          webpackConfigFile in fullOptJS := Some(baseDirectory.value / "config" / "webpack.config.js")
        )
    }

    def publish: PC =
      _.settings(
        publishMavenStyle := true,
        publishTo := {
          val nexus = "https://oss.sonatype.org/"
          if (isSnapshot.value)
            Some("snapshots" at nexus + "content/repositories/snapshots")
          else
            Some("releases"  at nexus + "service/local/staging/deploy/maven2")
        }
      )
  }

  object Projects {
    lazy val scalaJsReactMdl = project.in(file("."))
      .configure(
        Settings.scalajsProject, Settings.jsBundler, Settings.publish, Settings.react
      )
      .settings(
        name := "scalajs-react-mdl"
      )

    lazy val ex1 = project.configure(
        Settings.exampleProject("ex1")
      )
      .dependsOn(scalaJsReactMdl)
  }
}