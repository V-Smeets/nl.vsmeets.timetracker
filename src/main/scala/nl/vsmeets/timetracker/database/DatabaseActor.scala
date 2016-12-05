package nl.vsmeets.timetracker.database

import java.util.concurrent.atomic.AtomicInteger

import com.typesafe.config.Config

import akka.actor.Actor
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.event.LoggingReceive

object DatabaseActor {

  private val counter = new AtomicInteger(0)
  def apply(parentConfig: Config)(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[DatabaseActor], parentConfig.getConfig("database")),
      "Database-" + counter.incrementAndGet())

}

private class DatabaseActor(config: Config) extends Actor {

  def receive = LoggingReceive {
    case _ => ()
  }

}
