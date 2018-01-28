package com.julianpeeters.example.server

import com.julianpeeters.example.server.config.Config
import com.julianpeeters.example.server.db.migration.FlywayMigration

import cats.effect._
import eu.timepit.refined.auto._
import fs2._
import org.http4s.server.blaze.BlazeBuilder

object Server extends StreamApp[IO] {

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, StreamApp.ExitCode] =
    for {
      config   <- Config.load[IO]
      _        <- FlywayMigration.run[IO](config.db)
      exitCode <- BlazeBuilder[IO]
        .bindHttp(config.http.port, config.http.host)
        .mountService(Service.endpoints)
        .mountService(Service.assets, s"/${BuildInfo.assetsRoot}")
        .serve
    } yield exitCode

}