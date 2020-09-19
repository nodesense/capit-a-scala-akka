package workshop.dao

import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.H2Driver.api._
import slick.sql.{FixedSqlStreamingAction, SqlAction}

import scala.concurrent.Future

trait Config {

  val db = Database.forConfig("h2mem1")
  implicit val session: Session = db.createSession()


  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    val future = db.run(action)
    future.onComplete { case _ => session.close() }

    future
  }

  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    val future = db.run(action)
    future.onComplete { case _ => session.close() }

    future
  }

}
