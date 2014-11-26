package euler

import scala.math._

object Problem010 extends App {

  // al even numbers are not the prime number except 2
  def isPrime(n: Int): Boolean = {
    if (n % 2 == 0) false
    // assume n = a * b (a and b > 1),
    // then one of a, b should less equal than sqrt(n)
    else (3 to math.sqrt(n).toInt by 2) forall { n % _ != 0}
  }

  // also, you can use the Long type
  def primes(n: Int): List[BigInt] = {
    (for {
      i <- 3 to n 
      if isPrime(i)
    } yield i).toList map { BigInt(_) }
  }

  // we ommited 2 calculating primes
  println(primes(2000000).sum + 2)
}
