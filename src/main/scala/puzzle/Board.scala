package puzzle

import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import util.Random

import scala.collection.mutable.ArrayBuffer

class Board(implicit canvas: html.Canvas) {
  implicit val renderer = canvas.getContext("2d")
    .asInstanceOf[dom.CanvasRenderingContext2D]
  canvas.width = canvas.parentElement.clientWidth/2
  canvas.height = canvas.parentElement.clientHeight
  renderer.fillRect(0, 0, canvas.width, canvas.height)

  trait Drawable {
    def draw(implicit renderer: dom.CanvasRenderingContext2D)
  }

  case class Block(x: Int, y: Int, grid: Grid) extends Drawable {
   // val neighbors = ArrayBuffer()
    var blockColor = "blue"//"rgba(134,234,90)"//getColor()
    var nextColor = blockColor
    var ink = 0
    var lastSizeDiff = 0;
    def hit = {
      val r = Random.nextInt(255)
      val g = Random.nextInt(255)
      val b = Random.nextInt(255)
      blockColor = s"rgb($r,$g,$b)"
    }
    override def draw(implicit renderer: CanvasRenderingContext2D): Unit = {
      renderer.fillStyle = blockColor
      renderer.fillRect(x, y, grid.blockWidth, grid.blockHeight)
    }
  }

  case class Grid(){
    val numRows = 4
    val numColumns = 6
    val blockSpacing = 2

    val bound = canvas.getBoundingClientRect()
    val blockWidth = (bound.width/numColumns).toInt
    val blockHeight = (bound.height/numRows).toInt

    val blocks = for {
      column <- (0 to numColumns-1)
      row <- (0 to numRows-1)
      val x = column * (blockWidth + blockSpacing)
      val y = row * (blockHeight + blockSpacing)
    } yield Block(x, y, this)

    def selectBlock(x: Double, y: Double): Block = {
      val totalBlockWidth = blockWidth + blockSpacing
      val totalBlockHeight = blockHeight + blockSpacing
      def xFloor(x: Double) = Math.floor( x / totalBlockWidth )
      def yFloor(y: Double) = Math.floor( y / totalBlockHeight )
      val xCoord = xFloor(x).toInt
      val yCoord = yFloor(y).toInt
      (blocks.grouped(numRows).toList)(xCoord)(yCoord)
    }
  }

  var grid = new Grid
  var time = 0

  def draw() = {
    clear()
    grid.blocks.foreach(_.draw)
  }

  def update() = {
    time += 1
  }

  def clear() = {
    renderer.fillStyle = "black"
    renderer.fillRect(0, 0, canvas.width, canvas.height)
  }

}
