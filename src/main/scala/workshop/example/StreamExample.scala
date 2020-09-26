package workshop.example

import java.nio.file.Paths

import akka.{NotUsed, stream}
import akka.actor.ActorSystem
import akka.stream.IOResult
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.Future
import scala.concurrent.duration._

object StreamExample extends  App {
    implicit val system = ActorSystem("example")
    implicit val ec = system.dispatcher
  // Source, as set of numbers
  val source: Source[Int, NotUsed] = Source(1 to 5)

  // Processing, sum of numbers
  val sumOfNumbers = source.scan(BigInt(0)) ( (acc, nextValue) => acc + nextValue)

  // sink, an output of type Sink[T1, T]
  val fileSink = FileIO.toPath(Paths.get("sum.txt")) // project root director

  // connect all together as topology - materializing stream/executing the stream

  sumOfNumbers.runForeach( i => println(i))

  val result: Future[stream.IOResult] = sumOfNumbers.
    map(n => ByteString(s"$n\r\n")) // convert to string format
    .runWith(fileSink) // write to file

  result.onComplete(
    _ => {
      println("Stream work complete")
      system.terminate(); // example
    }
  )

}
