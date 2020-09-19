package workshop.example

import scala.collection.mutable
import scala.util.control.Breaks._
import scala.collection.mutable._

object ThreadExample extends  App {
  // Process, Working memory/instruction
  // thread  - execution context, execute the instruction
  // context - switching, share the CPUs amoung thread
  // low level programming

  val queue: mutable.Queue[Int]= new mutable.Queue

  // worker thread, additional threads
  class Producer extends  Thread {
    override def run(): Unit = {
      println("Thread start")
      var i = 0

      breakable {
        while (true) {
          println("Producer Thread ..." + Thread.currentThread().getId)
          println("I is ", i)

          queue.enqueue(i)

          i += 1;
          if (i == 100000000) {
            break
          }
          // demo only, not for production, don't Sleep
          Thread.sleep(1)
        }
      }

      println("Thread end")
    }
  }

  class Consumer extends  Runnable {
    def run = {
      println("Consumer thread start")
      while (true) {
        if (!queue.isEmpty) {
          val item = queue.dequeue()
          println("Deque " + item + " Items in Queue " + queue.length)
        } else {
          println("Consumer waiting....")
          Thread.sleep(500)
        }
      }
    }
  }


  // Main application runs on main thread
  println("Main thread " + Thread.currentThread().getId)
  val producerThread = new Producer
  producerThread.start() // call run method on separate thread

  val consumerThread = new Thread(new Consumer)
  consumerThread.start() // start consumer thread

}
