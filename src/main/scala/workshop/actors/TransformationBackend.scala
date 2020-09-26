package workshop.actors

import akka.actor.{Actor, RootActorPath}
import akka.cluster.ClusterEvent.{CurrentClusterState, MemberUp}
import akka.cluster.{Cluster, Member, MemberStatus}
import workshop.models.{BackendRegistration, TransformationJob, TransformationResult}


class TransformationBackend extends Actor {

  val cluster = Cluster(context.system)

  // subscribe to cluster changes, MemberUp
  // re-subscribe when restart
  override def preStart(): Unit = cluster.subscribe(self, classOf[MemberUp])
  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive = {
    case TransformationJob(text) =>  {
      println("Received the Job ", text)
      sender() ! TransformationResult(text.toUpperCase)
    }
    case state: CurrentClusterState =>
      state.members.filter(_.status == MemberStatus.Up).foreach(register)
    case MemberUp(m) => register(m)
  }

  def register(member: Member): Unit =
    if (member.hasRole("frontend"))
    {
      println("registering backend with frontend")
      context.actorSelection(RootActorPath(member.address) / "user" / "frontend") !
        BackendRegistration
    }
}