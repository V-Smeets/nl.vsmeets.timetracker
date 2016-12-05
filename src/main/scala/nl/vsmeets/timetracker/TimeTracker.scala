package nl.vsmeets.timetracker

import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import nl.vsmeets.timetracker.database.DatabaseActor
import nl.vsmeets.timetracker.logic.LogicActor
import nl.vsmeets.timetracker.userinterface.UserInterface
import scalafx.application.JFXApp

object TimeTracker extends JFXApp {
  implicit val system = ActorSystem("TimeTracker")
  val config = ConfigFactory.load()

  val databaseRef = DatabaseActor(config)
  val logicRef = LogicActor(config, databaseRef)
  stage = UserInterface(config, logicRef)

  override def stopApp() {
    system.terminate()
  }
}
