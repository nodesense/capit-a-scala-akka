package workshop.dao

import workshop.tables.InvoiceTable
import slick.driver.H2Driver.api._
import workshop.models.{Invoice}

import scala.concurrent.Future
object InvoiceDao extends  Config {
  val invoices = TableQuery[InvoiceTable]

  def list(): Future[Seq[Product]] = db.run {
    invoices.result
  }

  def findAll() = invoices.result

  def findById(orderId: Int)= invoices.filter(_.id === orderId).result.head
  def create(invoice: Invoice)  = invoices returning invoices.map(_.id) += invoice
  def update(newInvoice: Invoice, invoiceId: Option[Int]) = invoices.filter(_.id === invoiceId)
    .map(invoice => (invoice.id, invoice.customerId, invoice.amount))
    .update((newInvoice.id, newInvoice.customerId,   newInvoice.amount))

  def delete(orderId: Int)= invoices.filter(_.id === orderId).delete

}
