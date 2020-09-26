package workshop.example

import akka.actor.{Actor, ActorPath, ActorSystem, Props}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.{MemberDowned, MemberUp}
import akka.cluster.client.ClusterClient.Send
import akka.cluster.client.{ClusterClient, ClusterClientSettings}
import com.typesafe.config.ConfigFactory
import workshop.actors.TransformationFrontend
import workshop.models.{Email, TransformationJob}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.duration._

object Cluster20Example extends  App {

  class BackendActor extends  Actor {

    val Tick = "tick"

    // val cluster = Cluster(context.system)

    val cancellable =
      context.system.scheduler.schedule(5.seconds, 3.seconds, self, Tick)


    override def receive: Receive = {
      case MemberUp(m) => {
        println("Memberup ",  m)
      }

      case MemberDowned(m) => {
        println("Member Down", m)
      }

      case msg: String if msg == "tick" => {
        println("Scheduling message")
        val prefix = ""
        system.actorSelection(s"$prefix/user/frontend") ! TransformationJob("Job 1")

      }
    }
  }

  val config = ConfigFactory.parseString(s"""
      """
  ).
    withFallback(ConfigFactory.load("cluster-2"))


  implicit val system = ActorSystem("ClusterSystem", config)
  val cluster = Cluster(system)

  val backendActor = system.actorOf(Props[BackendActor], "backend")

  Cluster(system) registerOnMemberUp {
    system.actorOf(Props(classOf[TransformationFrontend]), name = "frontend")
  }

  val f = Future {
    Thread.sleep(10000)
    println("Sedning messages");


   // val prefix = "akka://ClusterSystem@127.0.0.1:2552"
    val prefix = ""
//
//    val initialContacts = Set(
//      ActorPath.fromString(s"$prefix/system/receptionist"))
//    val settings = ClusterClientSettings(system).withInitialContacts(initialContacts)
//
//    val c = system.actorOf(
//      ClusterClient.props(ClusterClientSettings(system).withInitialContacts(initialContacts)),
//      "client")
//
//     c ! ClusterClient.Send(path = "/user/emailService", msg = Email("admin@example.com", s"Message 1", "Now all run well"), localAffinity = true)


//    system.actorSelection(s"$prefix/user/emailService") ! Send(path = "/user/emailService", msg = Email("admin@example.com", s"Message 1", "Now all run well"), localAffinity = true)
//
//    system.actorSelection(s"$prefix/user/emailService") ! Email("admin@example.com", s"Message 1", "Now all run well")
////    system.actorSelection(s"$prefix/user/emailService") ! Email("admin@example.com", s"Message 2", "Now all run well")
//    system.actorSelection(s"$prefix/user/emailService") ! Email("admin@example.com", s"Message 3", "Now all run well")
//    system.actorSelection(s"$prefix/user/emailService") ! Email("admin@example.com", s"Message 4", "Now all run well")

    system.actorSelection(s"$prefix/user/transformationFrontend") ! TransformationJob("Job 1")

  }


  f.onComplete {
    case Success(value) => println(s"Got the callback, meaning = $value")
    case Failure(e) => println("Error "); e.printStackTrace
  }
}
