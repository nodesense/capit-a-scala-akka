package workshop.example

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object Cluster2Example  extends  App {

  class FrontendActor extends  Actor {
    val cluster = Cluster(context.system)

    println("FrontendActor Actor path" ,
      akka.serialization.Serialization.serializedActorPath(self))


    val cancellable =
      context.system.scheduler.schedule(5.seconds, 2.seconds, self, "DoWork")


    // Now have a list of backends actor reference
    var backends = IndexedSeq.empty[ActorRef]

    override def receive: Receive = {
      case msg: String if msg == "DoWork" => {
        println("DoWork msg, forwarding to backend worker")
        println("Total backend workers avaiable " + backends.length)

        if (backends.length > 0) {
          backends(0) ! "DoWork1"
        }
      }

        // this message sent by backend worker to register where are they are
      case msg: String if msg == "RegisterMe" => {
          println("Registerme by backend received")
          backends  = backends :+ sender()
      }

      case _ => println("FrontEnd Actor")
    }
  }

    val config = ConfigFactory.parseString(
      s"""  """.stripMargin)
    .withFallback(ConfigFactory.load("cluster2"))

  implicit  val system = ActorSystem("ClusterSystem", config)
  val cluster = Cluster(system)

  system.actorOf(Props(classOf[FrontendActor]),  "frontend")

  cluster.registerOnMemberUp {
  }
}
