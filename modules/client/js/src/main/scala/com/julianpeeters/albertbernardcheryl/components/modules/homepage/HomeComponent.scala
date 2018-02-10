package com.julianpeeters.albertbernardcheryl
package components
package modules
package homepage

import com.julianpeeters.albertbernardcheryl.models.PuzzlePage

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object HomeComponent {
  
  case class Props(
    scope: BackendScope[Unit, AppComponent.State],
    state: AppComponent.State
  )
  
  val component =
    ScalaComponent.builder[Props]("Home")
      .render_P(P =>
        <.div(
          <.ul(
            ^.cls := "homepage",
            // list puzzles, load and go to puzzle page when a puzzle is clicked
            P.state.puzzles.toTagMod(puzzle => {
              <.li(
                ^.onClick -->
                  P.scope.modState(_.copy(
                    appPage = PuzzlePage,
                    currentPuzzle = puzzle)),
                puzzle)
            }))
        ))
      .build

}