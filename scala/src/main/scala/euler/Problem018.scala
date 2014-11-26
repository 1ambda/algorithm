package euler

object Problem018 extends App {
  val triangle = """
75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23"""

  def parse(triangle: String): Array[Int] = {
    ("\\d\\d".r findAllIn triangle).map(_.toInt).toArray
  }

  case class Node(row: Int, idx: Int)
  case class Path(nodes: List[Node], acc: Int)


  def triHeight(t: Array[Int]): Int = {
    def recur(acc: Int, n: Int): Int = {
      if (acc - n == 0) n else recur(acc - n, n + 1)
    }

    recur(t.length, 1)
  }

  def allPath(t: Array[Int]): Set[Path] = {
    // in path, nodes are concatenated in reverse order to avoid performance prb
    def nextPath(p: Path): List[Path] = {
      // current node
      val n = p.nodes.head
      // next node -> idx + row, idx + 1 + row
      val n1 = Node(n.row + 1, n.idx + n.row)
      val n2 = Node(n.row + 1, n.idx + n.row + 1)

      List(
        Path(n1 :: p.nodes, p.acc + t(n1.idx)),
        Path(n2 :: p.nodes, p.acc + t(n2.idx)))
    }

    val height = triHeight(t)

    // extend paths until height
    def extendPaths(ps: Set[Path], h: Int): Set[Path] = h match {
      case h if h == height => ps
      case _ => {
        val nextPaths = for {
          prev <- ps
          next <- nextPath(prev)
        } yield next

        extendPaths(nextPaths, h + 1)
      }
    }

    val initialPath = Path(List(Node(1, 0)), t(0))

    extendPaths(Set(initialPath), 1)
  }

  def solution = {
    val t = parse(triangle)
    println(allPath(t).maxBy(_.acc).acc)
  }

  solution
}
