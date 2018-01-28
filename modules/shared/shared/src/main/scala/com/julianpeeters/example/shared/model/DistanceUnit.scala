package com.julianpeeters.example.shared.model

sealed trait DistanceUnit
final case object meter extends DistanceUnit
final case object kilometer extends DistanceUnit
