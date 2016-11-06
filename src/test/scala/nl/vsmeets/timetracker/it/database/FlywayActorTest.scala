package nl.vsmeets.timetracker.it.database

import akka.actor.actorRef2Scala
import nl.vsmeets.timetracker.ActorTest
import nl.vsmeets.timetracker.database.FlywayActor
import nl.vsmeets.timetracker.database.FlywayActor.Migrated
import nl.vsmeets.timetracker.tags.IntegrationTest

@IntegrationTest
class FlywayActorTest extends ActorTest {
  import FlywayActor.Migrate

  private class Fixture {
    lazy val flywayRef = FlywayActor.createRef
  }

  private def fixture = new Fixture

  "The FlywayActor" should "return Migrated to the requester" in {
    val f = fixture
    f.flywayRef ! Migrate
    val migrated = expectMsgClass(classOf[Migrated])
  }

  it should "return Migrated to all the requests" in {
    val f = fixture
    val range = 1 to 10
    range foreach { _ => f.flywayRef ! Migrate }
    range foreach { _ => expectMsgClass(classOf[Migrated]) }
  }

}
