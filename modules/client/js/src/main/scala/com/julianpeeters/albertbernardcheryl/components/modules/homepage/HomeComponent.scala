package com.julianpeeters.albertbernardcheryl
package components
package modules
package homepage

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object HomeComponent {
  val component =
    ScalaComponent.builder[List[String]]("Home")
      .render_P(puzzles =>
        <.div(
          <.ul(
            ^.cls := "navbar-header",
            puzzles.toTagMod(puzzle => <.li(puzzle)))
        ))
      .build

}