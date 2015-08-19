package puzzle
package board

import colors._

import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, html}

// Based on this MIT licensed LiveWeave: http://liveweave.com/rq4E0M
class Board(implicit canvas: html.Canvas) {
  implicit val renderer = canvas.getContext("2d")
    .asInstanceOf[dom.CanvasRenderingContext2D]
  canvas.width = canvas.parentElement.clientWidth/2
  canvas.height = canvas.parentElement.clientHeight
  renderer.fillRect(0, 0, canvas.width, canvas.height)

  // Grid of blocks
  case class Grid(){
    val bound = canvas.getBoundingClientRect()
    val numRows = 4
    val numColumns = 6
    val blockSpacing = 1
    val blockWidth = (bound.width/numColumns).toInt
    val blockHeight = (bound.height/numRows).toInt
    val maxInk = 200

    val blocks = for {
      column <- (0 to numColumns-1)
      row <- (0 to numRows-1)
      val gridCoords = GridCoords(BlockUnits(column), BlockUnits(row))
    } yield Block(gridCoords, this)

    def selectBlock(coord: GridCoords): Block = {
      (blocks.grouped(numRows).toList)(coord.x.value)(coord.y.value)
    }
  }

  trait Drawable {
    def draw(implicit renderer: dom.CanvasRenderingContext2D)
  }

  case class Block(gridCoords: GridCoords, grid: Grid) extends Drawable {
    val width = grid.blockWidth
    val height = grid.blockHeight
    val spacing = grid.blockSpacing
    val canvasCoord = gridCoords.toCanvasCoords(width, height, spacing)
    var blockColor = Color.getColor
    var ink = 0
    var selected = false
    val selectedIsThisColor = Color(RedValue(255), GreenValue(2), BlueValue(2))

    lazy val neighbors: List[Block] = {
      Neighbors.getNeighborsCoords(gridCoords, grid.numColumns, grid.numRows)
       .map(neighbor => grid.selectBlock(neighbor.gridCoords)).distinct
    }

    def hit = {
      blockColor = Color.getColor
      ink = grid.maxInk
    }

    def toggle = {
      if (selected) selected = false
      else selected = true
      blockColor = Color.getColor
      ink = grid.maxInk
    }

    def bleed = {
      // neighbor with the most ink
      val bestNeighbor: Block = neighbors.maxBy(block => block.ink)
      bestNeighbor.ink match {
        // blend with best neighbor if it has more ink than me
        case i if (i > ink && i > 1) => {
          // set bleed color
          blockColor = Color.blendColors(blockColor, bestNeighbor.blockColor)
          // bleed size
          val bleedSize = 0.7
          // bleed
          ink = Math.round(i.toDouble * bleedSize).toInt
          // bleed sustain
          bestNeighbor.ink = bestNeighbor.ink - Math.round(ink / 50)
          // guard against negative ink
          if (bestNeighbor.ink < 0) bestNeighbor.ink = 0;
        }
        case i if (ink > 0) => ink = ink - 1
        // when best neighbor's ink runs out, fade to this block to base color
        case i if (ink == 0) => {
          blockColor = Color.blendColors(blockColor, Color.baseColor)
        }
      }
    }
    override def draw(implicit renderer: CanvasRenderingContext2D): Unit = {
      renderer.fillStyle = {
        if (selected) selectedIsThisColor.toString
        else blockColor.toString
      }
      renderer.fillRect(
        canvasCoord.x.value,
        canvasCoord.y.value,
        grid.blockWidth,
        grid.blockHeight)
    }
  }

  val grid = new Grid
  var time = 0

  def draw() = {
    clear()
    grid.blocks.foreach(_.bleed)
    grid.blocks.foreach(_.draw)
  }

  def update() = {
    time += 1
  }

  def clear() = {
    renderer.fillStyle = "white"
    renderer.fillRect(0, 0, canvas.width, canvas.height)
  }

}
