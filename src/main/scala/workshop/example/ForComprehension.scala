package workshop.example

object ForComprehension extends  App {

  Some(10) // Option
  None // Option
  def toInt(value: String): Option[Int] = {
    try {
      Some(value.toInt)
    } catch {
      case _ => None
    }
  }


  val result = for {
                      value <- toInt("100")
                      discount <- Some(value * .9) // called when value is Some
                    } yield discount

  println(result)
}
