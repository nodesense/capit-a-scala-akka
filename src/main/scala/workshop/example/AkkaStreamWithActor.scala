package workshop.example

import java.nio.file.Paths

import akka.{NotUsed, stream}
import akka.actor.{Actor, ActorSystem, Props}
import workshop.models.Order
import akka.stream.scaladsl._
import akka.util.{ByteString, Timeout}
import workshop.example.StreamExample.{fileSink, sumOfNumbers}
import akka.pattern.ask
import scala.concurrent.duration._

import scala.concurrent.Future;

object AkkaStreamWithActor  extends  App {

  class OrderProcessingActor extends  Actor {

    override def receive: Receive =  {
      case order: Order => {
        println("Send by Stream ", order)
        // calculation and response
        sender() ! Order(order.id, order.customerId, order.amount * .75)
      }
    }

  }

  implicit val system = ActorSystem("example")
  implicit val ec = system.dispatcher

  val orderActor = system.actorOf(Props[OrderProcessingActor], "orders")

  val orders: Source[Order, NotUsed] = Source (
     Order(1, 100, 1000) ::
      Order(2, 200, 2000) ::
      Order(3, 300, 3000) :: Nil
  )
  orders.runForeach(println(_))
  implicit val timeout = Timeout(5.seconds)

  val discounted = orders
      .map( order => Order (order.id, order.customerId, order.amount * .9))
      .mapAsync(1) ( order => orderActor ? order)

  val fileSink = FileIO.toPath(Paths.get("orders.txt")) // project root director
  val result: Future[stream.IOResult] = discounted.
    map(order => ByteString(s"${order.toString}n\r\n")) // convert to string format
    .runWith(fileSink) // write to file
}
