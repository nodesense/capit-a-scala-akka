package workshop.example

object OptionExample extends  App {
  // Option means? a choice

  // "100" => output is 100 as int, some value or
  // "apple" ==> output None, no value

  // called function can handle exception
  // 1 definition of called function
  // exception is handled locally
  def parseInt(input: String): Option[Int] = {
    // try catch also an expression, returns value
    // if all good, returns try block output, 100
    // else catch block output, None
    try {
      Some(input.toInt) // returned Some(100)
    }catch {
      case _ => None // returned if any error
    }
  }

  // caller can handle exception
  // 100+ callers across applications call parseInt: not efficient
  val result: Option[Int] = parseInt("100")
  println("Option ", result)
  // check if option has value or not?
  println("Option isDefined ", result.isDefined)
  println("Option isEmpty ", result.isEmpty)

  if (result.isDefined) {
    // get the actual value from option which is 100 of type int
    val n: Int = result.get // return 100
    println("result", n)
  }

  val r2 = parseInt("200") match {
    case Some(value) => value
    case None => 0
  }

  println("R2", r2)

}
