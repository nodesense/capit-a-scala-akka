package workshop.example

import workshop.models.Order

object FoldExamples extends  App {
  val collection = List(1, 3, 2, 5, 4, 7, 6)

  // 1 million numbers, need to find min
  // 250K each, given 4 computers, min(250K), min(250K), min(250k) min(250k)
  // fold takes an initial value [accumulator init/start value]
  // start with start value [supplied outside the input]
  // fold is designed for parallism, multiple therads/cores
  // order of element is not guaranteed
  // sum, min, max, avg - order doesn't matter
  val sum2 = collection.fold(0) { (acc, i) => {
    println(s"acc $acc, i = $i")
    acc + i
  }}


  println("Fold Left ")
  // foldLeft
  // iterate sequentially, goes left to right, no parallism
  val sumLeft = collection.foldLeft(0) { (acc, i) => {
    println(s"acc $acc, i = $i")
    acc + i
  }}

  println("sumLeft ", sumLeft)

  println("Fold Right")
  // iterate sequentially, goes right to left, no parallism
  val sumRight = collection.foldRight(0) { (i, acc) => {
    println(s"acc $acc, i = $i")
    acc + i
  }}

  println("sumRight ", sumRight)

  // Meter reading , fold right would fit, diffence between consumption
  // 10  15  45  50 75

  val orders = List(
    Order(1, 100, 1000),
    Order(2, 50, 500),
    Order(3, 200, 300),
    Order(4, 200, 100),
  )

  // FIXME: Case classes
  // FIXME: Option Fold

  // fold - for parallel computation
  // foldLeft - sequential from left to right
  // foldRight - sequential from right to left

  val map = orders.groupBy(_.customerId)
  for (m <- map) println(m._1, m._2)

  // mapping customer id to Amount
  // val emptyMapAcc = Map[Int, Double](), create empty map

  val resultLeft = (orders.foldLeft(Map[Int, Double]()) {
    (custMap: Map[Int, Double], order: Order) =>
      println("Cusid", order.customerId)
      val amount: Double = custMap.getOrElse(order.customerId, 0.0)
      val newAmount = amount + order.amount

      custMap + (order.customerId -> newAmount)
  }).toList


  // { 100 -> 10000  } - immutable map

  // { 100 -> 10000
  //   50 -> 500 }

  // { 100 -> 10000
  //   50 -> 500,
  //   200 -> 300 }


  // { 100 -> 10000
  //   50 -> 500,
  //   200 -> 400 } // 300 + 100


  val resultLeft2 = (orders.foldLeft(Map[Int, Double]()) {
    (custMap, order) =>
      println("Cusid", order.customerId)
      val amount: Double = custMap.getOrElse(order.customerId, 0.0)
      val newAmount = amount + order.amount
      // add the map enty
      custMap + (order.customerId -> newAmount)
  }).toList


  println("Total Spend", resultLeft)


  val resultRight = (orders.foldRight(Map[Int, Double]()) {
    (order: Order, custMap) =>
      println("Cusid", order.customerId)
      val amount: Double = custMap.getOrElse(order.customerId, 0.0)
      val newAmount = amount + order.amount
      custMap + (order.customerId -> newAmount)
  }).toList

  println(resultRight)



  List(1, 3, 8).foldLeft(100)(_ - _) == ((100 - 1) - 3) - 8 == 88
  List(1, 3, 8).foldRight(100)(_ - _) == 1 - (3 - (8 - 100)) == -94

  val sum: Double = List(10.0, 20.0, 40.0).fold(0.0)((a, b) => a + b)

  // initialize a sequence of strings
  val str_elements: Seq[String] = Seq("hello",
    "Geeks", "For", "Geeks")
  println(s"Elements = $str_elements")

  // Concatenate strings with fold function
  val concat: String = str_elements.fold("")(
    (a, b) => a + "-" + b)
  println(s"After concatenation = $concat")

  val concat2: String = str_elements.foldLeft("")(
    (a, b) => a + "-" + b)
  println(s"After concatenation = $concat2")


  val numbers = List(5, 4, 8, 6, 2)
  numbers.fold(0) { (z, i) =>
    z + i
  }

}
