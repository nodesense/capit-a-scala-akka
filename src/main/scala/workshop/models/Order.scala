package workshop.models


// what is case class
// special class with companion object built by Scala compiler automatically
// it has apply, unapply methods build in
// by default the members are immutable
// id, customerId, amount are member variables, of val/immutable type, public scope
// used to represent a fact, that doesn't change
// var is allowed, not recommended
// toEquals, toString
case class Order(id: Int, customerId: Int, amount: Double)
