package euler

import scala.math
import scala.annotation.tailrec

object Problem016 extends App {
  // calculate positive exp
  def power(n: Int, exp: Int): BigInt = {
    @tailrec
    def recur(exp: Int , acc: BigInt): BigInt = exp match {
      case 0 => acc * 1
      case _ => recur(exp - 1, acc * n)
    }

    recur(exp, 1)
  }

  // can't calculate using double due to loss data
  def solution = power(2, 1000).toString.map(_.asDigit).sum

  println(solution)
}
