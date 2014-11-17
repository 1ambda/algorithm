package coursera.scc

import scala.io.Source
import scala.collection.mutable.Stack

object FindSCCs extends App {

  case class Edge(from: Int, to: Int)
  type Adjacency = List[Int] // the vertex's adj vertices lists
  type Graph = Vector[Adjacency] // each vertex `n` is in vector as nth index


  def str2Edge(line: String): Edge = {

    line.split(" ").map(_.toInt) match {
      case Array(from, to) => Edge(from, to)
    }
  }

  def str2RevEdge(line: String): Edge = str2Edge(line) match {
    case Edge(from, to) => Edge(to, from)
  }

  def makeGraph(path: String, convert: String => Edge): Graph = {
    val f = Source.fromFile(path)
    var vertices: Graph = (for(i <- 0 to maxVertex) yield Nil).toVector

    for (line <- f.getLines()) convert(line) match {
      case Edge(from, to) => vertices = vertices.updated(from, to +: vertices(from))
    }

    f.close()
    vertices
  }

  def edges(g: Graph, vertex: Int) =
    if (vertex == 0) throw new RuntimeException("vertex cannot be 0 in edges")
    else g(vertex)

  def isNotExplored(vertex: Int) =
    if (vertex == 0) throw new RuntimeException("vertex cannnot be 0 in isNotExplored")
    else explored(vertex) == false

  // non recursive version to avoid stack overflow
  def DFS(g: Graph, vertex: Int): Int = {
    val stack = new Stack[Int]
    stack.push(vertex)
    count = 0

    while (!stack.isEmpty) {
      val v = stack.top
      explored = explored.updated(v, true)

      val notVisited = edges(g, v) filter isNotExplored

      if (notVisited.isEmpty) {
        count += 1
        t += 1
        f = f.updated(t, v)
        stack.pop()
      } else {
        stack.push(notVisited.head)
      }
    }

    count
  }

  def findSCC(g: Graph): Unit = {
    for(v <- maxVertex to 1 by -1 if isNotExplored(v)) {
      DFS(g, v)
    }
  }

  // you can download this file here. 
  // http://spark-public.s3.amazonaws.com/algo1/programming_prob/SCC.txt
  // size: 69M
  val path = "src/main/scala/coursera/scc/SCC.txt"
  val maxVertex = 875714 // the last node number

  lazy val g: Graph = makeGraph(path, str2Edge)
  lazy val gRev: Graph = makeGraph(path, str2RevEdge)

  // vertices as deacresing finishing time
  var f: Vector[Int] = (for(i <- 0 to maxVertex) yield 0).toVector
  // current finishing time 
  var t: Int = 0
  // leader vector
  // explored map indicates each vertex is vistied or not
  var explored: Vector[Boolean] = (for(i <-0 to maxVertex) yield false).toVector

  // step 1: find leader node
  var count = 0 
  findSCC(gRev)

  // step 2: find SCCs
  explored = (for(i <- 0 to maxVertex) yield false).toVector
  t = 0
  val fs = f

  def solution() = {
    val sccs = for(t <- maxVertex to 1 by -1 if isNotExplored(fs(t))) yield DFS(g, fs(t))
    // find 5 largest SCCs as decreasing order
    println(sccs.sorted.reverse.take(5))
  }

  solution()
}
