package euler

object Problem020 extends App {

  // factorial using memoization

  def factorial(n: Int): BigInt = {
    val memo: Array[BigInt] = Array.fill[BigInt](n + 1)(0)

    def recur(n: Int): BigInt = {
      if (n == 1 || n == 0) 1
      else if (memo(n) != 0) memo(n)
      else {
        memo(n) = n * recur(n - 1)
        memo(n)
      }
    }

    recur(n)
  }

  def solution = {
    println(factorial(100).toString.map(_.asDigit).sum)
  }

  solution
}
