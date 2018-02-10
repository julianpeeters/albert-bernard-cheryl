package com.julianpeeters.albertbernardcheryl.shared.domain.model

import io.circe.testing.CodecTests
import io.circe.testing.instances._
import com.julianpeeters.albertbernardcheryl.shared.model.ArbitraryInstances._
import org.scalatest.FunSuite
import org.typelevel.discipline.scalatest.Discipline

class UsernameTest extends FunSuite with Discipline {
  checkAll("Username", CodecTests[Username].codec)
}
