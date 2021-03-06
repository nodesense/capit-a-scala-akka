package workshop.tables

import slick.driver.H2Driver.api._
import workshop.models.Invoice

import scala.concurrent.ExecutionContext.Implicits.global

class InvoiceTable(tag: Tag) extends Table[Invoice](tag, "invoices") {
  def id = column[Int]("id",  O.PrimaryKey)
  def customerId = column[Int]("customerId")
  def amount = column[Double]("amount")

  // projection operator, helps us to pull data from the db, convert to Model class
  def * = (id, customerId, amount) <> ((Invoice.apply _).tupled, Invoice.unapply)
}
