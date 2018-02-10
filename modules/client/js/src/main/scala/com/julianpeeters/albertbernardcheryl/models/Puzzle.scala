package com.julianpeeters.albertbernardcheryl
package models

case class Puzzle(
  id: PuzzleId,
  completed: PuzzleCompleted,
  text: PuzzleText,
  title: PuzzleTitle,
  intro: PuzzleIntro,
  initialPossibleStates: StateSpace,
  players: Map[String, Player],
  statements: Statements,
  question: PuzzleQuestion,
  answer: PuzzleAnswer
)

case class PuzzleId(text: String)
case class PuzzleCompleted(value: Boolean)
case class PuzzleText(text: String)
case class PuzzleTitle(text: String)
case class PuzzleIntro(text: String)
case class PuzzleQuestion(text: String) 
case class PuzzleAnswer(correctStates: List[State])



case class DimensionName(name: String)
case class ValueName(name: String)

case class ModelKnowledgeKey(modelKey: List[Parameter])
case class ModelKnowledgeValues(modelValues: List[List[ModelKnowledgeKey]])

case class Parameter(maybeDimensionName: Option[DimensionName], maybeValueName: Option[ValueName])
case class State(paramsMap: List[Parameter])//Map[MaybeDimensionName, MaybeValueName])
case class StateSpace(possibleStates: List[State])

case class Statements(texts: List[Statement])
case class Statement(speaker: Player, content: String)

case class Player(
  name: String,
  knownPossibilities: List[State] = List.empty[State],
  knownKeys: List[Option[DimensionName]] = List.empty[Option[DimensionName]])
