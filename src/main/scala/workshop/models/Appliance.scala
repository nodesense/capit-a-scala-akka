package workshop.models

// limit trait within scope

sealed trait Appliance {
  def on(): Boolean
  def off(): Boolean = false // default implementation, used only if child class not overriding
}

// without sealed, means can be used anywhere including users of the library
trait PowerConsumption {
  def reading(): Double
}

class Light extends  Appliance with PowerConsumption {
  override def on(): Boolean = true

  override def reading(): Double = 10 * 20
}

class Fan extends  Appliance with PowerConsumption {
  override def reading(): Double = 10 * 15

  override def on(): Boolean = true
  override def off(): Boolean = {
    println("Off")
    true
  }
}