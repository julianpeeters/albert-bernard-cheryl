package com.julianpeeters.albertbernardcheryl

import com.julianpeeters.albertbernardcheryl.components.AppComponent
import com.julianpeeters.albertbernardcheryl.components.modules.homepage.HomeComponent
import com.julianpeeters.albertbernardcheryl.components.modules.puzzlepage.PuzzleComponent

import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._

// routes
sealed trait AppPage
case object HomePage extends AppPage
case object PuzzlePage extends AppPage

// router
object AppRouter {

  def layout(puzzles: List[String])(c: RouterCtl[AppPage], r: Resolution[AppPage]) =
    AppComponent.component(AppComponent.Props(puzzles, c, r))

  def config(puzzles: List[String]) = RouterConfigDsl[AppPage].buildConfig { dsl =>
   import dsl._
    (trimSlashes
      | staticRoute(root, HomePage) ~> render(HomeComponent.component(puzzles))
      | staticRoute("#puzzle", PuzzlePage) ~> render(PuzzleComponent.component("blank board/choose a puzzle")))
      .notFound(redirectToPage(HomePage)(Redirect.Replace))
      .renderWith(layout(puzzles)(_,_))
  }
  
  def router(puzzles: List[String]) = Router(BaseUrl.fromWindowOrigin, config(puzzles))
  
}
