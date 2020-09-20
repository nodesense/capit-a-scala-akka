package workshop.repositories

import workshop.models.Invoice
import scala.concurrent.Future
import slick.driver.H2Driver.api._
import workshop.tables.InvoiceTable
import scala.concurrent.ExecutionContext.Implicits.global

// singleton
object  InvoiceRepository {

  val db = Database.forConfig("h2mem1")

  val invoices = TableQuery[InvoiceTable]

  def setup() = {

      val seed = DBIO.seq(
        (invoices.schema).create,
        invoices += Invoice(100,   100, 10000.0 ),
        invoices += Invoice(101,  101, 25000.0 ),
        invoices += Invoice(102, 102,     5600000.0),
      )

      val conn = db.source.createConnection()


      val setupFuture = db.run(seed)

    // print the data for debugging
//      db.run(invoices.result).map(_.foreach {
//        case invoice: Invoice =>
//          println( invoice )
//      })

  }

  def create(customerId: Int, amount: Double): Future[Invoice] = ???

  // get all records from db
  def list(): Future[Seq[Invoice]] = db.run {
    invoices.result // returns select * from invoices
  }

  def findById(id: Int): Future[Invoice] = db.run {
    invoices.filter(_.id === id).result.head
  }

  def delete(id: Int): Future[Int] = db.run {
    invoices.filter(_.id === id).delete
  }
}
