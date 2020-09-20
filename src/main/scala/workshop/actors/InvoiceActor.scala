package workshop.actors

import akka.actor.Actor
import workshop.models.{Invoice}
import workshop.repositories.InvoiceRepository

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

// Requests
// single instance
case object GetAllInvoices
// specific instances
case class GetInvoice(id: Int)
case class CreateInvoice(invoice: Invoice)
case class UpdateInvoice(invoice: Invoice)
case class DeleteInvoice(id: Int)

// response
// case class GetAllInvoicesResponse(invoices: Invoices)
case class GetAllInvoicesResponse(invoices: Seq[Invoice])

case class GetInvoiceResponse(invoice: Invoice)

class InvoiceActor  extends  Actor {
  println("InvoiceActor created")

  // fails means, actor instance also will fail
  // good place is preStart(), even before starting Actor System
  InvoiceRepository.setup() // overkill

  override def receive: Receive = {
    case GetAllInvoices => {
      val future = InvoiceRepository.list()
      val senderRef = sender()
      future.onComplete {
        case Success(value) => {
          println("Got invoices " + value)
            //ask call, need to respond
          senderRef.tell(GetAllInvoicesResponse(value), self)
        }
        case Failure(exception) => {
           println("exception ", exception)
          //TODO: respond to actor
        }
      }
    }
    case GetInvoice(id) => {
      val senderRef = sender()
      InvoiceRepository.findById(id)
        .map ( invoice => {
          println("Got invoice ", invoice)
          senderRef.tell( GetInvoiceResponse(invoice) , self)
        }) // Success
        .recover {
          case noEle: NoSuchElementException => sender().tell("Wrong", self)
          case _ => sender().tell("Unknown error", self)
        }
    }

    case UpdateInvoice(invoice) => {
      println("update invoice", invoice)
    }

    case invoice: Invoice => {}
      // @ need the message and also using extractor
    case deleteInvoice@DeleteInvoice(id)  => {}
    case _ => {}
  }
}
