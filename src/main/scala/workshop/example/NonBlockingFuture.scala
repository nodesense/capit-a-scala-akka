package workshop.example


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future}
import scala.util.{Failure, Success}

object NonBlockingFuture extends  App {
  val future1 = Future {
    // This is placeholder for the task
    // this code executed by random thread
    println("Future Start")
    println("Thread id", Thread.currentThread().getId)
    // return the output after waitign 5 seconds
    Thread.sleep(5000) // for demo only
    1 + 1
  }

  future1.onComplete {
    // after 5 seconds
    case Success(value) => println("Task completed " + value)
    case Failure(exception) => println("Error "); exception.printStackTrace()
  }

  println("Waiting.....")
  Thread.sleep(100) //
  Thread.sleep(10000)

}
