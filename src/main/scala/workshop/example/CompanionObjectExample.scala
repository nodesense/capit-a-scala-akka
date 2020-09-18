package workshop.example

import workshop.models.{Brand}

object CompanionObjectExample extends  App {
  // how to avoid using new keyword to create object

  // no new keyword
  val b1 = Brand() // calls Brand.apply()
  val b2 = Brand(12)  // calls Brand.apply(12)
  val b3 = Brand(145, "Apple")  // calls Brand.apply(145, "Apple")

  println(b3.name)
}
