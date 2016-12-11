package nl.vsmeets.timetracker.test.database

import scala.language.reflectiveCalls
import nl.vsmeets.timetracker.ActorTest
import nl.vsmeets.timetracker.database.DataSourceActor
import nl.vsmeets.timetracker.tags.IntegrationTest
import com.typesafe.config.ConfigFactory
import nl.vsmeets.timetracker.database.DatabaseActor
import nl.vsmeets.timetracker.database.DataSourceActor.DataSourceRequest
import org.h2.jdbcx.JdbcDataSource

class DataSourceActorTest extends ActorTest {

  private class Fixture {
    lazy val config = ConfigFactory.load()
    lazy val databaseConfig = config.getConfig(DatabaseActor.configName)
    lazy val dataSourceConfig = databaseConfig.getConfig(DataSourceActor.configName)
    lazy val dataSourceRef = DataSourceActor(databaseConfig)
  }

  private def fixture = new Fixture

  "A DataSource actor" should "return the configured data source" in {
    val f = fixture
    f.dataSourceRef ! DataSourceRequest
    val dataSource = expectMsgClass(classOf[JdbcDataSource])
    system.log.info("DataSource: {}", dataSource)
    assert(dataSource.getURL == f.dataSourceConfig.getString("url"))
    assert(dataSource.getUser == f.dataSourceConfig.getString("user"))
    assert(dataSource.getPassword == f.dataSourceConfig.getString("password"))
  }

  it should "return the same data source every time" in {
    val f = fixture
    f.dataSourceRef ! DataSourceRequest
    val response1 = expectMsgClass(classOf[JdbcDataSource])
    f.dataSourceRef ! DataSourceRequest
    val response2 = expectMsgClass(classOf[JdbcDataSource])
    assume(response1 == response2)
  }

}
