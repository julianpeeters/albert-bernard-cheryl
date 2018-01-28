package com.julianpeeters.example
package components
package modules
package itempage

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object ItemComponent {
  val component =
    ScalaComponent.builder[String]("Item")
      .render_P(name =>
        <.div(
          ^.cls := "itempage",
          name))
      .build

}