package workshop.example

object EitherExample extends  App {
  // Option is Some or None
  // Option is good, swallow exception or alternative flow
  // error/bad part is swallowed, take only good value

  // Either: Right, Left
  // convention: Right means good, Left means bad value
  // Good or bad/Exception, need both
  // Web get product by id 45678
      // 200 OK, with json
      // 404, not found, not an exception
     // 500 - server exception
     // bad request from client
  // read file
     // content of the file
    // errors like file not found, permission denied.. timeout
                              //      Left,  Right
  def safeDiv(a: Int, b: Int): Either[String, Int] =
                        if (b != 0) Right(a / b) else Left("Devide by Zero")

  val x: Either[String, Int] = safeDiv(10, 0)

  println(" isRight " + x.isRight)
  println(" isLeft " + x.isLeft)

  if (x.isRight) {
    println("X Right Value ", x.right.get)
  } else {
    println("X Left Value ", x.left.get)
  }

}
