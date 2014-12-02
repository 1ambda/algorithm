package coursera.median_maintenance

import scala.io.Source
import scala.collection.mutable.PriorityQueue
import math.abs

object MedianMaintenance extends App {

  type Queue = PriorityQueue[Int]

  def parse(path:String): Vector[Int] = {
    (for (line <- Source.fromFile(path).getLines())
    yield line.toInt).toVector
  }

  // ref:
  // http://stackoverflow.com/questions/10657503/find-running-median-from-a-stream-of-integers

  // stream of medians
  def allMedians(xs: Vector[Int]): Vector[Int]= {
    val maxQ = PriorityQueue[Int]() // left
    val minQ = PriorityQueue[Int]()(Ordering.Int.reverse) // right
    var medians: Vector[Int] = Vector()

    for (x <- xs) {
      // insert
      if (!maxQ.isEmpty && x <= maxQ.head) {
        maxQ.enqueue(x)
      } else if (!minQ.isEmpty && x > minQ.head){
        minQ.enqueue(x)
      } else {
        maxQ.enqueue(x)
      }

      // balance
      (maxQ.size - minQ.size) match {
        case diff if abs(diff) > 1 => // unbalanced
          if (maxQ.size > minQ.size) minQ.enqueue(maxQ.dequeue)
          else maxQ.enqueue(minQ.dequeue)
        case _ => 
      }

      // find median
      if (maxQ.size == minQ.size) medians = medians :+ maxQ.head
      else medians = medians :+ List(maxQ, minQ).maxBy(_.size).head
    }

    medians
  }

  def solution = {
    // from : http://spark-public.s3.amazonaws.com/algo1/programming_prob/Median.txt
    val path = "src/main/scala/coursera/median_maintenance/Median.txt"
    val xs = parse(path)
    // val xs = Vector(5, 15, 1, 3) => ms should be [5, 5, 5, 3]
    val ms = allMedians(xs)

    println(ms.sum % 10000)
  }

  solution
}
