package nl.vsmeets.timetracker

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Finders
import org.scalatest.FlatSpecLike

import akka.actor.ActorSystem
import akka.testkit.DefaultTimeout
import akka.testkit.ImplicitSender
import akka.testkit.TestKit

abstract class ActorTest
    extends TestKit(ActorSystem())
    with FlatSpecLike
    with BeforeAndAfterAll
    with ImplicitSender
    with DefaultTimeout {

  override def afterAll() = shutdown()

}
