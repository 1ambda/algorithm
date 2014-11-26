package euler

import scala.math

object Problem012 extends App {
  def divisors(n: Int): List[Int] = {
    (1 to n/2).filter(n % _ == 0).toList :+ n
  }

  // ref : http://stackoverflow.com/questions/110344/algorithm-to-calculate-the-number-of-divisors-of-a-given-number

  /*
   * each divisor has a partner
   * n = 24, [1 * 24, 2 * 12, 3 * 8, 4 * 6]
   * n = 25, [1 * 25, 5 * 5]
   */
  def countDivisors(n: Int): Int = {
    var limit = n
    var count = 0
    var i = 1

    while(i < limit) {
      if (n % i == 0) {
        limit = n / i
        if (limit != i) count += 1

        count += 1
      }
      i += 1
    }

    count
  }

  def trinum: Stream[Int]= {
    val acc = 0

    def recur(acc: Int, n: Int): Stream[Int] = {
      val num = acc + n
      num #:: recur(num, n + 1)
    }

    recur(acc, 1)
  }

  // too slow
  // println(trinum.filter(divisors(_).length >= 500).head)

  println(trinum.filter(countDivisors(_) >= 500).head)
}
