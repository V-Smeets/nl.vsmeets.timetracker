package nl.vsmeets.timetracker.userinterface

import com.typesafe.config.Config

import akka.actor.ActorRef
import akka.actor.ActorRefFactory
import scalafx.application.JFXApp.PrimaryStage

object UserInterface {

  val configName = "user-interface"
  def apply(parentConfig: Config, logicRef: ActorRef)(implicit context: ActorRefFactory) = new PrimaryStage {
    val config = parentConfig.getConfig(configName)

    title = config.getString("title")
  }

}