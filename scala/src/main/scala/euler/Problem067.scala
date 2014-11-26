package euler

import scala.io.Source
import Problem018._

object Problem067 extends App {
  val path = "src/main/scala/euler/Problem067.txt"
  val triangle = Source.fromFile(path).getLines().mkString("\n")
}
