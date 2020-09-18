package workshop.example

object MethodsApp extends  App {
  // explicit return type, single line
  // b is default if not passed
  def add(a: Int, b: Int = 0 ): Int = a + b
  // return type is based on type inference, single line
  def sub(a: Int, b: Int) = a - b

  // block statement
  // inference
  def mul(a: Int, b: Int) = {
    println(a, b)
    // return exprssion
    a * b // the last evaluated expression is automatically returned
    // no need for return keyword
  }

  def div(a: Int, b: Int): Int = {
    // avoid using return statement
    return a / b // using returns need explicit type for return
  }


  def fact(n: Int): Int = {
    if (n == 1) 1 // return 1
    else n * fact(n - 1) // return n * fact(n - 1)
  }


  // vars argument *
  def printNames(names: String*): Unit = {
    names.foreach(println)

    // forloop is an expression
    for (name <- names) {
      println(name)
    }
  }

  // Splat _* similar to JS spread ...
  val greeting = List("Welcome", "to", "Scala")
  printNames(greeting: _*) // get greeting list as individual object

  printNames() // 0 args
  printNames("scala") // 1 args
  printNames("scala", "java", "jvm") // 3 arg

  println(add(10, 20))

  // default argument
  println(add(10)) //  b = 0 by default

  // named argument, compiler shall fix the order based on name
  println("Sub", sub(b = 20, a = 10))

  // FIXME: call other methods later

  // scala always FAVOR EXPRESSION instead of statements
  // statements is executed, same for expression is executed
  // statement doesn't return values
  // expression returns the value

  val age = 16
  // if statement is expression that returns value
  val canVote = if (age >= 18) true else false
  println("canVote ", canVote)

  val canVote2 = if (age >= 18) {
                    println("age is ", age)
                    true // last value returned
                  }
                  else  {
                    println("age is ", age)
                    false // last value returned
                   }

  println("canVote2 ", canVote2)

}
