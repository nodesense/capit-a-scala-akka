package workshop.example

object ParameterGroup extends  App {
  // extended curries
  // memorized values
  // parameteric group, 3 curried functions
  // memorized values, closure, curries
  def sum (a: Int) (b:Int) (c:Int) = a + b + c

  // creates curried function automatically
  val add = (a: Int, b: Int) => a + b

  // sum _ converts def to function
  // sumOf10 is a partially applied function, the value 10 already applied
  val sumOf10 = (sum _) (10)  // a is 10 um (a: Int)
  val sumOf100 = (sum _) (100)

  val sumof10With5 = sumOf10(5) // a is 10, b is 5 sum (a: Int) (b:Int)

  // Int
  val result10_5_1: Int = sumof10With5(1) // a is 10, b is 5 sum, c is 1  def sum (a: Int) (b:Int) (c:Int) = a + b + c

  println("result10_5_1 ", result10_5_1)

  val result10_5_3: Int = sumof10With5(3)
  println("result10_5_3 ", result10_5_3)

  val add10 = add.curried (10) // a

  println("add10by20", add10(20)) // b is 20, prints 30
  println("add10by2", add10(2)) // b is 2, prints 12

  def htmlTag(parentTag: String) (tag: String) = s"<$parentTag>$tag</$parentTag>"
  val divTag = (htmlTag _ )("div")
  println(divTag("<span>10</span>"))
}
