package workshop.example

import workshop.models.{Appliance, Fan, Light}

object TraitExample extends  App {
  // class - create instance
  // case class - create instance
  // object - instance

  // trait - polymorphic, virtual, cannot create instances, java interface
  // traits also can have members, variables, methods, abstract

  // Appliance is type
  // Light, Fan are sub-types or product of appliances

  // Boolean is product of true/false, 2 possible values
  // Int is product, finite range = s^-31 to 2 ^32-1 possible values
  // String is product, inifite range. ..............


  def turnOn(appliance: Appliance): Unit = appliance.on()

  val appliance1: Appliance = new Fan()
  appliance1.on()
  appliance1.off()

  turnOn(new Light())
  turnOn(new Fan())

}
