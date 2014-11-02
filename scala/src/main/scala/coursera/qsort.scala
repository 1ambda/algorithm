package coursera

import scala.io.Source
import math._


object quicksort extends App {

  val path = "src/main/scala/coursera/QuickSort.txt"
  val f = Source.fromFile(path)
  val nums = f.getLines map { _.toInt } toArray // size: 10000
  var comparisons = 0

  // set the mid value into the left most index
  def getMidPivot(arr: Array[Int], l: Int, m: Int, r: Int): Int = {

    val map = Map(arr(l) -> l, arr(m) -> m, arr(r) -> r)
    val midValue = max(min(arr(l), arr(m)), min(max(arr(l), arr(m)), arr(r)))
    map(midValue)
  }

  // array, start index, last index
  def leftQsort(arr: Array[Int], start: Int, end: Int): Array[Int] = {

    if (start >= end) arr
    else {
      comparisons += end - start + 1

      // use the right most element as pivot, swap start with end
      // 170755 comparisons
      // val temp1 = arr(end)
      // arr(end) = arr(start)
      // arr(start) = temp1

      // use the left most element as pivot
      // 168738 comparisons
      var p = start
      var i = start
      var j = start + 1

      // use the median of three pivot rule
      // 157381
      val mid = getMidPivot(arr, start, (end - start + 1)/2, end)
      val temp2 = arr(mid)
      arr(mid) = arr(p)
      arr(p) = temp2

      while(j <= end) {
        if (arr(j) < arr(p)) {
          i += 1
          val temp = arr(i)
          arr(i) = arr(j)
          arr(j) = temp
        }

        j += 1 
      }

      // swap arr(p) with arr(i)
      val temp = arr(i)
      arr(i) = arr(p)
      arr(p) = temp
      p = i

      leftQsort(arr, start, p - 1)
      leftQsort(arr, p + 1, end)
      arr
    }
  }

  // val sorted = leftQsort(nums, 0, nums.length - 1).toList
  val sorted = leftQsort(nums, 0, nums.length - 1).toList

  var j = 0
  val res = for(i <- 0 to sorted.length - 2) yield {
    j = i + 1
    (sorted(i) < sorted(j))
  }

  println("sorted: " + (res forall { x => x == true }))
  println("# of comparison:" + comparisons)
}
