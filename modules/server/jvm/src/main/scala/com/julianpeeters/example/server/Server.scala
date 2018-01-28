package com.julianpeeters.albertbernardcheryl.server

import com.julianpeeters.example.server.config.Config

import cats.effect._
import eu.timepit.refined.auto._
import fs2._
import org.http4s.server.blaze.BlazeBuilder

object Server extends StreamApp[IO] {

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, StreamApp.ExitCode] =
    for {
      cfg      <- Config.load[IO]
      exitCode <- BlazeBuilder[IO]
        .bindHttp(cfg.http.port, cfg.http.host)
        .mountService(Service.endpoints)
        .mountService(Service.assets, s"/${BuildInfo.assetsRoot}")
        .serve
    } yield exitCode

}