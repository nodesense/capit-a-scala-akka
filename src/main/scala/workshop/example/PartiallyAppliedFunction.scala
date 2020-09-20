package workshop.example

object  PartiallyAppliedFunction  extends  App {

  def calculateProductPrice(discount: Double, productPrice: Double): Double =
    (1 - discount/100) * productPrice

  val dicountApplied = calculateProductPrice(30, _: Double)


  def getDiscount(discount: Double) = {
    println("Discount called")
    1 -  discount/100.0
  }

  def getPrice(price: Double) = {
    println("price called")
    price
  }


  def calculateProductPrice2(discount: Double, price: Double): Double =
    getDiscount(discount) * getPrice(price)

  def wrap(prefix: String, html: String, suffix: String) = {
    prefix + html + suffix
  }

  val wrapWithDiv = wrap("<div>", _: String, "</div>")
  wrapWithDiv("<p>Hello, world</p>")

  val da = calculateProductPrice2(30, _: Double)
  println(da(60))
}
