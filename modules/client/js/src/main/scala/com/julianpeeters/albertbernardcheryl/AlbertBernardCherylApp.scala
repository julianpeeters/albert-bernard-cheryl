package com.julianpeeters.albertbernardcheryl

import org.scalajs.dom
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object AlbertBernardCherylApp {
  
  def main(args: Array[String]): Unit = {

    val _ = for {
      puzzles   <- Future(List("from server1", "from server2"))
      container <- Future(dom.window.document.getElementById("js-app-hook"))
      jsApp = AppRouter.router(puzzles)
      _     = jsApp().renderIntoDOM(container)
    } yield ()
    
  }
  
}
