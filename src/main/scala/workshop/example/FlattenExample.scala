package workshop.example

object FlattenExample extends  App {

  val couples = List(List("kim", "al"), List("julia", "terry"))
  val people = couples.flatten

  println(people)

  val x = Vector(Some(1), None, Some(3), None)
  println(x.flatten)


  // flatMap(identity) is similar to map(identity).flatten.
  // like doing  two iterations, take more time.

  // map(identity) gives you the same collection, at  end it is the same as flatten.

  //  flatten is faster, easier to understand and designed for this purpose.

  // flatmap
  println(timeElapsed(List(List(1, 2, 3, 4), List(5, 6, 7, 8)).flatMap(identity)))
  // map and then flatten
  println(timeElapsed(List(List(1, 2, 3, 4), List(5, 6, 7, 8)).map(identity).flatten))
  // flatten
  println(timeElapsed(List(List(1, 2, 3, 4), List(5, 6, 7, 8)).flatten))

  /**
   * timeElapsed
   */
  def timeElapsed[T](block: => T): T = {
    val start = System.nanoTime()
    val res = block
    val totalTime = System.nanoTime - start
    println("Elapsed time: %1d nano seconds".format(totalTime))
    res
  }
}
