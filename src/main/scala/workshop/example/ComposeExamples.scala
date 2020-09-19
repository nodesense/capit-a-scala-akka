package workshop.example

object ComposeExamples extends  App {

  // f is function
  // g is a function
  // h is a function
  // compose f(g()) or compose g(f()), g(f(h()))
  // higher order function

  def f(x: String) = s"f($x)" // f('x')
  def g(x: String) = s"g($x)" // prints g('x')

  // returns a new function, the compose f and g
  // f(g(x))
  // first it calls result =  g(x)
  // the result is passed to f(result)
  // composition right to left
  val fComposeG = f _ compose g _
  println("fComposeG", fComposeG("x"))

  // g(f(x))
  // left to right
  // first result = f(x) executed
  // then g(result) executed
  val fAndThenG = f _ andThen g _
  println("fAndThenG", fAndThenG("x"))

  // gst
  // discount
  // coupon
  // exceptions

  def gst(amount: Double) = amount * 1.18
  def discount(amount: Double) = amount * .95
  def coupon(amount: Double) = amount * .98
  def price(amount: Double) = amount * 1

  def electronicProducts =
                  (price _) andThen  (discount _) andThen (gst _)

  def electronicProducts2 =
    (price _) andThen  (discount _)

  def agroProducts =
    (coupon _)  compose  (discount _) compose (price _)

  println("electronicProducts", electronicProducts(100))
  println("agroProducts", agroProducts(100))
}
