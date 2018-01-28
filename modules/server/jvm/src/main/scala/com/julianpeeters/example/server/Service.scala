package com.julianpeeters.example.server

import views.Page

import cats.effect.IO
import cats.data._
import org.http4s._
import org.http4s.CacheDirective._
import org.http4s.dsl._
import org.http4s.MediaType._
import org.http4s.headers._
import org.http4s.server.staticcontent.{webjarService, WebjarService}
import scalatags.Text.all._

object Service extends Http4sDsl[IO] {

  val endpoints = HttpService[IO] {
    case GET -> Root =>
      Ok(Page.template(Seq(), Seq(div(id:="js-app-hook")), Scripts.jsScripts, Seq()).render)
        .map(_.withContentType(`Content-Type`(`text/html`, Charset.`UTF-8`))
          .putHeaders(`Cache-Control`(NonEmptyList.of(`no-cache`()))))
  }
  
  val assets: HttpService[IO] =
    webjarService(WebjarService.Config())

}
