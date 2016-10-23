package nl.vsmeets.timetracker.database

import java.util.concurrent.atomic.AtomicInteger

import scala.annotation.implicitNotFound

import org.h2.jdbcx.JdbcDataSource

import com.typesafe.config.ConfigFactory

import akka.actor.Actor
import akka.actor.ActorRefFactory
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.LoggingReceive

object DataSourceActor {
  sealed trait Inbound
  case object DataSourceRequest extends Inbound

  sealed trait Outbound
  case class DataSourceResponse(dataSource: JdbcDataSource) extends Outbound

  private val counter = new AtomicInteger(0)
  def createRef(path: String)(implicit context: ActorRefFactory) =
    context.actorOf(
      Props(classOf[DataSourceActor], path),
      "DataSource-" + counter.incrementAndGet())
}

private class DataSourceActor(path: String) extends Actor {
  import DataSourceActor.DataSourceRequest
  import DataSourceActor.DataSourceResponse

  def receive = LoggingReceive {
    case DataSourceRequest =>
      val conf = ConfigFactory.load()
      val dataSourceConf = conf.getConfig(path)
      val ds = new JdbcDataSource()
      ds.setURL(dataSourceConf.getString("url"))
      ds.setUser(dataSourceConf.getString("user"))
      ds.setPassword(dataSourceConf.getString("password"))
      val response = DataSourceResponse(ds)
      sender ! response
      context become sendDataSource(response)
  }

  def sendDataSource(response: DataSourceResponse): Receive = LoggingReceive {
    case DataSourceRequest =>
      sender ! response
  }

}