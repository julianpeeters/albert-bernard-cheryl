package puzzle
package board
package colors

import util.Random

trait Channel
case class RedValue(var value: Int) extends Channel
case class GreenValue(var value: Int) extends Channel
case class BlueValue(var value: Int) extends Channel

case class Color(var r: RedValue, var g: GreenValue, var b: BlueValue){
  override def toString = s"rgb(${r.value}, ${g.value}, ${b.value})"
  def toRgba(a: Double) = s"rgba(${r.value}, ${g.value}, ${b.value}, $a)"
}

object Color {
  val baseColor = Color(RedValue(255), GreenValue(255), BlueValue(255))
  var currentColor = Color(RedValue(255), GreenValue(255), BlueValue(255))
  // hits result in the block blended with the target color
  var targetColor = Color(RedValue(254), GreenValue(254), BlueValue(254))

  // Every block made calls getColor, so every block made or hit is 1 shade
  // closer to the target color. When we reach the target color, get a new
  // random target
  def getColor = {
    if (targetColor==currentColor) {
      targetColor = random
      currentColor = Color.blendColors( currentColor, targetColor )
    }
    currentColor = Color.blendColors( currentColor, targetColor )
    currentColor
  }

  def blendColors(col1: Color, col2: Color): Color = {
    def blendChannel(currentValue: Int, targetValue: Int) = {
      currentValue match {
        case x if currentValue < targetValue => currentValue + 1
        case y if currentValue > targetValue => currentValue - 1
        case z if currentValue == targetValue => currentValue
      }
    }
    val newColor = col1.copy(
      r = col1.r.copy(value = blendChannel( col1.r.value, col2.r.value )),
      g = col1.g.copy(value = blendChannel( col1.g.value, col2.g.value )),
      b = col1.b.copy(value = blendChannel( col1.b.value, col2.b.value )))
    newColor
  }

  def random = {
    val r = RedValue(Random.nextInt(255))
    val g =  GreenValue(Random.nextInt(255))
    val b = BlueValue(Random.nextInt(255))
    Color(r, g, b)
  }

}
