import scala.io.Source

object Utils {
  def read(file: String): List[String] = {
    val bufferedSource = Source.fromFile(file)
    val lines = bufferedSource.getLines.toList
    bufferedSource.close()
    lines
  }
}

object MapDirection extends Enumeration {
  type MapDirection = Value
  val NORTH, EAST, SOUTH, WEST = Value
}

object TurnDirection extends Enumeration {
  type TurnDirection = Value
  val LEFT = Value('L')
  val RIGHT = Value('R')
}

object Keys {
  val ONE = 0
  val TWO = 1
  val THREE = 2
  val FOUR = 3
  val FIVE = 4
  val SIX = 5
  val SEVEN = 6
  val EIGHT = 7
  val NINE = 8
  type KeypadDirection = Char
  // 'U', 'D', 'L', 'R'

}

//object KeypadDirection extends Enumeration {
//  type KeypadDirection = Value
//  val UP = Value('U')
//  val DOWN = Value('D')
//  val LEFT = Value('L')
//  val RIGHT = Value('R')
//}
