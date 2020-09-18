package workshop.example

import scala.util.{Try, Success, Failure}

object TrySuccessFailureExample extends  App {
   // do an operation: Succcess(value) or Failure(exception)
  def safeDiv(a: Int, b: Int): Try[Int] = Try (a/b)

  val x = safeDiv(10, 2)
  if (x.isSuccess) {
    println("Success X is ", x.get) // prints 5
  }

  val y = safeDiv(10, 0)
  if (y.isFailure) {
    println(("Failure y Error " + y.failed.get))
  }

}
