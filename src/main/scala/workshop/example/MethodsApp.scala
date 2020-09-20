package workshop.example

import scala.annotation.tailrec

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

  // fact(500000)
  // every function leave call stack, it reserve memory
  // fact(5) { stack, n = 5 }
  // fact(4) { stack, n = 4 }
  // fact(3) { stack, n = 3 }
  // fact(2) { stack, n = 2 }
  // fact(1) { stack, n =  1 }  <-- top , wind up

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


  // tail recursion is to avoid expensive call stack
  // rewrite the code to use simple non-call stack way
  // for the compiler to optimize your program during compile


  // n * fact(n-1) - not compatible with tail recurrsion due to n (left)

  @tailrec
  def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else  gcd(b, a % b)
  }

  // cannot be tail recursing due to call stack maintenance
  //@tailrec //ERROR
  def factorial(n: Int): Int = {
    if (n <= 0)
      return 1

    println(s"N $n")

    n * factorial(n - 1);
  }

  def factorialWithTrail(n: Int) = {
    // accumulator
    var acc = 1

    @tailrec
    def fact(i: Int): Int = {
      if (i <= 0)
        return acc

      acc *= i;

       fact(i - 1)
    }

    fact(n)
  }

  println(factorialWithTrail(10))

}
