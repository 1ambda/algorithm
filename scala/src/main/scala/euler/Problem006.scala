package euler

object Problem006 extends App {

  def sumOfSquares(n: Long): Long = {
    n * (n + 1) * (2 * n + 1) / 6
  }

  def squareOfSum(n: Long): Long = {
    Math.pow(n * (n + 1) / 2, 2).toLong
  }

  println(squareOfSum(100)- sumOfSquares(100))
}
