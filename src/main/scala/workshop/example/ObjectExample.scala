package workshop.example

object ObjectExample extends  App {
  println(Logger.logLevel) //first use, invoke object constructor
  Logger.setLevel("info")
  println(Logger.logLevel)
  println(Logger) // calls toString
}
