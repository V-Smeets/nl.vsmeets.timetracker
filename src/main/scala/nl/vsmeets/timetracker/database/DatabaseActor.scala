package nl.vsmeets.timetracker.database

import java.util.concurrent.atomic.AtomicInteger

import com.typesafe.config.Config

import akka.actor.Actor
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.event.LoggingReceive

object DatabaseActor {

  val configName = "database"
  private val counter = new AtomicInteger(0)
  def apply(parentConfig: Config)(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[DatabaseActor], parentConfig.getConfig(configName)),
      configName + "-" + counter.incrementAndGet())

}

private class DatabaseActor(config: Config) extends Actor {

  val dataSourceRef = DataSourceActor(config)

  def receive = LoggingReceive {
    case _ => ()
  }

}
