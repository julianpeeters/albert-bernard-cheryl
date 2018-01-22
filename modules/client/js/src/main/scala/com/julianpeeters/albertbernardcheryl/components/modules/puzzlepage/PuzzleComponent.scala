package com.julianpeeters.albertbernardcheryl
package components
package modules
package puzzlepage

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object PuzzleComponent {
  val component =
    ScalaComponent.builder[String]("Puzzle")
      .render_P(name =>
        <.div(
          ^.cls := "puzzlepage",
          name))
      .build

}