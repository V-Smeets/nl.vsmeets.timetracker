package nl.vsmeets.timetracker.userinterface

import com.typesafe.config.Config

import akka.actor.ActorRef
import akka.actor.ActorRefFactory
import scalafx.application.JFXApp.PrimaryStage

object UserInterface {

  def apply(parentConfig: Config, logicRef: ActorRef)(implicit context: ActorRefFactory) = new PrimaryStage {
    val config = parentConfig.getConfig("user-interface")
    
    title = config.getString("title")
  }

}