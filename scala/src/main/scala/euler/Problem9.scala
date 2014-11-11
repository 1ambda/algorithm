package euler

import scala.math._

object Problem9 extends App {

  val candidates = for {
    a <- 3 to 333                         // by the condition a < b < c
    b <- a + 1 to 498                     // a + b > c, by the definition of triangle 
    c <- (1000 - a - b) to (1000 - a - b) // a + b + c = 1000
    if a + b + c == 1000 && b < c
  } yield List(a, b, c)

  val answer = candidates filter { case List(a, b, c) =>
    math.pow(a, 2) + math.pow(b, 2) == math.pow(c, 2)
  }

  println(answer(0).product)
}
