package workshop.example

object FunctionsExample extends  App {
  // Functions, similar to Java Lambda
  // 2.11, converted to classes, with trait/interface, 1.7, 1.8 etc
  // 2.12, converted to Java Function/lambda JDK 1.8

  // Functions - First class citizen
  // Functions are objects, assigned to val, var, return object, pass object as argument
  // lazy initiation, invocation etc
  // Methods (def) are not OBJECTs

  // don't use def for function, annoymose function

  // takes 0 arg, returns 0 output
  val hello = () => println("Welcome")
  val hello2 = hello // reference

  println("ref ", hello) // print obj addr
  println("ref ", hello2) // print obj addr

  // call
  println("calling ", hello())

  // function with 1 arg, 1 output: Int
  val power = (n: Int) => n * n

  // multi line block st
  val power2 = (n: Int) => {
    println(n)
    n * n
  }

  println("power", power(5))

  val add = (a: Int, b: Int) => a + b

  // FunctionN - trait
  // N number of arguments passed into to a function
  // Return type can be Unit (void) or any other type
  // Function0, Function1...Function22
  // => is sugar part, scala compiler convert => into FunctionN

  // zero argument Function0, return Unit (void)
  val greeting = new Function0[Unit] {
      def apply(): Unit = println("good morning")
  }

  // Invoke functionN, either by apply or normal call
  greeting.apply() //

  // calls apply automatically
  greeting() // sugar, compiler make call to greeting.apply()

  // 1 arg, return 1 Long
  // the  last type is return type
  val cube = new Function1[Int, Long] {
    def apply(n: Int): Long = n * n * n
  }

  println(cube(5))

  // scala compiler converts => into FunctionN

  println(add(10, 20))
  println(add.apply(10, 20))

  println("Curries", add.curried(10)(20))
  println("tuple", add.tupled( (10, 20)) )

  // define a function that has explicit return type
  // Compiler Error
  // val mul = (a: Int, b: Int): Long => a * b // compiler error

  // Type declaration
  // val variableName: TYPE, val i : Int = 10

  // mul is val name
  // (Int, Int)=> Long a FunctionN, Function2[Int, Int, Long] is a TYPE
  val mul: (Int, Int)=> Long = (a, b) => a * b

  val mul2: Function2[Int, Int, Long] = mul;
  val mul3: (Int, Int)=> Long =  mul2

  println("mul(10, 2)", mul(10, 2))

  // 3 minutes
  // Define a functionN for sub

  // def vs FunctionN/=>

  def div(a: Int, b: Int) = a / b

  // Will not work, compilation error, div is not FunctionN, no apply
  // div.apply(10, 20)

  println("Div", div(10, 2))

  // convert to def method to functionN
  // _ basically def to functionN
  val division = div _;

  val division2: Function2[Int, Int, Int] = division;

  println(division.apply(10, 2)) // works

  // (div _)(30, 40) // works

  // in SCALA, has default implicit conversion which convert def to function sugar

}
