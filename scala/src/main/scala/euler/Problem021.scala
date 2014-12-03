package euler

import scala.math.sqrt

object Problem021 extends App {

  type Divisors = List[Int]

  // return proper divisors
  def properDivisors(n: Int): Divisors = {
    1 +: (for {
      i <- 2 to sqrt(n).toInt
      v <- List(i, n / i)
      if n % i == 0
    } yield v).toList
  }

  def solution = {
    val m = (for {
      n <- 2 to 10000
    } yield (n -> properDivisors(n).sum)).toMap

    def d(n: Int) = m.getOrElse(n, 0)

    var s: Set[Int] = Set()

    // naive, in fact we don't need to iterate all keys.
    m foreach { case(k, v) => if (k == d(v) && k != v) s = s + k + v }

    println(s.sum)
  }

  solution
}
