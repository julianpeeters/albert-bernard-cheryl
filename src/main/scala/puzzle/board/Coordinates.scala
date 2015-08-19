package puzzle
package board

import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, html}

trait Coordinates

case class MouseCoords(x: PixelUnits, y: PixelUnits) extends Coordinates {
  def toCanvasCoords(canvas: html.Canvas): CanvasCoords = {
    val offsetX = x.value - canvas.offsetLeft
    val offsetY = y.value - canvas.offsetTop
    CanvasCoords(PixelUnits(offsetX), PixelUnits(offsetY))
  }
}

case class CanvasCoords(x: PixelUnits, y: PixelUnits) extends Coordinates {
  def toGridCoords(width: Int, height: Int, spacing: Int): GridCoords = {
    val totalWidth = width + spacing
    val totalHeight = height + spacing
    def xFloor(x: Double) = Math.floor( x / totalWidth ).toInt
    def yFloor(y: Double) = Math.floor( y / totalHeight ).toInt
    val convertedX = xFloor(x.value)
    val convertedY = yFloor(y.value)
    GridCoords(BlockUnits(convertedX), BlockUnits(convertedY))
  }
}

case class GridCoords(x: BlockUnits, y: BlockUnits) extends Coordinates {
  def toCanvasCoords(width: Int, height: Int, spacing: Int): CanvasCoords = {
    val totalWidth = width + spacing
    val totalHeight = height + spacing
    val convertedX = x.value * totalWidth
    val convertedY = y.value * totalHeight
    CanvasCoords(PixelUnits(convertedX), PixelUnits(convertedY))
  }
}
