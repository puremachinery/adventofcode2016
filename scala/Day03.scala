import scala.collection.mutable.ListBuffer

case class Triangle(a: Int, b: Int, c: Int)

object Day03 extends App {
  val tryangles = Utils.read(args(0)).map { line =>
    line.split(" ")
      .filter(_.trim.length != 0)
      .map(_.toInt)
      .sorted
  }

  // Part 1: How many tryangles are possible triangles because any two sides are greater than the third?
  println(tryangles.count { triple => triple(0) + triple(1) > triple(2) })

  // Part 2: Reading the input by column-triples.
  val transpangles: List[List[Int]] = Utils.read(args(0)).map { line =>
    line.split(" ").toList
      .filter(_.trim.length != 0)
      .map(_.toInt)
  }

  val fixedInput = ListBuffer[List[Int]]()
  for (i <- transpangles.indices by 3) {
    val tripleTransposed: List[List[Int]] = List(transpangles(i), transpangles(i+1), transpangles(i+2)).transpose
    fixedInput appendAll List(tripleTransposed(0), tripleTransposed(1), tripleTransposed(2))
  }

  println(fixedInput.map { _.sorted }.count { triple => triple(0) + triple(1) > triple(2) })
}
