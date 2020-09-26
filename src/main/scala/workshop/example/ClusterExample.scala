package workshop.example

import java.net.InetAddress

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props, RootActorPath}
import akka.cluster.{Cluster, Member, MemberStatus}
import akka.cluster.ClusterEvent.{CurrentClusterState, MemberDowned, MemberUp}
import akka.cluster.sharding.{ClusterSharding, ClusterShardingSettings, ShardRegion}
import com.typesafe.config.ConfigFactory
import workshop.actors.{ClusterListener, EmailServiceActor, TransformationBackend}
import workshop.models.{BackendRegistration, TransformationJob, TransformationResult}

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

  val config = ConfigFactory.parseString(s"""
      """
  ).
    withFallback(ConfigFactory.load("cluster-1"))


  implicit val system = ActorSystem("ClusterSystem", config)
  val cluster = Cluster(system)

  // val backendActor = system.actorOf(Props[BackendActor], "backend")

  Cluster(system) registerOnMemberUp {
    system.actorOf(Props[ClusterListener], name = "clusterListener")
    system.actorOf(Props[EmailServiceActor], name = "emailService")
    system.actorOf(Props(classOf[TransformationBackend]), name = "backend")
  }
}
