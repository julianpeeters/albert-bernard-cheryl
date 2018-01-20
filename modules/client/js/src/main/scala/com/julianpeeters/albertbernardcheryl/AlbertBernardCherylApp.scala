package com.julianpeeters.albertbernardcheryl

import com.julianpeeters.albertbernardcheryl.components.AppComponent

import org.scalajs.dom

object AlbertBernardCherylApp {
  
  def main(args: Array[String]): Unit = {
    val page = AppComponent.Hello("world!")
    val _ = page.renderIntoDOM(dom.window.document.getElementById("js-app-hook"))
  }
  
}
