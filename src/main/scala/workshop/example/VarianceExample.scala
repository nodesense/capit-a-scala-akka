package workshop.example

object VarianceExample extends  App {
  sealed trait Appliance // Type
  case class Light(id: Int) extends  Appliance // SubType
  case class Fan(id: Int) extends  Appliance  // SubType

  def invariantExample() = {
    // Complex Types Box[Appliance], Box[Light], Box[Fan]
    class Box[T] (val appliance: T)
    // Invariant, simple, strait forward
    val box1: Box[Light] = new  Box(Light(100))
    val appliance1 = new Light(100); // OK
    // NOT OK, Box[Light] cannot be assigned to Box[Appliance]
    //val applianceBox: Box[Appliance] = lightBox
  }

  def coVariantExample() = {
    // in generic definition , +T
    // Co-Variant +T
    class Box[+T] (val appliance: T)

    def inspect(applianceBox: Box[Appliance]) = {
      println("Inspecting " + applianceBox.appliance)
    }

    val lightBox: Box[Light] = new  Box(Light(100))
    inspect(lightBox)

    val fanBox: Box[Fan] = new Box(Fan(600))
    inspect(fanBox)

    // Light is an Appliance [Subtype]
    // Co-Variant allow Box[Light] can be assigned to Box[Appliance]
    val applianceBox: Box[Appliance] = lightBox
  }

  coVariantExample()


}
