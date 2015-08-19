package puzzle
import board._

import org.scalajs.dom
import org.scalajs.dom.html
import scala.scalajs.js.annotation.JSExport

@JSExport
object CherylsBirthday {
  @JSExport
  def main(canvas: html.Canvas): Unit = {

    implicit val canv = canvas

    val board = new Board

    def selectBlock(e: dom.MouseEvent) = {
      val width = board.grid.blockWidth
      val height = board.grid.blockHeight
      val spacing = board.grid.blockSpacing
      val x = PixelUnits(e.pageX)
      val y = PixelUnits(e.pageY)
      val mouseCoords = MouseCoords(x, y)
      val canvasCoords = mouseCoords.toCanvasCoords(canvas)
      val gridCoords = canvasCoords.toGridCoords(width, height, spacing)
      val block = board.grid.selectBlock(gridCoords)
      block
    }

    canvas.onmousemove = (e: dom.MouseEvent) => selectBlock(e).hit
    canvas.onmousedown = (e:dom.MouseEvent) => selectBlock(e).toggle

    def run = {
      board.draw()
      board.update()
    }

    dom.setInterval(() => run, 30)
  }
}
