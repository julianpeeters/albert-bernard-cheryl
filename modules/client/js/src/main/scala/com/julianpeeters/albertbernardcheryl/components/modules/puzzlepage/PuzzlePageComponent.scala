package com.julianpeeters.albertbernardcheryl
package components
package modules
package puzzlepage

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object PuzzlePageComponent {
  val component =
    ScalaComponent.builder[String]("PuzzlePage")
      .render_P(name =>
        <.div(
          ^.cls := "puzzlepage",
          name))
      .build

}