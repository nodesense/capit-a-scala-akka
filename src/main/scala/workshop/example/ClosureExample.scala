package workshop.example

object ClosureExample extends  App {
  // clousure function defines bound scope for variable
  // variable live still the visibility on
  // Closure useful to define functional state, abstraction, encapsulation

  // auto increment seq as example
  // 1 by 1 ==> 1, 2, 3, 4,
  // 10 by 5 ==> 10, 15, 20, ....

  // Curry: A function returns another function

  def seq(seed: Int, step: Int = 1): () => Int = {
    //
    var counter = seed // closure bound variable, exist even after seq function
    var time = 10 // the time variable/value is not used inside incr, not visible outside block, not a bound
    val incr = () => {
      val current = counter // visiblity, used in curried function
      counter += step
      current
    }
    incr // returning a function, curry function
  }


  {
    val seq1By1 = seq(1, 1)
    val seq100By10 = seq(100, 10)

    println("seq1By1", seq1By1())
    println("seq1By1", seq1By1())
    println("seq100By10", seq100By10())
    println("seq100By10", seq100By10())
    println("seq1By1", seq1By1())
  } // end of the block, seq100By10, seq1By1 garbage collected,
    // then counter bound variable also released from memory

}
