package euler

object Problem004 extends App{

  def isPalindrome(s: String): Boolean = (s.length >= 2) && s == s.reverse

  def getMaxPdnum(n: Int): Int = {
    def recur(a: Int, b: Int): Int = b match {
      case 100 => 10001
      case _ => {
        val num = a * b
        if (isPalindrome(num.toString())) num else recur(a, b-1)
      }
    }

    recur(n, n)
  }

  val max = (999 to 100 by -1 map getMaxPdnum).max
  println(max)
}
