package euler

import scala.math

// https://projecteuler.net/problem=23
object Problem023 extends App {

  def properDivisors(n: Int): Set[Int] = {
    // we don't need to iterate until n/2
    (for {
      i <- 2 to math.sqrt(n).toInt if n % i == 0
    } yield Set(i, n / i)).flatten.toSet ++ Set(1)
  }

  // exist [t |x + y = t, x and y in a set]
  // in this problem, x can be equal to y, x == y
  def twoSum(t: Int, set: scala.collection.mutable.Set[Int]): Boolean = {
    var i = 0
    set.toStream.takeWhile(x => !set.contains(t - x)).foreach(_ => i += 1)
    !(set.size == i)
  }

  def solution = {
    val deficients = scala.collection.mutable.Set[Int]()
    val abundants = scala.collection.mutable.Set[Int]()
    val perfects = scala.collection.mutable.Set[Int]() // for fun

    // all positive integers which can't be written as the sum of two abundant numbers
    val wanted = scala.collection.mutable.Set[Int]()

    val limit = 28123 // by mathematical analysis

    for (n <- 1 to limit) {
      val total = properDivisors(n).sum
      if (total < n) deficients += n
      else if (total > n) abundants += n
      else perfects += n

      if (!twoSum(n, abundants)) wanted += n
    }

    println(wanted.sum)
  }

  solution
}
