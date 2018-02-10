package com.julianpeeters.albertbernardcheryl.server.db.migration

import com.julianpeeters.albertbernardcheryl.server.config.Config

import cats.implicits._
import cats.effect.Sync
import eu.timepit.refined.auto._
import fs2._
import org.flywaydb.core.Flyway

object FlywayMigration {
  def run[F[_]: Sync](config: Config.Db): Stream[F, Unit] =
    Stream.eval(Sync[F].delay {
      val flyway = new Flyway
      flyway.setDataSource(config.url, config.user, config.password)
      flyway.setLocations("classpath:db/migration")
      flyway.migrate()
    }.void)
    
}