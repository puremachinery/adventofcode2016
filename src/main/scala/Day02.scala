import Keys._

import scala.collection._

case class Key(name: String, adjacentKeys: mutable.Map[KeypadDirection, Key]) {
  override def toString: String = this.name
}
case class Keypad(keys: List[Key])

object Day02 extends App {
  val instructions: List[String] = Utils.read(args(0))

  for (part <- 1 to 2) {
    val keypad: Keypad = initializeKeypad(part)
    var key: Key = keypad.keys(FIVE)

    instructions.foreach { line =>
      for (direction <- line)
        key = move(key, direction)

      print(key)
    }
    println()
  }

  def move(key: Key, dir: KeypadDirection): Key = key.adjacentKeys.getOrElse(dir, key)

  def initializeKeypad(part: Int): Keypad = part match {
    case 1 =>
      val one   = Key("1", mutable.Map[KeypadDirection, Key]())
      val two   = Key("2", mutable.Map[KeypadDirection, Key]())
      val three = Key("3", mutable.Map[KeypadDirection, Key]())
      val four  = Key("4", mutable.Map[KeypadDirection, Key]())
      val five  = Key("5", mutable.Map[KeypadDirection, Key]())
      val six   = Key("6", mutable.Map[KeypadDirection, Key]())
      val seven = Key("7", mutable.Map[KeypadDirection, Key]())
      val eight = Key("8", mutable.Map[KeypadDirection, Key]())
      val nine  = Key("9", mutable.Map[KeypadDirection, Key]())
      one.adjacentKeys    += ('D' -> four, 'R' -> two)
      two.adjacentKeys    += ('D' -> five, 'L' -> one, 'R' -> three)
      three.adjacentKeys  += ('L' -> two, 'D' -> six)
      four.adjacentKeys   += ('U' -> one, 'R' -> five, 'D' -> seven)
      five.adjacentKeys   += ('U' -> two, 'L' -> four, 'R' -> six, 'D' -> eight)
      six.adjacentKeys    += ('U' -> three, 'L' -> five, 'D' -> nine)
      seven.adjacentKeys  += ('U' -> four, 'R' -> eight)
      eight.adjacentKeys  += ('U' -> five, 'L' -> seven, 'R' -> nine)
      nine.adjacentKeys   += ('U' -> six, 'L' -> eight)
      Keypad(List(one, two, three, four, five, six, seven, eight, nine))

    case 2 =>
      val one   = Key("1", mutable.Map[KeypadDirection, Key]())
      val two   = Key("2", mutable.Map[KeypadDirection, Key]())
      val three = Key("3", mutable.Map[KeypadDirection, Key]())
      val four  = Key("4", mutable.Map[KeypadDirection, Key]())
      val five  = Key("5", mutable.Map[KeypadDirection, Key]())
      val six   = Key("6", mutable.Map[KeypadDirection, Key]())
      val seven = Key("7", mutable.Map[KeypadDirection, Key]())
      val eight = Key("8", mutable.Map[KeypadDirection, Key]())
      val nine  = Key("9", mutable.Map[KeypadDirection, Key]())
      val a     = Key("A", mutable.Map[KeypadDirection, Key]())
      val b     = Key("B", mutable.Map[KeypadDirection, Key]())
      val c     = Key("C", mutable.Map[KeypadDirection, Key]())
      val d     = Key("D", mutable.Map[KeypadDirection, Key]())
      one.adjacentKeys    += ('D' -> three)
      two.adjacentKeys    += ('R' -> three, 'D' -> six)
      three.adjacentKeys  += ('L' -> two, 'U' -> one, 'R' -> four, 'D' -> seven)
      four.adjacentKeys   += ('L' -> three, 'D' -> eight)
      five.adjacentKeys   += ('R' -> six)
      six.adjacentKeys    += ('L' -> five, 'U' -> two, 'R' -> seven, 'D' -> a)
      seven.adjacentKeys  += ('L' -> six, 'U' -> three, 'R' -> eight, 'D' -> b)
      eight.adjacentKeys  += ('L' -> seven, 'U' -> four, 'R' -> nine, 'D' -> c)
      nine.adjacentKeys   += ('L' -> eight)
      a.adjacentKeys      += ('U' -> six, 'R' -> b)
      b.adjacentKeys      += ('L' -> a, 'U' -> seven, 'R' -> c, 'D' -> d)
      c.adjacentKeys      += ('U' -> eight, 'L' -> b)
      d.adjacentKeys      += ('U' -> b)
      Keypad(List(one, two, three, four, five, six, seven, eight, nine, a, b, c, d))
  }
}
