package nl.vsmeets.timetracker

import akka.actor.ActorSystem

object TimeTracker extends App {
  val system = ActorSystem("TimeTracker")
}
