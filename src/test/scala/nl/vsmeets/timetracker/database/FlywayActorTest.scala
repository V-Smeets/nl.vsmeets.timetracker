package nl.vsmeets.timetracker.database

import org.h2.jdbcx.JdbcDataSource

import com.typesafe.config.ConfigFactory

import akka.actor.ActorRef
import akka.actor.actorRef2Scala
import akka.testkit.TestActor
import akka.testkit.TestActor.AutoPilot
import akka.testkit.TestProbe
import javax.sql.DataSource
import nl.vsmeets.timetracker.ActorTest
import nl.vsmeets.timetracker.database.FlywayActor.Flyway

class FlywayActorTest extends ActorTest {
  import DataSourceActor.{ DataSourceRequest, DataSourceResponse }
  import FlywayActor.{ Migrate, Migrated }

  class TestFlyway extends Flyway {
    def setDataSource(dataSource: DataSource): Unit = {}
    def migrate(): Int = 0
  }

  def fixture = new {
    lazy val dataSourceConfigPath = "database.data-source"
    lazy val dataSource = TestProbe("DataSourceProbe")
    lazy val dataSourceAutoPilot = new TestActor.AutoPilot {
      def run(sender: ActorRef, msg: Any): AutoPilot = msg match {
        case DataSourceRequest =>
          val conf = ConfigFactory.load()
          val dataSourceConf = conf.getConfig(dataSourceConfigPath)
          val ds = new JdbcDataSource()
          ds.setURL(dataSourceConf.getString("url"))
          ds.setUser(dataSourceConf.getString("user"))
          ds.setPassword(dataSourceConf.getString("password"))
          val response = DataSourceResponse(ds)
          sender ! response
          keepRunning
      }
    }
    lazy val flyway = new TestFlyway()
    lazy val flywayRef = FlywayActor.createRef(dataSource.ref, flyway)
  }

  "The FlywayActor" should "request a data source" in {
    val f = fixture
    f.flywayRef ! Migrate
    f.dataSource expectMsg DataSourceRequest
  }

  it should "return Migrated after getting the data source" in {
    val f = fixture
    f.dataSource setAutoPilot f.dataSourceAutoPilot
    f.flywayRef ! Migrate
    expectMsg(Migrated)
  }

  it should "return Migrated to all the requests" in {
    val f = fixture
    f.dataSource setAutoPilot f.dataSourceAutoPilot
    val range = 1 to 10
    range foreach { _ => f.flywayRef ! Migrate }
    range foreach { _ => expectMsg(Migrated) }
  }

}