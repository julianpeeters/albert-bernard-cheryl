package com.julianpeeters.albertbernardcheryl.server

import scalatags.Text.all.Modifier

object Scripts {
  val jsScript = s"${BuildInfo.assetsPath}/client-jsdeps.js"
  val jsDeps = s"${BuildInfo.assetsPath}/client-${BuildInfo.jsOptPostfix}.js"
  val jsScripts: Seq[Modifier] = {
    import scalatags.Text.all._
    List(
      script(`type` := "text/javascript", src := jsScript),
      script(`type` := "text/javascript", src := jsDeps)
    )
  }
}