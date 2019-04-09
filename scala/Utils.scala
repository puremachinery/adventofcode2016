import scala.io.Source

object Utils {
  def read(file: String): List[String] = {
    val bufferedSource = Source.fromFile(file)
    val lines = bufferedSource.getLines.toList
    bufferedSource.close()
    lines
  }
}

object Direction extends Enumeration {
  type Direction = Value
  val NORTH, EAST, SOUTH, WEST = Value
}

object Orientation extends Enumeration {
  type Orientation = Value
  val RIGHT = Value("R")
  val LEFT = Value("L")
}
