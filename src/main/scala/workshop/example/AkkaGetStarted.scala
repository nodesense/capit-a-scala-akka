package workshop.example

import java.net.SocketException
import java.sql.SQLException

import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.pattern.ask
import akka.util.Timeout
import workshop.actors.{GetAllInvoices, GetAllInvoicesResponse, GetInvoice, GetInvoiceResponse, InvoiceActor, UpdateInvoice}
import workshop.models.Invoice

import scala.concurrent.duration._
// 2 second    [postfix operator, compilation flag/ import statement
import scala.language.postfixOps
import scala.concurrent.{Future, Await}
import scala.util.{Failure, Success}

import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpMethods.{GET}


import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._
  implicit val invoiceJsonFormat = jsonFormat3(Invoice)
  implicit val getAllInvoicesResponseFormat = jsonFormat1(GetAllInvoicesResponse)
  implicit  val getInvoiceFormat = jsonFormat1(GetInvoiceResponse)
}

object AkkaGetStarted extends  App with JsonSupport {

  class WorkerActor extends  Actor {
    println("WorkerActor created")

    // path? uri
    // path? uri
    println("WorkerActor Actor path" ,
      akka.serialization.Serialization.serializedActorPath(self))

    override def receive: Receive = {
      case "Work" => {
        println("WorkerActor Doing work...")
        // responding to forwarded message,
        // sender() is the original sender
        sender().tell("Work 1 Completed", self)
      }
    }
  }

  class SupervisedChildActor extends  Actor {
      println("SupervisedChildActor created")

    override def preStart(): Unit = {
      super.preStart() // good practice
      // called durign first time
      // init db/resources/memory/connection
      println("SupervisedChildActor:preStart")

      // after loading db/connecting to db, then create children
    }

    override def postRestart(reason: Throwable): Unit = {
      // reason is the exact exception caused the actor to restart
      super.postRestart(reason)
      println("SupervisedChildActor::postRestart ex " + reason)
    }

    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      super.preRestart(reason, message)
      // reason is the exact exception caused the actor to restart
      // message is nothing the one we received from mailbox that may caused exception
      // the message to be processed again, this is the right place
      println("SupervisedChildActor::preRestart message " + message)
    }


    override def postStop() = {
      // called after the actor stopped
      // uninitialize, disconnect
      println("SupervisedChildActor::postStop")
    }



