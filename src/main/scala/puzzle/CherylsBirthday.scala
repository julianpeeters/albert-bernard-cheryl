package puzzle

import org.scalajs.dom
import org.scalajs.dom.html
import scala.scalajs.js.annotation.JSExport

@JSExport
object CherylsBirthday {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    
    implicit val canv = canvas

    val board = new Board

    canvas.onmousemove = {
      (e: dom.MouseEvent) =>
        board.grid.selectBlock(e.pageX, e.pageY).hit
    }

    canvas.onmousedown = {
      (e:dom.MouseEvent) => {
        board.grid.selectBlock(e.pageX, e.pageY).hit
      }
    }

    def run = {
      board.draw()
      board.update()
    }

    dom.setInterval(() => run, 50)
  }
}
