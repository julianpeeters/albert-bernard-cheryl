package com.julianpeeters.albertbernardcheryl
package components

import com.julianpeeters.albertbernardcheryl.components.navbar.NavComponent
import com.julianpeeters.albertbernardcheryl.components.modules.homepage.HomeComponent
import com.julianpeeters.albertbernardcheryl.components.modules.puzzlepage.PuzzlePageComponent
import com.julianpeeters.albertbernardcheryl.models._

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object AppComponent {
  
  case class State(
    appPage: AppPage,
    puzzles: List[String],
    currentPuzzle: String)

  object State {
    val initial = State(HomePage, List.empty, "blank board/choose a puzzle")
  }

  class Backend(scope: BackendScope[Unit, State]) {
    def init: Callback =
      Callback.future(Future(List("puzzle1 from server", "puzzle2 from server"))
        .map(retrieved => scope.modState(_.copy(puzzles = retrieved))))//getUserResponse >>= dispatchUserInfo >>= loadAndDispatchCitiesWeather
    def render(S: State): VdomElement = {
      <.div(
        // navbar
        NavComponent.navMenu(NavComponent.Props(
          navMenuItems = List(
            NavComponent.NavMenuItem("Home ", HomePage),
            NavComponent.NavMenuItem("Puzzle", PuzzlePage)
          ), selectedPage = S.appPage, scope)),
        // active module is shown in this container
        <.div(^.cls := "container", S.appPage match {
          case HomePage => HomeComponent.component(HomeComponent.Props(scope, S))
          case PuzzlePage => PuzzlePageComponent.component(S.currentPuzzle)
        }))
    }
  }

  val component = ScalaComponent.builder[Unit]("AppComponent")
    .initialState(State.initial)
    .renderBackend[Backend]
    .componentDidMount(scope => scope.backend.init)
    .build
}