package com.julianpeeters.albertbernardcheryl
package components
package modules
package puzzlepage

import com.julianpeeters.albertbernardcheryl.models.Puzzle

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object PuzzlePageComponent {
  
  case class Props(
    puzzle: Puzzle
  )
  
  val component =
    ScalaComponent.builder[Props]("PuzzlePage")
      .render_P(P =>
        <.div(
          ^.cls := "puzzlepage",
          P.puzzle.title.text))
      .build

}