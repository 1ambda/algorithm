package coursera

import scala.io.Source
import scala.util.Random

object RandomContraction extends App {

  def readFile(path: String): Map[Int, List[Int]] = {
    val f = Source.fromFile(path)
    (f.getLines().flatMap { line =>
      val xs = line.split("\t").map(_.toInt).toList
      Map(xs.head -> xs.tail)
    }).toMap
  }

  def randomEdge(vertices: Map[Int, List[Int]]): (Int, Int) = {
    val u = vertices.keys.toList(r.nextInt(vertices.size))
    var v = u

    while (v == u)
      v = vertices(u)(r.nextInt(vertices(u).length))

    (u, v)
  }

  // find minimum cut
  // vts : the graph, abbrv for vertices
  def randomContract(vts: Map[Int, List[Int]]): Map[Int, List[Int]] = vts.size match {
    case 2 => vts 
    case _ => {

      // 1. pick a remaining edge uniformly at random
      val (u, v) = randomEdge(vts)

      // 2. contract
      val uv = vts(u) ++ vts(v)
      val vts1 = vts + (u -> uv)

      val vRemovedMaps = vts1(v).map(x => x -> {
        // we should add `u` as many as 'v' 
        val vNum = vts1(x).filter(_ == v).length
        vts1(x).filter(_ != v) ++ (for(i <- 0 until vNum) yield u)
      })
      val vts2 = vts1 ++ vRemovedMaps - v

      // 3. remove self-loops
      val loopRemovedVertex_u = vts2(u).filter(_ != u)
      val vts3 = vts2 + (u -> loopRemovedVertex_u)

      // 3. recursive call
      randomContract(vts3)
    }
  }


  // you can download this file Here
  // http://spark-public.s3.amazonaws.com/algo1/programming_prob/kargerMinCut.txt
  val path = "src/main/scala/coursera/kargerMinCut.txt"
  val vertices: Map[Int, List[Int]] = readFile(path)
  val r = new Random() // stochastic simulation due to multiple trials 

  def simulate(trials: Int) = {
    (for(t <- 0 until trials) yield (randomContract(vertices).toList)(0)._2.length).min
  }

  println(simulate(100))
}
