package workshop.example

object TupleExample extends  App {
  // Unit is tuple ()
  // return multiple values from a function
  // each element can have its own type
  // statically defined, compiled, not grow at dynamic

  val tuple2 = (10, 20)
  println(tuple2._1, tuple2._2)

  def getTuple(): (Int, String, Double) = (100, "Phones", 100000)

  val t = getTuple()
  println(t._1, t._2, t._3)
}
