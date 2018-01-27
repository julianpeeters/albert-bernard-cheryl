package com.julianpeeters.albertbernardcheryl.server
package views

import scalatags.Text.TypedTag
import scalatags.Text.all.Modifier

object Page {

  def template(
      headContent: Seq[Modifier],
      bodyContent: Seq[Modifier],
      scripts: Seq[Modifier],
      cssStyles: Seq[Modifier]): TypedTag[String] = {
    import scalatags.Text.all._
  
    html(
      head(
        headContent,
        cssStyles,
        link(rel:="shortcut icon", media:="image/png", href:="/assets/images/favicon.png")
      ),
      body(
        bodyContent,
        scripts
      )
    )
  
  }

}