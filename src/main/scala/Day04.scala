import scala.math.Ordering

object Day04 extends App {
  val rooms = Utils.read(args(0)).flatMap(Room.fromString)

  println(s"The sum of the sector IDs of the real rooms is ${rooms.map { _.id }.sum}.")

  val npsRoom = rooms.map { r => (r.id, r.name.map { shift(_, r.id) }.trim) }
    .filter { t => t._2.contains("north") && t._2.contains("pole") && t._2.contains("storage") }
    .head

  println(s"The ${npsRoom._2} room has id ${npsRoom._1}.")

  def shift(c: Char, n: Int): Char = c match {
    case ch if ch.isLetter && ch <= 'z' =>
      ((c - 'a' + n) % 26 + 'a').toChar

    case '-' => ' '
  }
}

case class Room(name: String, id: Int, checksum: String)

case class CharCount(char: Char, count: Int)

object CharCountOrdering extends Ordering[CharCount] {
  def compare(a: CharCount, b: CharCount): Int =
    if (b.count.compare(a.count) == 0)
      a.char compare b.char
    else
      b.count compare a.count
}

object Room {
  def fromString(possibleRoomSpec: String): Option[Room] = {
    val pattern = """((?:[a-z]+-)+)(\d+)\[([a-z]{5})\]""".r

    possibleRoomSpec match {
      case pattern(encryptedName, sectorID, checksum) =>
        val commonChars: String = encryptedName
          .filterNot(_ == '-')
          .groupBy(identity)
          .mapValues(_.length)
          .map { charCountMapEntry => CharCount(charCountMapEntry._1, charCountMapEntry._2) }
          .toSeq
          .sorted(CharCountOrdering)
          .map(_.char)
          .take(5)
          .mkString

        if (commonChars equals checksum) Some(Room(encryptedName, sectorID.toInt, checksum)) else None

      case _ => throw new IllegalArgumentException(s"$possibleRoomSpec doesn't match regex $pattern")
    }
  }
}
