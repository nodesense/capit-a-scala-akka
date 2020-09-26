package workshop.actors

import akka.actor.{Actor, ActorRef, Terminated}
import workshop.models.{BackendRegistration, JobFailed, TransformationJob}

class TransformationFrontend extends Actor {

  var backends = IndexedSeq.empty[ActorRef]
  var jobCounter = 0

  def receive = {
    case job: TransformationJob if backends.isEmpty => {
      println("no backend available")
      sender() ! JobFailed("Service unavailable, try again later", job)
    }

    case job: TransformationJob => {
      println("received job")
      jobCounter += 1
      backends(jobCounter % backends.size).forward(job)
    }

    case BackendRegistration if !backends.contains(sender()) => {
      println("Backend registered")
      context.watch(sender())
      backends = backends :+ sender()
    }

    case Terminated(a) =>
      backends = backends.filterNot(_ == a)
  }
}
