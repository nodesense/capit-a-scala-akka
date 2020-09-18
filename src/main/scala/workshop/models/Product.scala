package workshop.models



// auxillary constructor


// no static member variable/methods allowed in scala class

// discount constructor args, not a class member
// id a member variable, class member
// prefixing val/var make the cons arg a member variable, by default public scope
// no public keyword
// private, protected keywords there
// p.id
class Product(val id: Int,
              val name: String,
              private var _price: Int,
              discount: Int) {
  // constructor - default
  // constructor function, whole class body
  println("Product Cons")
  println("ID", id)
  println("Discount ", discount)


  // auxiliary constructor, secondary cons
  // using keyword this

  def this(id: Int) = {
    // calling default constructor with missing values
    this(id, "", -1, 0) // calls primary class constructor
  }

  def this(id: Int, name: String, discount: Int ) = this(id, name, -1, discount)

  def this() = {
    this(0) // calls this(id: Int)
  }

  // Needed if Java like compatablity
  // Java like getter and setter
  def getPrice() =  _price
  // Java like setter
  def setPrice(value: Int) = _price = value



  // SCALA Way, getter and setter
  // getter
  def price = _price

  //setter, scala style, special, not compataible with Java setter
  // price_= itself a method
  // invoked p.price = 55555 will call price_, value shall be 55555 as arg
  def price_= (value: Int) = _price = value


  // Scala special string /template string
  override def toString() = s"ID=$id,name=$name,price=${_price}"

}
