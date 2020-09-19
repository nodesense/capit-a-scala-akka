package workshop.example

import workshop.models.Order

object TypeSystemExample extends  App {
  // type alias
  type Age = Int

  type Pair = (Int, Double)  // Tuple

  type CustomOrder = Order

  val parentAge: Age = 70

  // Generic, in Java <>, in class using []
  class Stack[T] {
    // LIFO using immutable list
    var elems: List[T] = Nil
    def push(x: T) = elems = x :: elems
    def top (): T = elems.head
    def pop() = elems = elems.tail
    def isEmpty = elems.isEmpty
  }

  trait Goods

  case class Book(id: String) extends  Goods
  case class Cycle(id: String) extends  Goods

  val stackCycles: Stack[Cycle] = new Stack

  // stackCycles.push(Book("3 idiots"))// error

  val stack: Stack[Book] = new Stack
  stack.push(Book("3 idiots"))
  stack.push(Book("programming in scala"))
  println("is empty", stack.isEmpty)
  println("top ", stack.top())
  stack.pop()
  println("top ", stack.top())

}
