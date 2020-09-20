package workshop.example

object ReduceExample extends  App {

  val collection = List(1, 3, 2, 5, 4, 7, 6)

  // reduce, accept collection of elements, produce 1 output, min/max, sum
  // starts with first element, then goes reduceLeft to right
  // start with first element, 1, 3
  // finding the maximum valued element
  val res = collection.reduce((x, y) => x max y)
  println(res)

  // first _ is left value (x), second _ is right value (y)
  val maximum  = collection.reduce(_ max _)
  println(s"Maximum element = $maximum")

  // find the minimum element using reduce function
  val minimum  = collection.reduce(_ min _)

  val sum  = collection.reduce(_ + _)
  println(s"Minimum element = $minimum")
  println(s"sum elements = $sum")


}
