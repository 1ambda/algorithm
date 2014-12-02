package coursera.two_sum

import scala.io.Source

object TwoSum extends App {

  // you can download this file here
  // https://d396qusza40orc.cloudfront.net/algo1%2Fprogramming_prob%2F2sum.txt
  val path = "src/main/scala/coursera/two_sum/2sum.txt"

  def parse(path: String): Array[Long] = {
    (for {
      line <- Source.fromFile(path).getLines()
    } yield line.toLong).toArray.sorted
  }

  // return index where `n` should be inserted
  def binSearch(arr: Array[Long], key: Long): Int = {

    def recur(l: Int, h: Int): Int = {
      val mid = l + (h - l) / 2

      arr(mid) match {
        case elem if elem == key => mid
        case elem if elem < key => // right search 
          if (mid + 1 > h) h else recur(mid + 1, h)
        case _ => // left search
          if (l > mid - 1) l else recur(l, mid - 1)
      }
    }

    recur(0, arr.length - 1)
  }

  // using set(hashing), -100 ~ 100, 42s
  // using binary search to all t, -100 ~ 100, 17s

  // in our case, t is [-10000, 10000] => 2 * 10^5
  // if we use hashing, running time is about O(n) * 2 * 10^5 where n = 1000000

  // instead, just use sorting and checking strategy
  // the idea is that

  // since t is [-10000, 10000], -10000 - x <= t <= 10000 - x
  // if we have an sorted array
  // every x, we can find the valid y set which is relatively small size
  def solution = {
    val xs: Array[Long] = parse(path)
    // val xs = Array(-20000l, 20000l, 40000l)
    val minT = -10000l
    val maxT = 10000l
    var s: Set[Long] = Set()

    // valid y indices. 0 <= index < length
    def yIndices(minY: Long, maxY: Long) = {
      val left = binSearch(xs, minY)
      val right = binSearch(xs, maxY)
      (left to right).filter(n => 0 <= n && n < xs.length)
    }

    // find y, [y | x + y = t]
    for {
      x <- xs
      i <- yIndices(minT - x, maxT - x)
    } {
      val y = xs(i)
      if (minT <= x + y && x + y <= maxT && x != y) s = s + (x + y)
    }

    println(s.size)
  }

  solution
}
