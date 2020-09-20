package workshop.example

object FlattenExample extends  App {


  // flatten, flatMap - does the same purpose
  // should use flatten in most case, better with performance


  val couples = List( List("kim", "al"), List("julia", "terry"))
  val people = couples.flatten

  println(" Couple ", couples)

  println(" People flatten ", people)

  val flatMapResult = couples.flatMap( l => l)
  println(" flatMapResult ", flatMapResult)



  // also respect the Option/Future
  val x = Vector(Some(1), None, Some(3), None)
  println("X", x)
  // supress None options
  // take the value from options, return numbers Int, not Option[Int]
  println("Options Flatten", x.flatten)


  // flatMap(identity) is similar to map(identity).flatten.
  // like doing  two iterations, take more time.

  // map(identity) gives you the same collection, at  end it is the same as flatten.

  //  flatten is faster, easier to understand and designed for this purpose.

  // flatmap
  println("flatMap", timeElapsed(List(List(1, 2, 3, 4), List(5, 6, 7, 8)).flatMap(identity)))
  // map and then flatten
  println("Map Flatten", timeElapsed(List(List(1, 2, 3, 4), List(5, 6, 7, 8)).map(identity).flatten))
  // flatten
  println("Flatten", timeElapsed(List(List(1, 2, 3, 4), List(5, 6, 7, 8)).flatten))

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
