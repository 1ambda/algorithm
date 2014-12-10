package euler

object Problem024 extends App {

  /*
   
   haskell implementation

   interleave :: a -> [a] -> [[a]]
   interleave x [] = [[x]]
   interleave x (y:ys) = (x:y:ys) : map (y:) (interleave x ys)

   permutate :: [a] -> [[a]]
   permutate [] = [[]]
   permutate (x:xs) = concat (map (interleave x) (permutate xs))
   
   */

  def interleave[A](x: A, ys: List[A]): List[List[A]] = ys match {
    case Nil => List(List(x))
    case (z :: zs) => (x :: z :: zs) +: interleave(x, zs).map(z +: _)
  }

  def permutate[A](xs: List[A]): List[List[A]] = xs match {
    case Nil => List(List())
    case (z :: zs) => (permutate(zs).map(interleave(z, _))).flatten
  }

  def solution = {
    // Total time: 63 s
    // slow, but works.
    // val permutations = permutate("0123456789".toList).map(_.mkString).sorted
    // println(permutations(1000000 - 1))

    // to decrease running time, you can use heuristic apporach
    // since 9! = 362881, 1000000th string will start with "2"
    // also, 8! = 40320, 1000000th string will start with "27"

    // val index = (1000000 % 9!) % 8!
    // == 32320
    // so, permutate("01345689").sorted(index - 1) 
    val permutations = permutate("01345689".toList).map(_.mkString).sorted
    println("27" + permutations(32319))
  }

  solution
}

