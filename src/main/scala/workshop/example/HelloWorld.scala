package workshop.example

// App etc later, Trait, it has main function, static void main(String argv[])
object HelloWorld extends  App {
  println("Hello World")
  println("args length " + args.length)

  // in scala to access array by position, (index)
  println("arg0" + args(0) ) // args(0) functional call
  println("arg1" + args(1) ) // args(0) functional call

}
