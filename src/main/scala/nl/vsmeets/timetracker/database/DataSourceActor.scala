package nl.vsmeets.timetracker.database

import java.util.concurrent.atomic.AtomicInteger

import org.h2.jdbcx.JdbcDataSource

import com.typesafe.config.ConfigFactory

import akka.actor.Actor
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.LoggingReceive
import com.typesafe.config.Config

object DataSourceActor {

  /**
   * Request a JDBC data source.
   * @akka.request DataSourceRequest
   * @akka.response JdbcDataSource
   */
  case object DataSourceRequest

  val configName = "data-source"
  private val counter = new AtomicInteger(0)
  def apply(parentConfig: Config)(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[DataSourceActor], parentConfig.getConfig(configName)),
      configName + "-" + counter.incrementAndGet())

}

private class DataSourceActor(config: Config) extends Actor {
  import DataSourceActor.DataSourceRequest

  def receive = LoggingReceive {
    case DataSourceRequest =>
      val dataSource = new JdbcDataSource()
      dataSource.setURL(config.getString("url"))
      dataSource.setUser(config.getString("user"))
      dataSource.setPassword(config.getString("password"))
      sender ! dataSource
      context become sendDataSource(dataSource)
  }

  def sendDataSource(dataSource: JdbcDataSource): Receive = LoggingReceive {
    case DataSourceRequest =>
      sender ! dataSource
  }

}