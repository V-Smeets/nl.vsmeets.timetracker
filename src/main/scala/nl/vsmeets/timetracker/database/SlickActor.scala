package nl.vsmeets.timetracker.database

import java.util.concurrent.atomic.AtomicInteger

import scala.annotation.implicitNotFound
import scala.concurrent.Promise

import akka.actor.Actor
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.LoggingReceive
import akka.pattern.pipe
import slick.driver.H2Driver.api.Database

object SlickActor {
  sealed trait Inbound
  case object DatabaseRequest extends Inbound

  sealed trait Outbound
  case class DatabaseResponse(database: Database) extends Outbound

  private val counter = new AtomicInteger(0)
  def createRef(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[SlickActor]),
      "Slick-" + counter.incrementAndGet())
}

private class SlickActor extends Actor {
  import FlywayActor.{ Migrate, Migrated }
  import SlickActor.{ DatabaseRequest, DatabaseResponse, Outbound }
  import context.dispatcher

  val flywayRef = FlywayActor.createRef

  def receive = LoggingReceive {
    case DatabaseRequest =>
      val currentSender = sender()
      val p = Promise[Outbound]
      p.future pipeTo currentSender
      flywayRef ! Migrate
      context become waitingForMigrated(p)
  }

  def waitingForMigrated(p: Promise[Outbound]): Receive = LoggingReceive {
    case DatabaseRequest =>
      val currentSender = sender()
      p.future pipeTo currentSender
    case Migrated(dataSource) =>
      val database = Database.forDataSource(dataSource)
      p.success(DatabaseResponse(database))
  }

}