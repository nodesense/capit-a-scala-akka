package workshop.example

import slick.driver.H2Driver.api._
import workshop.dao.InvoiceDao
import workshop.models.Invoice
import workshop.tables.InvoiceTable

import scala.concurrent.ExecutionContext.Implicits.global

object InvoiceDaoTest extends  App {

  val invoices = TableQuery[InvoiceTable]



  val setup2 = DBIO.seq(
    // Create the tables, including primary and foreign keys
    (invoices.schema).create,

    // Insert some suppliers
    invoices += Invoice(100,   100, 10000.0 ),
    invoices += Invoice(101,  101, 25000.0 ),
    invoices += Invoice(102, 102,     5600000.0),
    // Equivalent SQL code:
    // insert into invoices(ID, CUSTOMER_ID, AMOUNT) values (?,?,?)
  )
  //val db = Database.forConfig("h2mem1")

  try {

  val conn = InvoiceDao.db.source.createConnection()

  val setupFuture2 = InvoiceDao.db.run(setup2)
  println("Created")
    InvoiceDao.db.run(invoices.result).map(_.foreach {
    case invoice: Invoice =>
      println( "\t" + invoice.id + "\t" + invoice.customerId + "\t" + invoice.amount + "\t" )
  })

    InvoiceDao.list().map(_.foreach( {
      case invoice: Invoice =>
        println( "Second\t" + invoice.id + "\t" + invoice.customerId + "\t" + invoice.amount + "\t" )
    }))

  } finally InvoiceDao.db.close

}
