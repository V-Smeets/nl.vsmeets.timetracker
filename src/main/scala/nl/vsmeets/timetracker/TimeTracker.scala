package nl.vsmeets.timetracker

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.LoggingReceive
import nl.vsmeets.timetracker.database.SlickActor

object TimeTracker extends App {
  import SlickActor.DatabaseRequest
  import SlickActor.DatabaseResponse
  implicit val system = ActorSystem("TimeTracker")

  class MainActor extends Actor {
    def receive = LoggingReceive {
      case DatabaseResponse(_) =>
        system.terminate()
    }
  }
  val mainRef = system.actorOf(Props[MainActor], "Main")

  val slickRef = SlickActor.createRef
  slickRef.tell(DatabaseRequest, mainRef)

}
