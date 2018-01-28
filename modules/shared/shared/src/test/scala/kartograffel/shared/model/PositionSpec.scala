package com.julianpeeters.example.shared.model

import io.circe.testing.CodecTests
import io.circe.testing.instances._
import com.julianpeeters.example.shared.model.ArbitraryInstances._
import org.scalatest.FunSuite
import org.typelevel.discipline.scalatest.Discipline

class PositionSpec extends FunSuite with Discipline {
  checkAll("Position", CodecTests[Position].codec)
}
