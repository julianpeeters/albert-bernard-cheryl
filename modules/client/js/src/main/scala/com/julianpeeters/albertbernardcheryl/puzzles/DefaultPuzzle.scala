package com.julianpeeters.albertbernardcheryl
package puzzles

import com.julianpeeters.albertbernardcheryl.models._

object DefaultPuzzle {
  val puzzle = Puzzle(PuzzleId("41424344-4546-4748-494a-4b4c4d4e4f50"), PuzzleCompleted(false), PuzzleText("text?"), PuzzleTitle("title?"), PuzzleIntro("intro?"), StateSpace(List(
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("May"))), Parameter(Some(DimensionName("day")), Some(ValueName("15"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("May"))), Parameter(Some(DimensionName("day")), Some(ValueName("16"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("May"))), Parameter(Some(DimensionName("day")), Some(ValueName("19"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("Jun"))), Parameter(Some(DimensionName("day")), Some(ValueName("17"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("Jun"))), Parameter(Some(DimensionName("day")), Some(ValueName("18"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("Jul"))), Parameter(Some(DimensionName("day")), Some(ValueName("14"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("Jul"))), Parameter(Some(DimensionName("day")), Some(ValueName("16"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("Aug"))), Parameter(Some(DimensionName("day")), Some(ValueName("14"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("Aug"))), Parameter(Some(DimensionName("day")), Some(ValueName("15"))))),
      State(List(Parameter(Some(DimensionName("month")), Some(ValueName("Aug"))), Parameter(Some(DimensionName("day")), Some(ValueName("17"))))))), Map("Albert" -> Player("Albert"), "Bernard" -> Player("Bernard")), Statements(List.empty) , PuzzleQuestion("Why"), PuzzleAnswer(List.empty)
    )
}