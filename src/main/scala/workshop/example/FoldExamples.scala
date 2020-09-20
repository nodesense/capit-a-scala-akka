package workshop.example

import workshop.models.Order

object FoldExamples extends  App {
  val orders = List(
                    Order(1, 100, 1000),
    Order(2, 50, 50),
    Order(3, 200, 300),
    Order(4, 200, 100),
  )


  // fold - for parallel computation
  // foldLeft - sequential from left to right
  // foldRight - sequential from right to left

  val map = orders.groupBy(_.customerId)
  for (m <- map) println(m._1, m._2)


  val resultLeft = (orders.foldLeft(Map[Int, Double]()) {
    (custMap, row) =>
      println("Cusid", row.customerId)
      val amount: Double = custMap.getOrElse(row.customerId, 0.0)
      val newAmount = amount + row.amount
      custMap + (row.customerId -> newAmount)
  }).toList

  println(resultLeft)


  val resultRight = (orders.foldRight(Map[Int, Double]()) {
    (row, custMap) =>
      println("Cusid", row.customerId)
      val amount: Double = custMap.getOrElse(row.customerId, 0.0)
      val newAmount = amount + row.amount
      custMap + (row.customerId -> newAmount)
  }).toList

  println(resultRight)



  case class Pack(name: String, age: Int, dob: String)

  case class NewPack(name: String, pack: List[Pack])

  object NewPack{
    def getNewPackList(data: List[Pack]): List[NewPack] =
      data.map{case pack@Pack(name, _, _) => NewPack(name, List(pack))}
  }

  println(NewPack.getNewPackList(List(Pack("Nila", 10, "feb-18"))))

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
