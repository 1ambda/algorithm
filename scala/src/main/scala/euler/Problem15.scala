package euler

import scala.annotation.tailrec

object Problem15 extends App {

  @tailrec
  def recur(n: BigInt, until: BigInt, acc: BigInt): BigInt = n match {
    case k if k == until => until * acc
    case _ => recur(n - 1, until, acc * n)
  }

  def factorial(n: BigInt): BigInt = {
    recur(n, 1, 1)
  }

  def factorialUntil(n: BigInt, until: BigInt): BigInt = {
    recur(n, until, 1)
  }

  // (a, b) = (row, col)
  def solution(a: BigInt, b: BigInt) = {
    val max = List(a, b).max
    val min = List(a, b).min
    val sum = a + b

    // doesn't work due to overflow even Long type 
    // (max + 1 to sum).product / (1 to min).product

    factorialUntil(sum, max + 1) / factorial(min)
  }

  println(solution(20, 20))
}
