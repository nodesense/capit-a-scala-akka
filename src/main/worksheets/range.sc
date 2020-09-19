// doesn't return value
for (x <- 1 to 10 by 2) {
  println(x)
}

val output = (1 to 10).foreach(println)

println("Output", output)

// for yield, making for as more expression
// now for is an expression, return output
val output2 = for (x <- 1 to 10 by 2) yield x

val output3 = for { x <- 1 to 10 by 2 } yield x

// guarded if statement

val output4 = for (i <- 1 to 10 if i % 2 == 0) yield i

println(output4)
