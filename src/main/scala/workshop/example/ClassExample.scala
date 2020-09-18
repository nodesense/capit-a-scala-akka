package workshop.example

//import workshop.models.Product // import single
// import workshop.models._ // import * all
import workshop.models.{Product} // use command to import more

object ClassExample extends  App {
  // new is a keyword, statement, not composable function
  // new is not functional
  val p = new Product(1000, "Iphone", 50000, 10)
  val p2 = new Product(2000, "Pixel pro", 45000, 20)

  //println(p.discount)//error:  discount is constructor arg, not a class member
  // access member variable p/public scope
  println("ID ", p.id)
  // p._price // error

  p.setPrice(60000)
  println("Price " + p.getPrice())

  p.price = 55555 // calls setter price_= method
  println("Price " + p.price) // calls getter price

  println("Product ", p)
}
