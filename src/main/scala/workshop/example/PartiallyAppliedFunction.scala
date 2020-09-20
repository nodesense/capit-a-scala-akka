package workshop.example

object  PartiallyAppliedFunction  extends  App {

  def calculateProductPrice(discount: Double, productPrice: Double): Double =
    (1 - discount/100) * productPrice


  println(calculateProductPrice(10, 100))

  // partially applied function, a curry function
  // returns a new function, that wraps the value 30
  val discountApplied30 = calculateProductPrice(30, _: Double)

  println(discountApplied30(1000))

  def htmlTag(parent: String, child: String, content: String) =
                              s"<$parent><$child>$content</$child></$parent>"

  // closure, curry
  val div = htmlTag("div", _: String, "100")
  val table = htmlTag("table", _: String, "100")

  println(div("span"))

}
