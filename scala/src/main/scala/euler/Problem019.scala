package euler

// Counting Sundays
// from: https://projecteuler.net/problem=19

object Problem019 extends App {
  def isLeapYear(y: Int) =
    if (y % 4 != 0) false
    else if (y % 100 != 0) true
    else if (y % 400 == 0) true
    else false

  def monthDays(y: Int, m: Int) = {
    if (m == 4 || m == 6 || m == 9 || m == 11) 30
    else if (m == 2 && isLeapYear(y)) 29
    else if (m == 2) 28 // not leap year
    else 31
  }

  def solution = {

    var dayOfWeek = 0 // 1901.01.01, Tue
    // 1901.01.01 ~ 2000.12.31
    val sundays = for{
      y <- 1901 to 2000
      m <- 1 to 12
    } yield {
      val days = monthDays(y, m)
      val prev = dayOfWeek
      dayOfWeek = (dayOfWeek + days) % 7 // update day of week

      // if you want to count # of sundays in a month, use 'prev % 7 >= 35'
      if (prev% 7 == 0) 1 // check if the first of a month is monday
      else 0
    }

    println(sundays.sum)
  }

  solution
}
