package nl.vsmeets.timetracker

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.LoggingReceive
import nl.vsmeets.timetracker.database.DataSourceActor
import nl.vsmeets.timetracker.database.DataSourceActor.DataSourceRequest
import nl.vsmeets.timetracker.database.DataSourceActor.DataSourceResponse

object TimeTracker extends App {
  implicit val system = ActorSystem("TimeTracker")

  class MainActor extends Actor {
    def receive = LoggingReceive {
      case DataSourceResponse(ds) =>
        system.terminate()
    }
  }
  val mainRef = system.actorOf(Props[MainActor], "Main")

  val dataSourceRef = DataSourceActor.createRef("database.data-source")
  dataSourceRef.tell(DataSourceRequest, mainRef)

}
