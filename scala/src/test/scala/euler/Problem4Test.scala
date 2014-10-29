package euler

import org.scalatest._

class Problem4Test extends FlatSpec with Matchers {

  import Problem4._

  "isPalindromeTest" should "pass" in {
    assert(isPalindrome("") == false)
    assert(isPalindrome("a") == false)
    assert(isPalindrome("asdds") == false)
    assert(isPalindrome("asddsa") == true)
    assert(isPalindrome("asdsa") == true)
  }
}
