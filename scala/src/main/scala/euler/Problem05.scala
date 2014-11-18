package euler

object Problem05 extends App {

  /*
   * based on http://www.scala-lang.org/old/node/47.html
   */

  // function divisors can be improved
  // since we don't need to test until n
  def divisors(n: Int): List[Int] =
    (for(i <- 1 to n if n % i == 0) yield i).toList

  def isPrime(n: Int): Boolean = divisors(n).length == 2

  def getPrimes(n: Int): List[Int] = (2 to n).toList filter isPrime

  // modulo operator differ from divide
  def divisableNum(n: Long): Long = {
    val primes = (1 to 20).toList

    def recur(acc: Long): Long = {
      if (primes forall { acc % _ == 0 }) acc else recur(acc + 1)
    }

    recur(n)
  }

  println(divisableNum(20))
}


