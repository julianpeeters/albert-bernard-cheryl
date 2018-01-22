package com.julianpeeters.albertbernardcheryl
package components
package navbar

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._

object NavComponent {
  
  case class NavMenuItem(name: String, route: AppPage)
  
  case class Props(navMenuItems: List[NavMenuItem],
                   selectedPage: AppPage,
                   ctrl: RouterCtl[AppPage])

  implicit val currentPageReuse = Reusability.by_==[AppPage]
  implicit val propsReuse = Reusability.by((_: Props).selectedPage)

  val navMenu = ScalaComponent.builder[Props]("Menu")
    .render_P { P =>
      def nav(name: String, target: AppPage) =
        <.li(
          ^.cls := "navbar-brand active",
          P.ctrl setOnClick target,
          name)
      <.div(
        ^.cls := "navbar navbar-default",
        <.ul(
          ^.cls := "navbar-header",
          P.navMenuItems.toTagMod(i => nav(i.name, i.route))))
    }
    .configure(Reusability.shouldComponentUpdate)
    .build
}