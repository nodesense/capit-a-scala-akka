package workshop.example

object ReduceExample extends  App {
  val collection = List(1, 3, 2, 5, 4, 7, 6)

  // finding the maximum valued element
  val res = collection.reduce((x, y) => x max y)

  println(res)

  val maximum  = collection.reduce(_ max _)
  println(s"Maximum element = $maximum")

  // find the minimum element using reduce function
  val minimum  = collection.reduce(_ min _)

  println(s"Minimum element = $minimum")

}
