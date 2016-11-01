package nl.vsmeets.timetracker.database

import java.util.concurrent.atomic.AtomicInteger

import scala.annotation.implicitNotFound
import scala.concurrent.Future
import scala.concurrent.Promise

import org.flywaydb.core.Flyway
import org.h2.jdbcx.JdbcDataSource

import akka.actor.Actor
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.LoggingReceive
import akka.pattern.pipe

object FlywayActor {
  sealed trait Inbound
  case object Migrate extends Inbound

  sealed trait Outbound
  case class Migrated(dataSource: JdbcDataSource) extends Outbound

  private val counter = new AtomicInteger(0)
  def createRef(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[FlywayActor]),
      "Flyway-" + counter.incrementAndGet())
}

private class FlywayActor extends Actor {
  import DataSourceActor.{ DataSourceRequest, DataSourceResponse }
  import FlywayActor.{ Migrate, Migrated, Outbound }
  import context.dispatcher

  val dataSourceRef = DataSourceActor.createRef

  def receive = LoggingReceive {
    case Migrate =>
      val currentSender = sender()
      val p = Promise[Outbound]
      p.future pipeTo currentSender
      dataSourceRef ! DataSourceRequest
      context become waitingForDataSource(p)
  }

  def waitingForDataSource(p: Promise[Outbound]): Receive = LoggingReceive {
    case Migrate =>
      val currentSender = sender()
      p.future pipeTo currentSender
    case DataSourceResponse(dataSource) =>
      val flyway = new Flyway()
      flyway.setDataSource(dataSource)
      val f = Future { flyway.migrate() } map { _ => Migrated(dataSource) }
      p completeWith f
  }

}