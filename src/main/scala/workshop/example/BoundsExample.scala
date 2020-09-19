package workshop.example

object BoundsExample extends  App {
  // Bounds
  // LowerBound
  // Upperbound

  trait Thing // upper bound
  class Vehicle extends  Thing
  class Car extends  Vehicle
  class Jeep extends  Car
  class ArmyJeep extends  Jeep // lower bound

  class Animal
  class Lion extends  Animal

  def genericParking: Unit = {
    class Parking[A] (val vechile: A)

    new Parking[ArmyJeep](new ArmyJeep) // OK // same type
    new Parking[Jeep](new ArmyJeep) // OK // super type, subtype

    new Parking[Lion](new Lion) // Allowed, but we don't want this
    // new Parking[Jeep](new Cycle) // NOT OK error
  }

  def upperBoundExample: Unit = {
    // <: upper bound
    class Parking[A <: Vehicle] (val vehicle: A)
    new Parking[ArmyJeep](new ArmyJeep)
    new Parking[Jeep](new ArmyJeep)

    // new Parking[Animal](new Lion)  // ERROR, restriction in type, Vehicle should super type
    // new Parking[Lion](new Lion) // ERROR
  }

}