      override def receive: Receive = {
        case msg: String if msg == "SQLException" => throw  new SQLException()
        case msg: String if msg == "ArithmeticException" => throw  new ArithmeticException()
        case msg: String if msg == "SocketException" => throw  new SocketException()
        case msg: String if msg == "OutOfMemoryError" => throw  new OutOfMemoryError()
        case _ => throw  new Exception
      }
    }

  class SupervisorActor extends  Actor {
    import akka.actor.OneForOneStrategy
    import akka.actor.SupervisorStrategy._

    val child1 = context.actorOf(Props[SupervisedChildActor], "child1")

    override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 5,
                                      withinTimeRange =  1 minute) {
      case _: SQLException => Restart
      case _:ArithmeticException => Resume
      case _: SocketException => Stop
      case _: OutOfMemoryError => Escalate // to the parent
    }

    override def receive: Receive = {
      case _ => println("Supervisor onReceive default")
    }
  }

  class HelloActor extends  Actor {
    println("HelloActor created")

    // path? uri
    println("Actor path" ,
      akka.serialization.Serialization.serializedActorPath(self))

   // create child actor for hello actor

    val workerActorRef = context.actorOf(Props[WorkerActor], "worker1")

    override def preStart(): Unit = {
      super.preStart() // good practice
      // called durign first time
      // init db/resources/memory/connection
      println("ChildActor:preStart")

      // after loading db/connecting to db, then create children
    }

    override def postStop() = {
      // called after the actor stopped
      // uninitialize, disconnect
      println("ChildActor::postStop")
    }

    // is a callback, whenever a message land in mail box
    // dispatcher calls the receive method
    // while calling receive, dispatcher pulls the message out of mail box
    // when message is received, but not processed, messgae may lost
    override def receive: Receive = {
      // example for ask, actor should reply to the sender
      // reply (tell) land in sender mailbox, Future
      case "what is your name?" => sender().tell("HelloActor", self)
      // msg == "Work"
      case msg:String if msg == "Work" => {
        println("Forwarding Work to worker actor")
        workerActorRef.forward(msg)
      }
      case msg:String => println("received message " + msg)
      case _ => println("Some message")
    }
  }

  // 1. creation of the actor system
  // single JVM process should have only one actor system
  // hierarchy of path/uri
  // akka://finapp
  // akka://finapp/user - user space
  // akka://finapp/system -- used internal purpose

  // system itself is an actor
  // akka http needs the system
  implicit  val system = ActorSystem("finapp")
  implicit  val timeout = Timeout(10 seconds) // postfix imports help

  val requestHandler: HttpRequest => HttpResponse = {
    case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
          HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,
                                           "<h2>Hello</h2>"))
  }

  val route = path("invoices-all") {
      get {
        val response = (invoiceActor ? GetAllInvoices).mapTo[GetAllInvoicesResponse]
        complete(response)
      }
  }

  // GET  /invoices
  // POST /inovoices

  // GET /invoices/1
  // PUT /invoices/1
  // DELETE /invoices/1

  // {id, customerId, amount}

  val invoicesRoute = pathPrefix("invoices") {
    concat(
      pathEnd {
        concat (
          get {
            val response = (invoiceActor ? GetAllInvoices).mapTo[GetAllInvoicesResponse]
            complete(response)
          },
          post {
            entity(as[Invoice]) {
              invoice =>
                println("REceived ", invoice)
                val response = (invoiceActor ? UpdateInvoice(invoice)).mapTo[GetInvoiceResponse]
                complete(StatusCodes.OK)

            }
            complete(StatusCodes.OK)
          }
        )
      },
      path(Segment) {
        id =>
          concat(
            get {
              println("REceived ", id)
              val response = (invoiceActor ? GetInvoice(id.toInt)).mapTo[GetInvoiceResponse]
              complete(response)
            },
            put {
              entity(as[Invoice]) {
                invoice =>
                  println("REceived ", invoice)
                  val response = (invoiceActor ? UpdateInvoice(invoice)).mapTo[GetInvoiceResponse]
                  complete(StatusCodes.OK)
              }
              complete(StatusCodes.OK)
            },
            delete {
              complete(StatusCodes.OK)
            }
          )
      }
    )
  }

  val homeRoute = path("") {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
        "<h2>Home</h2>"))
    }
  }

  //Http().bindAndHandleSync(requestHandler, "localhost", 8888)
  Http().bindAndHandle(homeRoute ~ route ~ invoicesRoute, "localhost", 8888)


  // 2. creating actor
  //   an actor creates another actor, a parent create child actors, to form hierarchy
  // .actorOf to instantiate another actor
  // system is parent, helloActor is a child
  // helloActor is not the instance of HelloActor class, not recommend to use instance
  val helloActor: ActorRef = system.actorOf(Props[HelloActor], name="hello")

  val supervisorActor = system.actorOf(Props[SupervisorActor], name="supervisor")

  val invoiceActor = system.actorOf(Props[InvoiceActor], name="invoice")

  // 3 ways to send message
  // tell - fire and forget
  // ask
  // forward

  // sender ref/ optional, null, self, noSender
  // FIXME: InternalActorRef

  // tell
  // the messgae Hello is placed in mail box
  helloActor.tell("Hello", null)

  // ! = tell
  helloActor ! "Good Morning"

  // ask, timeout, async, Future
  val future1 = ask(helloActor, "what is your name?")

  // blocking, not good
  val result = Await.result(future1, 5 second)
  println("response ", result)

  // ? - ask
  // non blocking call
  val future2 = helloActor ? "what is your name?"
  future2.onComplete( {
    case Success(value) => println("Async response " + value)
    case Failure(exception) => println("got exception " , exception)
  })

  // should be forwarded
  // A Ask -> B Forward  --> C
  // C tells -->  A as response
  // Response is not going via B

  helloActor ! "Work"

  val future3 = helloActor ? "Work"
  val result2 = Await.result(future3, 2 second)
  println("Result2", result2)

  // stop the actor, by posting PoisonPill to the mail box
  // the actor continue processing all the previous messages in order
  // once PoinsonPill received, the actor terminated
  // helloActor ! PoisonPill

  // if any message posted after the actor terminated?
  // message dead lettered ... not delivered
  helloActor ! "Work 2"


   // restart, child get killed here
   // system.actorSelection("/user/supervisor/child1") ! "SQLException"

  // resume
  // system.actorSelection("/user/supervisor/child1") ! "ArithmeticException"

  //Stop, child get killed here
  // system.actorSelection("/user/supervisor/child1") ! "SocketException"

  // entire system shutdown if not handled by parent
  // Escalate, goes to the top level system actors, shutdown or other actors can handle them well
  // system.actorSelection("/user/supervisor/child1") ! "OutOfMemoryError"

  // stop child actor
  // system.stop(helloActor)

  val invFuture = invoiceActor ? GetAllInvoices
  val invoiceResponse = Await.result(invFuture, 10 seconds)
  println("Invoices resp", invoiceResponse)

  val inv2 = invoiceActor ? GetInvoice(101)
  val invoiceResponse2 = Await.result(inv2, 10 seconds)
  println("Invoice resp", invoiceResponse2)

  // called while during services being stopped linux/ systemd, windows services
  scala.sys.addShutdownHook {
    println("Shutting down")
    val f = system.terminate()
    // onComplete may not get called, by the time the system shutdown
    f.onComplete( {
      case Success(value) => println("system shutdown")
      case Failure(exception) => println("Error , forceful shutdown") // process.exit(0)
    })

  }

}
