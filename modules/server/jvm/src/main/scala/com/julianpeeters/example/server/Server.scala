package com.julianpeeters.example.server

import com.julianpeeters.example.server.persistence.DBMigrations
//import scala.util.Properties.envOrNone

import cats.effect._
import eu.timepit.refined.auto._
import fs2._
import org.http4s.server.blaze.BlazeBuilder

object Server extends StreamApp[IO] {

  //val ip: String = "0.0.0.0"

  override def stream(
    
  //   for {
  //   config <- Config.load[IO]
  //   _ <- DoobieMigration.run[IO](config.db)
  //   xa <- db.transactor[IO](config.db)
  //   gr = GraffelRepository.transactional(xa)
  // } yield blazeBuilder(config.http, gr)
  // 
    args: List[String], requestShutdown: IO[Unit]): Stream[IO, StreamApp.ExitCode] =
    for {
      cfg      <- Config.load[IO]
      _        <- DBMigrations.run[IO](cfg.db.url, cfg.db.user, cfg.db.password)
      exitCode <- BlazeBuilder[IO]
        .bindHttp(cfg.http.port, cfg.http.host)
        .mountService(Service.endpoints)
        .mountService(Service.assets, s"/${BuildInfo.assetsRoot}")
        .serve
    } yield exitCode

}