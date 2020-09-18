package workshop.example

object HigherOrderFunctionExample extends  App {
  // (Int, Int) => Int: f, Function2[Int, Int, Int]
  val sum: ( (Int, Int) => Int, Int, Int) => Int = (f, a, b) => f(a, b)

  def add(a: Int, b: Int) = a + b
  def sub(a: Int, b: Int) = a - b

  // scala implicitly convert def to function2
  println(sum(add _, 10, 20))
  println(sum(sub, 10, 20))

}
