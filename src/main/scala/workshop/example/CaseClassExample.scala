package workshop.example

import workshop.models._;  // import Order

// case object

// no special parameter needed, no member variable needs
case object RefreshCache {
  // ok to have members, but not used
}

case object Prefetch;

object CaseClassExample extends  App {
  // create new object using companion
   val o1 = Order(1000, 1, 4500) // companion object, it creates object
  // creating new object
  val o2 = Order(2000, 1, 9500)
  println("o1=" + o1)
}
