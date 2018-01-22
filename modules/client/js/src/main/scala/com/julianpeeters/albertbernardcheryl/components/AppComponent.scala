package com.julianpeeters.albertbernardcheryl
package components

import com.julianpeeters.albertbernardcheryl.components.navbar.NavComponent

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._
// import scala.concurrent.Future
// import scala.concurrent.ExecutionContext.Implicits.global

object AppComponent {
  
  case class State(puzzles: List[String], currentPuzzle: String)
  object State {
    val initial = State(List.empty, "initial")
  }
  
  case class Props(
    p: List[String],
    c: RouterCtl[AppPage],
    r: Resolution[AppPage])

  class Backend(scope: BackendScope[Props, State]) {
    // def mounted: Callback =
    //   Callback.future(Future(List("puzzle from server"))
    //     .map(retrieved => scope.modState(_.copy(puzzles = retrieved))))//getUserResponse >>= dispatchUserInfo >>= loadAndDispatchCitiesWeather
    def render(P: Props): VdomElement = {
      <.div(
        // navbar
        NavComponent.navMenu(NavComponent.Props(
          navMenuItems = List(
            NavComponent.NavMenuItem("Home " + scope, HomePage),
            NavComponent.NavMenuItem("Puzzle", PuzzlePage)
          ), selectedPage = P.r.page, ctrl = P.c)),
        // currently active module is shown in this container
        <.div(^.cls := "container", P.r.render()))
    }
  }

  val component = ScalaComponent.builder[Props]("AppComponent")
    .initialState(State.initial)
    .renderBackend[Backend]
  //  .componentDidMount(scope => scope.backend.mounted)
    .build
}