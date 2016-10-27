package nl.vsmeets.timetracker

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.LoggingReceive
import nl.vsmeets.timetracker.database.DataSourceActor
import nl.vsmeets.timetracker.database.FlywayActor
import nl.vsmeets.timetracker.database.FlywayActor.Flyway
import nl.vsmeets.timetracker.database.FlywayActor.Migrate
import nl.vsmeets.timetracker.database.FlywayActor.Migrated

object TimeTracker extends App {
  implicit val system = ActorSystem("TimeTracker")

  class MainActor extends Actor {
    def receive = LoggingReceive {
      case Migrated =>
        system.terminate()
    }
  }
  val mainRef = system.actorOf(Props[MainActor], "Main")

  val dataSourceRef = DataSourceActor.createRef("database.data-source")
  val flywayRef = FlywayActor.createRef(dataSourceRef, new org.flywaydb.core.Flyway with Flyway)
  flywayRef.tell(Migrate, mainRef)

}
