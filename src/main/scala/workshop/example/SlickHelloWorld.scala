package workshop.example

import slick.driver.H2Driver.api._
import workshop.models.Invoice
import workshop.tables.InvoiceTable

import scala.concurrent.ExecutionContext.Implicits.global



object SlickHelloWorld extends  App {

  class Suppliers(tag: Tag) extends Table[(Int, String, String)](tag, "SUPPLIERS") {
    def id = column[Int]("SUP_ID", O.PrimaryKey) // This is the primary key column
    def name = column[String]("SUP_NAME")
    def state = column[String]("STATE")

    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name,   state)
  }

  val suppliers = TableQuery[Suppliers]

  val setup = DBIO.seq(
    // Create the tables, including primary and foreign keys
    (suppliers.schema).create,

    // Insert some suppliers
    suppliers += (101, "Acme, Inc.",       "CA" ),
    suppliers += ( 49, "Superior Coffee",      "CA" ),
    suppliers += (150, "The High Ground",       "CA"),
    // Equivalent SQL code:
    // insert into SUPPLIERS(SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP) values (?,?,?,?,?,?)

  )

  val db = Database.forConfig("h2mem1")
  try {
    val conn = db.source.createConnection()
    val st = conn.createStatement();
    val result = st.execute("select 100")
    val resultSet = st.getResultSet()
    println("ResultSet " + resultSet.first())

    val count = resultSet.getInt(1)
    println("Count " + count)

    val setupFuture = db.run(setup)
    println("Created")
    db.run(suppliers.result).map(_.foreach {
      case (id, state, name) =>
        println("  " + name + "\t" + id + "\t" + state + "\t" + name + "\t" )
    })


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


    val setupFuture2 = db.run(setup2)
    println("Created")
    db.run(invoices.result).map(_.foreach {
      case invoice: Invoice =>
        println( "\t" + invoice.id + "\t" + invoice.customerId + "\t" + invoice.amount + "\t" )
    })
  } finally db.close

}
