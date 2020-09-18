package workshop.example

import workshop.models._

object PatternMatching extends  App {
  // switch case in java - is statement
  // match, case, in scala - expression that returns value

  // match is an expression that returns output
  val result = 10 % 3  match  {
      // match value
    case 0 => "zero"
    case 1 => "one"
    case _ => "default value"
  }

  println("Result ", result)

  def process(obj: Object) = obj match {
    // match  type
    // only the first match executed
    case p: Product => println("processing product ", p)
    case b: Brand => println("Processing brand ", b)
    // guarded case
    case Order(id, customerId, price) if price > 10000 => println("Order VIP Customer", price)
    // _ used for place holder, whatever
    case Order(_, _, price) if price > 5000 => println("Special Customer", price)
    case c:Order if c.amount >= 3000 => println("Free Shipping order", c)
    case c:Order => println("Processing order", c)
    case _ => println("Unknown object ", obj)
  }

  process("Hello")
  process(new Product(88888))
  process(Brand(4444, "Apple"))
  process(Order(123, 1, 1000))
  process(Order(123, 1, 20000))

  // try..catch..finally match word is not used, implicit
  try {
    val d = 10 / 0
  }catch {
    case ae: ArithmeticException => println("Div by zero error Error", ae)
    case e: Exception => println("unknown exception ", e)
    case _: Throwable => println("default exception ")
  }

}
