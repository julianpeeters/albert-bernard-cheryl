package com.julianpeeters.albertbernardcheryl.server.db

import cats.effect.IO
import cats.implicits._
import doobie.{ConnectionIO, Transactor}
import doobie.implicits._
import doobie.specs2.analysisspec.IOChecker
import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString
import com.julianpeeters.albertbernardcheryl.server.infrastructure.doobie.DoobieMigration
import com.julianpeeters.albertbernardcheryl.server.{BuildInfo, Config}
import org.specs2.mutable.Specification

trait DbSpecification extends Specification with IOChecker {
  DbSpecification.runMigrationOnce

  override def transactor: Transactor[IO] =
    DbSpecification.transactor

  implicit class ConnectionIoOps[A](val self: ConnectionIO[A]) {
    def yolo: A = self.transact(transactor).unsafeRunSync()
  }
}

object DbSpecification {
  val dbConfig: Config.Db = {
    val path = s"${BuildInfo.crossTarget.toString}/h2/com/julianpeeters/albertbernardcheryl"
    val url = NonEmptyString.unsafeFrom(
      s"jdbc:h2:$path;MODE=PostgreSQL;AUTO_SERVER=TRUE")

    Config.Db(
      driver = "org.h2.Driver",
      url = url,
      user = "",
      password = ""
    )
  }

  lazy val runMigrationOnce: Unit =
    DoobieMigration.run[IO](dbConfig).void.unsafeRunSync()

  val transactor: Transactor[IO] =
    Transactor.fromDriverManager[IO](
      driver = dbConfig.driver,
      url = dbConfig.url,
      user = dbConfig.user,
      pass = dbConfig.password
    )
}
