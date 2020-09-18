package workshop.example

object PartialFunctionExample extends  App {
  // normalFunction is invoked even the input is wrong
  // normalFunction (a, b) {
  //    if (a < 0 || b < 0) throw exception/error/.......
  // else
  //    return an output
  //}
  // normalFunction(-10, -5) calls the function and fails
  // best way is check whether func accept values, process them or not
  // then do function call
  // pre-condition, a > 0, b> 10
  // PartialFunction [one input, out output]
  val divide = new PartialFunction[Int, Int] {
    def apply(x: Int) = 10 / x
    override def isDefinedAt(x: Int): Boolean = {
      println("isDefinedAt ", x)
      x != 0
    }
  }

  // easy way of defining partial function using case statement
  // generate partial function, automatically define apply, isDefinedAt
  val div: PartialFunction[Int, Int] = {
    case x: Int if x != 0 => 10 / x
  }

  println("0", divide.isDefinedAt(0)) // false
  println("2", divide.isDefinedAt(2)) // true

  if (divide.isDefinedAt(2)) {
    println("/2", divide(2))
  }

  println("0", div.isDefinedAt(0)) // false
  println("2", div.isDefinedAt(2)) // true

  if (div.isDefinedAt(2)) {
    println("/2", div(2))
  }

  // 0 will be an error, not handled by partial function
  val list: List[Int] = List(2, 4, 8, 0, 6, 5)
  // collect respect partial function, it calls isDefinedAt
  // map, filter few functions does't respect partial function
  val result = list.collect(divide)
  println(result)

  val result2 = list.collect(div)
  println(result2)

//  list.filter(x => divide.isDefinedAt(x))
//    .map(x => divide(x))
}


// collection.
//           /.filter
             //.map
             // .reduce
             //.collect  --