package workshop.example

object Implicits extends  App {

  {
    val x: Int = 42.0.toInt // explicit
    println("X is", x)
  }

  {
    // how to make this default, automatic, instead of explicit casting
    // Rule 1: implicit follows scope
    //  restrict within block, object/class, function, package
    // Rule 2: Implicit strictly works based on Type System

    implicit def doubleToInt(d: Double): Int =  d.toInt
    implicit def booleanToInt(b: Boolean): Int =  if (b) 1 else 0

    // BAD IDEA
    // implicit def objToInt(obj: Object): Int =  1

    val x: Int = 42.0 // now compiler will use doubleToInt automatically to convert
    println("X is", x)

    val y: Int = true // implicit to booleanToInt
  }

  {
    // error, as doubleToInt defined in another scope
    // val y: Int = 42.0
  }



  {
    // implicit parameter passing
    // thread pool, db connection pool, queues, stacks used/shared across application
    // instance of above list pools, queues to be passed as parameter to another function
    // Rule 3: No duplicate type alloed

    implicit val i : Int = 10
    implicit val d : Double = 1.5 // double is ok, if implicit double used

    // implicit val k : Int = 60 // ambiguos error, as implicit Int already defined
    // parameteric group
    def add(a: Int) (implicit b: Int) = a + b
    println(add(20)) // a is 20, 10 is b, passed implicitly by compiler
    println((add(50)(15)))// a is 50, b is 15, prints 65, won't use implicits
  }

  {
    // b value can vary program to program
    // app 1, company A
    implicit val poolSize: Int = 1000

    def getConnectionPool(implicit  poolSize: Int) = None

  }

  {
    // b value can vary program to program
    // app 2, company B
    implicit val poolSize: Int = 5000

    def getConnectionPool(implicit  poolSize: Int) = None
  }

  {
    // b value can vary program to program
    // app 5, company B
    implicit val delayType: Int = 1000
  }


}
