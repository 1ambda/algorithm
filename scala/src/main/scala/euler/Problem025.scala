package euler

import scala.math.BigInt

object Problem025 extends App {

  // n: # of digit
  // x: the number to test
  def isNdigit(x: BigInt, n: Int): Boolean = {
    x.toString.length >= n
  }

  val memo = scala.collection.mutable.Map[Int, BigInt]()

  // fib using memoization
  // stream impl will be simple. but It would extreamly slow. 
  def fib(n: Int): BigInt = {
    def recur(k: Int): BigInt = {
      if (k == 1 || k == 2) 1
      else if (memo.isDefinedAt(k)) memo(k)
      else {
        val fValue: BigInt = recur(k - 1) + recur(k - 2)
        memo.put(k, fValue)
        fValue
      }
    }

    recur(n)
  }

  def from(n: Int): Stream[Int] = {
    n #:: from(n + 1)
  }

  def solution = {
    val nat = from(1) // natural number
    var i = 0;

    nat.takeWhile(n => !isNdigit(fib(n), 1000)).foreach(n => i = n)
    println(i + 1)
  }

  solution
}
