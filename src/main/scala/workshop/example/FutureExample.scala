package workshop.example


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}

//ExecutionContext - Java, Core JDK
// Abstraction over Low level threads
// Task, Task Queue,  Thread Pool, Execution

// create task, submit to task queue : Output is a Future object
// execution context, pull tasks from queue, execute them using worker thread
// not a dedicated thread, instead pool of reserved threads
// caller/who submit the task, will have future handle, wait for the result
object FutureExample extends  App {
  val future1 = Future {
    // This is placeholder for the task
    // this code executed by random thread
    println("Future Start")
    println("Thread id", Thread.currentThread().getId)
    // return the output after waitign 5 seconds
    Thread.sleep(5000) // for demo only
    1 + 1
  }

  println("Main Thread", Thread.currentThread().getId)

  // get output
  // blocking call/wait, block the caller thread, not recommended
  // bad idea to block a thread while waiting for data
  println("Waiting")
  val result = Await.result(future1, 6.second)
  println("Result ", result)

}
