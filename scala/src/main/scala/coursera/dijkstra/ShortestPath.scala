package coursera.dijkstra

import scala.io.Source

object ShortestPath extends App {

  type Vertex = Int
  type Weight = Long
  type Edge = (Vertex, Weight)
  type Graph = Vector[Vector[Edge]]

  // " 185, 5950    ", ... -> [185, 5950, ...]
  def parseLine(line: String): List[Long] = {
    (line.split("\\s+|,").toList.tail.filter(_ != "").map(_.toLong)).toList
  }

  // [185, 5950, ...] -> [(185, 5950), ...]
  def makeEdges(xs: List[Long]): Vector[Edge] = {
    (for (List(to, weight) <- xs.grouped(2)) yield (to.toInt, weight)).toVector
  }

  def makeGraph(path: String): Graph = {
    (for {
      line <- Source.fromFile(path).getLines()
    } yield makeEdges(parseLine(line))).toVector
  }

  // return shortest path represented by successive edges
  def shortestPath(from: Vertex, to: Vertex, g: Graph): (List[Vertex], Weight) = {

    val size = g.length
    var visitedMap: Vector[Boolean] = Vector.fill(size)(false)
    var weightsMap: Vector[Weight] = Vector.fill(size)(Long.MaxValue)
    var visited: List[Vertex] = List(from) 

    // naive implementation O(m * n)
    def nextPath: (Vertex, Edge) = {
      val availableEdges = (for {
        vertex <- visited
        edge <- g(vertex)
        if visitedMap(edge._1) == false
      } yield (vertex, edge)).toList

      // can not reach. we haven't no available path
      if (availableEdges.isEmpty) (-1, (0, 0))
      else {
        // dijkstra selection
        val minPath = availableEdges.minBy { case (vertex: Vertex, edge: Edge) =>
          weightsMap(vertex) + edge._2
        }

        // (from, (to, weight))
        minPath
      }
    }

    visitedMap = visitedMap.updated(from, true)
    weightsMap = weightsMap.updated(from, 0.toLong)
    var next = nextPath
    var current = next._1
    var nextVertex = next._2._1
    var weight = next._2._2

    // until visited 'to' vertex or no path
    while(!visitedMap(to) && next._1 != -1){
      current = next._1
      nextVertex = next._2._1
      weight = next._2._2

      visited = visited ++ List(nextVertex)
      visitedMap = visitedMap.updated(nextVertex, true)
      weightsMap = weightsMap.updated(nextVertex, weightsMap(current) + weight)
      next = nextPath
    }

    // If there is no path between a vertex v and vertex 1,
    // define the shortest-path distance between 1 and v to be 1000000.
    if (visitedMap(to)) (visited, weightsMap(to))
    else (List(), 1000000)
  }

  def solution = {
    // you can download this file here
    // http://spark-public.s3.amazonaws.com/algo1/programming_prob/dijkstraData.txt
    val path = "src/main/scala/coursera/dijkstra/dijkstraData.txt"

    // small test data
    // ref: http://fisnikhasani.com/dijkstras-algorithm/
    // val path = "src/main/scala/coursera/dijkstra/smallGraph.txt"

    // vertex index starts from 1, not 0
    val g: Graph = Vector() +: makeGraph(path)
    val targets = List(7,37,59,82,99,115,133,165,188,197)

    val answer = for (t <- targets) yield shortestPath(1, t, g)._2
    println(answer)
  }

  solution
}
