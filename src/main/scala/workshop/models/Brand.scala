package workshop.models

class Brand(val id: Int, val name: String) {
  println("Brand created " + s"$id, $name")

  // can access private/protected members of object Object
}

// Companion object, should be on same file/package scope
// object with same class Name is called companion object

object Brand {
  // can access all private, protected of class Brand
  // involved to create object
  // apply is object factory
  // we can replace auxilary constructor of class with apply methods by overloading
  def apply() = new Brand(-1, "")
  def apply(id: Int) = new Brand(id, "")
  def apply(id: Int, name: String) = new Brand(id, name)


}