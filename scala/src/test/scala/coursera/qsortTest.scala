package coursera

import org.scalatest._

class qsortTest extends FlatSpec with Matchers {

  import quicksort._

  "leftQsort(1, 2)" should "pass" in {
    assert(leftQsort(Array(1, 2), 0, 1).deep == Array(1, 2).deep)
  }

  "leftQsort(2, 1)" should "pass" in {
    assert(leftQsort(Array(2, 1), 0, 1).deep == Array(1, 2).deep)
  }

  "leftQsort(1, 2, 3)" should "pass" in {
    assert(leftQsort(Array(1, 2, 3), 0, 2).deep == Array(1, 2, 3).deep)
  }

  "leftQsort(2, 3, 1)" should "pass" in {
    assert(leftQsort(Array(2, 3, 1), 0, 2).deep == Array(1, 2, 3).deep)
  }

  "leftQsort(2, 1, 3)" should "pass" in {
    assert(leftQsort(Array(2, 1, 3), 0, 2).deep == Array(1, 2, 3).deep)
  }

  "leftQsort(3, 2, 1)" should "pass" in {
    assert(leftQsort(Array(3, 2, 1), 0, 2).deep == Array(1, 2, 3).deep)
  }

  "leftQsort(3, 1, 2)" should "pass" in {
    assert(leftQsort(Array(3, 1, 2), 0, 2).deep == Array(1, 2, 3).deep)
  }

  "getMidPivot(8, 4, 1)" should "return 4" in {
    assert(getMidPivot(Array(8, 2, 4, 5, 7, 1), 0, 2, 5) == 2)
  }

  "getMidPivot(4, 1, 3)" should "return 3" in {
    assert(getMidPivot(Array(4, 1, 3), 0, 1, 2) == 2)
  }
}
