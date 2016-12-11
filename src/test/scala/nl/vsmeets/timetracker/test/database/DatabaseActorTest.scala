package nl.vsmeets.timetracker.test.database

import nl.vsmeets.timetracker.ActorTest
import nl.vsmeets.timetracker.database.DatabaseActor
import com.typesafe.config.ConfigFactory

class DatabaseActorTest extends ActorTest {

  private class Fixture {
    lazy val config = ConfigFactory.load()
  }

  private def fixture = new Fixture

  "A Database actor" can "be created" in {
    val f = fixture
    DatabaseActor(f.config)
  }

}