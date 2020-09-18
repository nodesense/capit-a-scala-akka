package workshop.example

object  HelloWorldMain {
  // to define a method use keyword def
  // Unit is return type, in java similar to void
  def main(args: Array[String]): Unit = {
    println("Main func")
    println(args.length) // args(0), args(1)

    // var, val, lazy val, def, functions....

    // variable, the value can change, reference can be changed
    var i = 10 // type inference, compiler derive type from right hand expression

    var j: Int = 0  // explicit type declaration

    println("i", i, " j ", j)

    i = 20
    j = 5
    println("i", i, " j ", j)

    // value type, immutable, similar to const
    // value or reference cannot be changed


    // at runtime, when JVM runs this code,
    // it creates memory for PI, evaluate expression  value 3.14 assigned
    val PI = 3.14
    val k: Double = 3.14

    // PI = 2.14 // error
    println("PI", PI)

    // lazy val
    // at runtime, it declarare variable PIE as type double
    // it will not evaluate the expression until first use
    lazy val PIE = 3.13 + 0.01

    // There no lazy var

    println("PIE ", PIE) // now the  3.13 + 0.01 expression is evaluated

    // def to define methods, java member functions, not java lambda -> [annoymose]
    def getPie(): Double =  {
      // allocate 100 mb
      // connect to db, begin tranction
      // use system resources, need cleanup etc
      println("getPie called")
      return 3.14 // return keyword is optional
    }

    // prints getPie called
    // val PI2 = getPie() // execute the expression, take the value, assign to PI2

    // getPie is not called until first use
    lazy val PI3 = getPie()

    // first use of variable, getPie called and initialize value 3.14 to PI3
    print("PI3", PI3)

    print("PI3", PI3)

//
//    def beginTransaction(p1: => Object, p2: Connection) = {
//     //  val connection = getConnection() // whether want or not, connectin is established
//      lazy val connection = getConnection() // not called until first use
//      switch (...) {
//        case 1
//          connection.begin(), // getconnection()
//          connection.transact(...)
//        connection.transact(...)
//          conn.commit
//        case 2
//        connection.begin() // getconnection()
//        connection.transact(...)
//        connection.transact(...)
//        conn.commit
//        case 3 // not calling getconnection(), not used
//          ... done't use connection
//         case 4:
//            connection
//             //delete
//      }
//       if (true) {
//
//         connection.begin()
//         connection.transact(...)
//         conn.commit
//       } else {
//
//       }
//       connect to db
//       write the object
//        close the transaction
//    }


  }
}
