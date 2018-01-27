package com.julianpeeters.albertbernardcheryl
package models

sealed trait AppPage
case object HomePage extends AppPage
case object ItemPage extends AppPage

