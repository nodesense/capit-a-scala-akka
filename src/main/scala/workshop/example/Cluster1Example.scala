package workshop.example

import akka.actor.{Actor, ActorSystem, Props, RootActorPath}
import akka.cluster.ClusterEvent.{InitialStateAsEvents, MemberDowned, MemberEvent, MemberRemoved, MemberUp, UnreachableMember}
import akka.cluster.{Cluster, Member, MemberStatus}
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext.Implicits.global

object Cluster1Example  extends  App {

  // backend work, the one which does the actual job
  class BackendActor extends  Actor {
    val cluster = Cluster(context.system)

    // subscribe for events
    override def preStart(): Unit = {
      super.preStart()

      cluster.subscribe(self, initialStateMode = InitialStateAsEvents,
        classOf[MemberEvent],
        classOf[UnreachableMember],
        classOf[MemberUp],
        classOf[MemberDowned],
        classOf[MemberRemoved]
      )
    }

    override def receive: Receive = {
      // cluster member, node1[printer], node2[emailer], node2[emailer], node3[order],.... ....
      case MemberUp(m) => {
        println("Memberup ",  m, "roles",  m.roles)
        // TODO: self register with front end actor,
        if (m.hasRole("frontend")) {
          // self register with backend, i am here, worker is here in this cluster
          println("Root actor path", RootActorPath(m.address))
          // akka://clustersystem@127.0.01/user/frontend
          context.actorSelection(RootActorPath(m.address) / "user" / "frontend" ) ! "RegisterMe"
        }
      }

      case UnreachableMember(m) => {
        println("UnreachableMember ",  m)
      }

      case MemberRemoved(member, previousStatus) =>
         println(s"Member is Removed: ${member.address} after ${previousStatus}")

      case MemberDowned(m) => {
        println("Member Down", m)
      }

      case msg: String if msg == "DoWork1" => {
        println("received do work 1 from frondend")
      }

    }


  }

  val config = ConfigFactory.parseString(
      s"""  """.stripMargin).withFallback(ConfigFactory.load("cluster1"))

  implicit  val system = ActorSystem("ClusterSystem", config)
  val cluster = Cluster(system)

  // called once the cluster is up
  cluster.registerOnMemberUp {
    // register actors
    system.actorOf(Props[BackendActor], name="backend")
  }

}
