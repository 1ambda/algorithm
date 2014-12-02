package euler

import scala.io.Source

object Problem067 extends App {

  case class Node(row: Int, idx: Int)
  case class Path(nodes: List[Node], weights: Int)

  def parse(text: String): Array[Int] = {
    ("\\d\\d".r findAllIn text).map(_.toInt).toArray
  }

  // return the height of a triangle
  def height(tri: Array[Int]): Int = {
    def recur(acc: Int, n: Int): Int = {
      if (acc - n == 0) n else recur(acc - n, n + 1)
    }

    recur(tri.length, 1)
  }

  // return next 2 nod
  def next(n: Node): List[Node] = {
    // next nodes' indices -> idx + row, idx + 1 + row
    List(
      Node(n.row + 1, n.idx + n.row),
      Node(n.row + 1, n.idx + n.row + 1))
  }


  def solution = {
    // you can download this file
    // https://projecteuler.net/project/resources/p067_triangle.txt
    val path = "src/main/scala/euler/Problem067.txt"
    val text = Source.fromFile(path).getLines().mkString("\n")
    val triangle = parse(text)
    val h = height(triangle)
    val minWeights = Array.fill(triangle.length)(0)

    def shortestPaths(paths: Set[Path]): Set[Path] = {
      // assume initial paths is not empty
      if (paths.head.nodes.length == h) paths
      else {
        val nextPaths = for {
          p <- paths
          n <- next(p.nodes.head)
          if triangle(n.idx) + p.weights > minWeights(n.idx)
        } yield {
          val w = triangle(n.idx) + p.weights; 
          minWeights(n.idx) = w; // update min weights
          Path(n +: p.nodes, w)
        } 

        shortestPaths(nextPaths)
      }
    }

    minWeights(0) = triangle(0)
    val initPaths = Set(Path(List(Node(1, 0)), triangle(0)))
    val res = shortestPaths(initPaths)
    println(res.maxBy(_.weights))
  }

  solution
}
