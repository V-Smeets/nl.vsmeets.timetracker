package nl.vsmeets.timetracker.database

import java.util.concurrent.atomic.AtomicInteger

import scala.annotation.implicitNotFound
import scala.concurrent.Future
import scala.concurrent.Promise

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.LoggingReceive
import akka.pattern.pipe
import javax.sql.DataSource
import nl.vsmeets.timetracker.database.FlywayActor.Flyway

object FlywayActor {
  trait Flyway {
    def setDataSource(dataSource: DataSource): Unit
    def migrate(): Int
  }

  sealed trait Inbound
  case object Migrate extends Inbound

  sealed trait Outbound
  case object Migrated extends Outbound

  private val counter = new AtomicInteger(0)
  def createRef(dataSourceRef: ActorRef, flyway: Flyway)(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[FlywayActor], dataSourceRef, flyway),
      "Flyway-" + counter.incrementAndGet())

}

private class FlywayActor(dataSourceRef: ActorRef, flyway: Flyway) extends Actor {
  import DataSourceActor.{ DataSourceRequest, DataSourceResponse }
  import FlywayActor.{ Migrate, Migrated, Outbound }
  import context.dispatcher

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
      flyway.setDataSource(dataSource)
      val f = Future { flyway.migrate() } map { _ => Migrated }
      p completeWith f
  }

}