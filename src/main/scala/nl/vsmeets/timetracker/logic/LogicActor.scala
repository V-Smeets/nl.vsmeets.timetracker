package nl.vsmeets.timetracker.logic

import java.util.concurrent.atomic.AtomicInteger

import com.typesafe.config.Config

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.event.LoggingReceive

object LogicActor {

  val configName = "logic"
  private val counter = new AtomicInteger(0)
  def apply(parentConfig: Config, databaseRef: ActorRef)(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[LogicActor], parentConfig.getConfig(configName), databaseRef),
      configName + "-" + counter.incrementAndGet())

}

private class LogicActor(config: Config, databaseRef: ActorRef) extends Actor {

  def receive = LoggingReceive {
    case _ => ()
  }

}
