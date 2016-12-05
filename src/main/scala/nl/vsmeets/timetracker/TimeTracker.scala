package nl.vsmeets.timetracker

import akka.actor.ActorSystem
import nl.vsmeets.timetracker.database.DatabaseActor
import nl.vsmeets.timetracker.logic.LogicActor

object TimeTracker extends App {
  implicit val system = ActorSystem("TimeTracker")

  val databaseRef = DatabaseActor.createRef
  val logicRef = LogicActor.createRef(databaseRef)
}
