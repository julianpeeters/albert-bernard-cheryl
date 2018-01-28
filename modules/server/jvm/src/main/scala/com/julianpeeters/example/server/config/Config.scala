package com.julianpeeters.example.server.config

import com.julianpeeters.example.server.config.Config.{Db, Http}

import cats.effect.Sync
import eu.timepit.refined.pureconfig._
import eu.timepit.refined.types.net.PortNumber
import eu.timepit.refined.types.string.NonEmptyString
import fs2._

final case class Config(
    http: Http,
    db: Db
)

object Config {
  final case class Http(
      host: NonEmptyString,
      port: PortNumber
  )

  final case class Db(
      driver: NonEmptyString,
      url: NonEmptyString,
      user: String,
      password: String
  )

  def load[F[_]](implicit F: Sync[F]): Stream[F, Config] =
    Stream.eval(F.pure(pureconfig.loadConfigOrThrow[Config]))
}