import MapDirection._
import TurnDirection._

case class Position(x: Int, y: Int)

object Day01 extends App {
  val instructions = Utils.read(args(0)).head.split(", ")

  var position = Position(0, 0)
  var facing = NORTH
  var visited = Set(position)
  var found = false

  for (inst <- instructions) {
    val turn: String = inst.substring(0, 1)
    val steps = inst.substring(1).toInt

    if (RIGHT equals Orientation.withName(turn))
      facing = facing match {
        case NORTH  => EAST
        case EAST   => SOUTH
        case SOUTH  => WEST
        case WEST   => NORTH
      }
    else facing =
      facing match {
        case NORTH  => WEST
        case WEST   => SOUTH
        case SOUTH  => EAST
        case EAST   => NORTH
      }

    position = facing match {
      case NORTH  =>
        for (y <- position.y+1 to position.y+steps) {
          val path = Position(position.x, y)
          if (!found)
            if (visited.contains(path)) {
              println(s"$path visited more than once!")
              found = true
            } else {
              visited = visited + path
            }
        }
        position.copy(y = position.y + steps)

      case EAST   =>
        for (x <- position.x+1 to position.x+steps) {
          val path = Position(x, position.y)
          if (!found)
            if (visited.contains(path)) {
              println(s"$path visited more than once!")
              found = true
            } else {
              visited = visited + path
            }
        }
        position.copy(x = position.x + steps)

      case SOUTH  =>
        for (y <- position.y-1 to position.y-steps by -1) {
          val path = Position(position.x, y)
          if (!found)
            if (visited.contains(path)) {
              println(s"$path visited more than once!")
              found = true
            } else {
              visited = visited + path
            }
        }
        position.copy(y = position.y - steps)

      case WEST   =>
        for (x <- position.x-1 to position.x-steps by -1) {
          val path = Position(x, position.y)
          if (!found)
            if (visited.contains(path)) {
              println(s"$path visited more than once!")
              found = true
            } else {
              visited = visited + path
            }
        }
        position.copy(x = position.x - steps)
    }
  }

  println(s"Final position is $position.")
}
