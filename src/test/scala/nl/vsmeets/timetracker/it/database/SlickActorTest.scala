package nl.vsmeets.timetracker.it.database

import akka.actor.actorRef2Scala
import nl.vsmeets.timetracker.ActorTest
import nl.vsmeets.timetracker.database.SlickActor
import nl.vsmeets.timetracker.database.SlickActor.DatabaseResponse
import nl.vsmeets.timetracker.tags.IntegrationTest

@IntegrationTest
class SlickActorTest extends ActorTest {
  import SlickActor.DatabaseRequest

  private class Fixture {
    lazy val slickRef = SlickActor.createRef
  }

  private def fixture = new Fixture

  "The slick actor" should "return the database to the requestor" in {
    val f = fixture
    f.slickRef ! DatabaseRequest
    val databaseResponse = expectMsgClass(classOf[DatabaseResponse])
  }

  it should "return the database to all the requests" in {
    val f = fixture
    val range = 1 to 10
    range foreach { _ => f.slickRef ! DatabaseRequest }
    range foreach { _ => expectMsgClass(classOf[DatabaseResponse]) }
  }

}