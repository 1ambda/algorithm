package euler

import scala.annotation.tailrec

object Problem007 extends App {

  def isPrime(n: Int): Boolean = {

    val limit = n / 2

    @tailrec
    def recur(acc: Int): Boolean = {
      if (acc > limit) true
      else if (n % acc == 0) false
      else recur(acc + 1)
    }

    recur(2)
  }

  def nthPrime(n: Int): Int = {

    @tailrec
    def npRecur(num: Int, count: Int): Int = isPrime(num) match {
      case true => if (count == n) num else npRecur(num + 1, count + 1)
      case false => npRecur(num + 1, count)
    }

    npRecur(2, 1)
  }

  println(nthPrime(10001))
}

