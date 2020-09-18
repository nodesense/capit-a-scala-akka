package workshop.example

object ByNameExample extends  App {
  //  if () expression with/without curly brace
  // if (predicate) (trueBlockCode) else (elseBlockCode)

  def nano(): Long = {
    println("Nano function called")
    System.nanoTime()
  }

  // by Value/Object/Ref
  def printTime(t: Long) = {
    println("Print Time")
    println(t)
  }

  // by name parameter/code block
  // t is a code, not yet executed, can be single line or block
  // code t, provide Long as return value, lazily evaluated when called
  def delayed(t: => Long) = {
    println("Delayed ")
    println(t)
    // println(t)
  }

  printTime(nano())

  val k = 100
  printTime( {
    println("Code block")
    k
  })

  println("-------------delayed")
  delayed( nano() )

  delayed( nano() )

  delayed({
    println("Lazy processing 1000000")
    100000
  })

}
