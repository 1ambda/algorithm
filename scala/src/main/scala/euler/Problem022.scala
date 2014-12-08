package euelr

import scala.io.Source

object Problem022 extends App {

  // https://projecteuler.net/project/resources/p022_names.txt
  val path = "src/main/scala/euler/Problem022.txt"

  val line = Source.fromFile(path).getLines().toList.head
  val pattern = "[A-Z]+".r
  // contains 5163 names
  val names = (pattern findAllIn line).toList.sorted

  // Char to Int map
  val char2int = (for (i <- 1 to 26) yield (i + 64).toChar -> i).toMap

  def name2int(name: String): Int = {
    (name map char2int).sum
  }

  var i = 0;

  def solution = {
    val answer = names.foldLeft(0) { case(total, name) =>
      i += 1
      total + name2int(name) * i;
    }

    println(answer)
  }

  solution
}
