package puzzle
package board


trait Units
case class PixelUnits(value: Double) extends Units
case class BlockUnits(value: Int) extends Units
