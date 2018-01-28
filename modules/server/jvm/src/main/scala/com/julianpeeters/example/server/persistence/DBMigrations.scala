package com.julianpeeters.example.server.persistence

import cats.implicits._
import cats.effect.Sync
import fs2._
import org.flywaydb.core.Flyway

object DBMigrations {
  def run[F[_]: Sync](url: String, user: String, password: String): Stream[F, Unit] =
    Stream.eval(Sync[F].delay {
      val flyway = new Flyway
      flyway.setDataSource(url, user, password)
      flyway.setLocations("classpath:db/migration")
      flyway.migrate()
    }.void)
}