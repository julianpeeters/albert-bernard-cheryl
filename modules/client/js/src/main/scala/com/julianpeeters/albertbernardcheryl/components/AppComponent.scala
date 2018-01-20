package com.julianpeeters.albertbernardcheryl.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object AppComponent {
  val Hello =
  ScalaComponent.builder[String]("Hello")
    .render_P(name => <.div("Hello there ", name))
    .build
}