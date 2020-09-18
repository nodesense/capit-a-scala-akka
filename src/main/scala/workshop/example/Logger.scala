package workshop.example

// Object? Singleton, only one copy
// created lazily on first use
// Logger itself is an object, we cannot create object of Logger again
// useful to represent static types similar to Java
object Logger {
  // member variable for Logger, public scope
  var logLevel = "debug"

  // private/protected supported
  def setLevel(level: String) = logLevel = level

  // whole body is object constructor
  // can have val, def member variables, private, protected access specifier
  println("Logger constructor")

  override def toString=s"Logger ($logLevel)"
}
