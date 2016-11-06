package nl.vsmeets.timetracker.it.database

import com.typesafe.config.ConfigFactory

import akka.actor.actorRef2Scala
import nl.vsmeets.timetracker.ActorTest
import nl.vsmeets.timetracker.database.DataSourceActor
import nl.vsmeets.timetracker.database.DataSourceActor.DataSourceRequest
import nl.vsmeets.timetracker.database.DataSourceActor.DataSourceResponse

class DataSourceActorTest extends ActorTest {

  def fixture = new {
    lazy val dataSourceRef = DataSourceActor.createRef
  }

  "A DataSource actor" should "return the configured data source" in {
    val f = fixture
    f.dataSourceRef ! DataSourceRequest
    val conf = ConfigFactory.load()
    val dataSourceConf = conf.getConfig("database.data-source")
    val DataSourceResponse(ds) = expectMsgClass(classOf[DataSourceResponse])
    system.log.info("DataSource: {}", ds)
    assert(ds.getURL == dataSourceConf.getString("url"))
    assert(ds.getUser == dataSourceConf.getString("user"))
    assert(ds.getPassword == dataSourceConf.getString("password"))
  }

  it should "return the same data source every time" in {
    val f = fixture
    f.dataSourceRef ! DataSourceRequest
    val response1 = expectMsgClass(classOf[DataSourceResponse])
    f.dataSourceRef ! DataSourceRequest
    val response2 = expectMsgClass(classOf[DataSourceResponse])
    assume(response1 == response2)
  }

}
