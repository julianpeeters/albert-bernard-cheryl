package com.julianpeeters.albertbernardcheryl

import com.julianpeeters.albertbernardcheryl.components.AppComponent

import cats.effect.IO
import org.scalajs.dom

object AlbertBernardCherylApp {
  
  def main(args: Array[String]): Unit = {

    val jsApp = for {
      container <- IO(dom.window.document.getElementById("js-app-hook"))
      _ = AppComponent.component().renderIntoDOM(container)
    } yield ()
    
    jsApp.unsafeRunSync()
    
  }
  
}
