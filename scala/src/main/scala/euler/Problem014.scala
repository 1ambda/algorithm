package euler

import scala.math._
import scala.annotation.tailrec

object Problem014 extends App {

  // interesting collatz graph.
  // http://www.jasondavies.com/collatz-graph/


  def even(n: Long) = n % 2 == 0
  def odd(n: Long) = !even(n)

  // return collatz number count
  // can't use Integer type because of overflow
  def countCollatzNumber(n: Long): Long = {
    var map = Map()

    @tailrec
    def recur(n: Long, count: Long): Long = n match {
      case 1 => count + 1
      case _ =>
        if (even(n)) recur(n / 2, count + 1)
        else recur((3 * n) + 1, count + 1)
    }

    recur(n, 0)
  }

  def solution = {
    val pairs =
      (for (i <- 1 to 1000000) yield (i, countCollatzNumber(i))).toList
    pairs.maxBy(_._2)
  }

  // (837799,525)
  // [success] Total time: 1 s, completed Nov 18, 2014 11:39:46 PM
  println(solution)
}

