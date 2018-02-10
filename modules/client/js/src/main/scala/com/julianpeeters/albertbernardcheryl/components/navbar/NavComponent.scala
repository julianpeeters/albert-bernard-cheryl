package com.julianpeeters.albertbernardcheryl
package components
package navbar

import com.julianpeeters.albertbernardcheryl.models.AppPage

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.vdom.html_<^._

object NavComponent {
  
  case class NavMenuItem(name: String, route: AppPage)
  
  case class Props(navMenuItems: List[NavMenuItem],
                   selectedPage: AppPage,
                   scope: BackendScope[Unit, AppComponent.State])

  implicit val currentPageReuse = Reusability.by_==[AppPage]
  implicit val propsReuse = Reusability.by((_: Props).selectedPage)

  val navMenu = ScalaComponent.builder[Props]("Menu")
    .render_P { P =>
      def nav(name: String, target: AppPage) =
        <.li(
          ^.cls := "navbar-brand active",
          ^.onClick --> P.scope.modState(_.copy(appPage = target)),
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