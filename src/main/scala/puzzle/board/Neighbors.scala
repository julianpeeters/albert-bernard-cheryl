package puzzle
package board

object Neighbors {

  case class NeighborPos(gridCoords: GridCoords, edgeTest: EdgeTest)
  case class EdgeTest(test: Boolean)

  def getNeighborsCoords(gridCoords: GridCoords, numColumns: Int, numRows: Int) = {
    val x = gridCoords.x
    val y = gridCoords.y
    val xInc = BlockUnits(x.value + 1)
    val xDec = BlockUnits(x.value - 1)
    val yInc = BlockUnits(y.value + 1)
    val yDec = BlockUnits(y.value - 1)
    val right = NeighborPos(GridCoords(xInc, y), EdgeTest(x.value < numColumns-1))
    val left = NeighborPos(GridCoords(xDec, y), EdgeTest(x.value > 0))
    val top = NeighborPos(GridCoords(x, yInc), EdgeTest(y.value < numRows-1))
    val bottom = NeighborPos(GridCoords(x, yDec), EdgeTest(y.value > 0))
    val possibleNeighbors = List(right, left, top, bottom)
    possibleNeighbors.filter(neighbor => neighbor.edgeTest.test)
  }
  
}
