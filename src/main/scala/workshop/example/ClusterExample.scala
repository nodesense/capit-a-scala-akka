package workshop.example

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.{MemberDowned, MemberUp}
import scala.concurrent.ExecutionContext.Implicits.global

object ClusterExample extends  App {

  class BackendActor extends  Actor {

   // val cluster = Cluster(context.system)

    override def receive: Receive = {
      case MemberUp(m) => {
        println("Memberup ",  m)
      }

      case MemberDowned(m) => {
        println("Member Down", m)
      }
    }
  }


  implicit val system = ActorSystem("ClusterSystem")
  val cluster = Cluster(system)

  val backendActor = system.actorOf(Props[BackendActor], "backend")

  // run the cluster on a system1, system2
  // system1:2551, system2:2552
}
