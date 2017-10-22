package nl.vsmeets.timetracker.database.schema

import slick.driver.H2Driver.api._
import java.sql.Timestamp
import java.sql.Date

/**
 * The database schema of TimeTracker.
 */
object TimeTracker {

  /**
   * The countries.
   */
  case class Country(id: Long, code: String)
  class CountryTable(tag: Tag) extends Table[Country](tag, "COUNTRY") {
    /** The generated primary key */
    def id = column[Long]("COUNTY_ID", O.PrimaryKey, O.AutoInc)
    /** The ISO 3166-1 2 letter country code */
    def code = column[String]("CODE")
    def * = (id, code) <> (Country.tupled, Country.unapply)
  }
  val countries = TableQuery[CountryTable]

  /**
   * The projects.
   */
  case class Project(id: Long, countryId: Long, code: String, name: Option[String])
  class ProjectTable(tag: Tag) extends Table[Project](tag, "PROJECT") {
    /** The generated primary key */
    def id = column[Long]("PROJECT_ID", O.PrimaryKey, O.AutoInc)
    /** The foreign key to the country of this project. */
    def countryId = column[Long]("COUNTRY_ID")
    def country = foreignKey("PROJECT_FK_COUNTRY", countryId, countries)(_.id)
    /** The 6 character project code */
    def code = column[String]("CODE")
    /** The full name of the project */
    def name = column[Option[String]]("NAME")
    def * = (id, countryId, code, name) <> (Project.tupled, Project.unapply)
  }
  val projects = TableQuery[ProjectTable]

  /**
   * The PSPs.
   */
  case class PSP(id: Long, projectId: Long, code: String, name: Option[String])
  class PSPTable(tag: Tag) extends Table[PSP](tag, "PSP") {
    /** The generated primary key */
    def id = column[Long]("PSP_ID", O.PrimaryKey, O.AutoInc)
    /** The foreign key to the project */
    def projectId = column[Long]("PROJECT_ID")
    def project = foreignKey("PSP_FK_PROJECT", projectId, projects)(_.id)
    /** The 3 character PSP code */
    def code = column[String]("CODE")
    /** The full name of the PSP */
    def name = column[Option[String]]("NAME")
    def * = (id, projectId, code, name) <> (PSP.tupled, PSP.unapply)
  }
  val psps = TableQuery[PSPTable]

  /**
   * The users.
   */
  case class User(id: Long, name: String)
  class UserTable(tag: Tag) extends Table[User](tag, "USER") {
    /** The generated primary key */
    def id = column[Long]("USER_ID", O.PrimaryKey, O.AutoInc)
    /** The full name of the user */
    def name = column[String]("NAME")
    def * = (id, name) <> (User.tupled, User.unapply)
  }
  val users = TableQuery[UserTable]

  /**
   * The PSP assignments.
   */
  case class PSPAssignment(id: Long, pspId: Long, userId: Long, startDate: Date, endDate: Date)
  class PSPAssignmentTable(tag: Tag) extends Table[PSPAssignment](tag, "PSP_ASSIGNMENT") {
    /** The generated primary key */
    def id = column[Long]("PSP_ASSIGNMENT_ID", O.PrimaryKey, O.AutoInc)
    /** The foreign key to the PSP */
    def pspId = column[Long]("PSP_ID")
    def psp = foreignKey("PSP_ASSIGNMENT_FK_PSP", pspId, psps)(_.id)
    /** The foreign key to the user */
    def userId = column[Long]("USER_ID")
    def user = foreignKey("PSP_ASSIGNMENT_FK_USER", userId, users)(_.id)
    /** The first date of the assignment (inclusive) */
    def startDate = column[Date]("START_DATE")
    /** The last date of the assignment (inclusive) */
    def endDate = column[Date]("END_DATE")
    def * = (id, pspId, userId, startDate, endDate) <> (PSPAssignment.tupled, PSPAssignment.unapply)
  }
  val pspAssignments = TableQuery[PSPAssignmentTable]

  /**
   * The time entries.
   */
  case class TimeEntry(id: Long, pspAssignmentId: Long, date: Date, duration: Int, comment: Option[String])
  class TimeEntryTable(tag: Tag) extends Table[TimeEntry](tag, "TIME_ENTRY") {
    /** The generated primary key */
    def id = column[Long]("TIME_ENTRY_ID", O.PrimaryKey, O.AutoInc)
    /** The foreign key to the PSP assignment */
    def pspAssignmentId = column[Long]("PSP_ASSIGNMENT_ID")
    def pspAssignment = foreignKey("TIME_ENTRY_FK_PSP_ASSIGNMENT", pspAssignmentId, pspAssignments)(_.id)
    /** The date of this entry */
    def date = column[Date]("DATE")
    /** The duration in seconds */
    def duration = column[Int]("DURATION")
    /** The comment of this entry */
    def comment = column[Option[String]]("COMMENT")
    def * = (id, pspAssignmentId, date, duration, comment) <> (TimeEntry.tupled, TimeEntry.unapply)
  }
  val timeEntries = TableQuery[TimeEntryTable]

  /**
   * The tasks.
   */
  sealed trait Task { val id: Long; val name: String }
  case class ToplevelTask(id: Long, name: String, pspAssignmentId: Long) extends Task
  case class SublevelTask(id: Long, name: String, parentTaskId: Long) extends Task
  type TaskData = (Long, String, Option[Long], Option[Long])
  def taskConstructor: TaskData => Task = {
    case (id, name, Some(pspAssignmentId), None) => ToplevelTask(id, name, pspAssignmentId)
    case (id, name, None, Some(parentTaskId)) => SublevelTask(id, name, parentTaskId)
    case (id, name, pspAssignmentId, parentTaskId) =>
      throw new IllegalArgumentException(s"pspAssignmentId: $pspAssignmentId, parentTaskId: $parentTaskId")
  }
  def taskExtractor: PartialFunction[Task, TaskData] = {
    case ToplevelTask(id, name, pspAssignmentId) => (id, name, Some(pspAssignmentId), None)
    case SublevelTask(id, name, parentTaskId) => (id, name, None, Some(parentTaskId))
  }
  class TaskTable(tag: Tag) extends Table[Task](tag, "TASK") {
    /** The generated primary key */
    def id = column[Long]("TASK_ID", O.PrimaryKey, O.AutoInc)
    /** The name of this task */
    def name = column[String]("NAME")
    /** The foreign key to the PSP assignment. NULL in case this is a sub task */
    def pspAssignmentId = column[Option[Long]]("PSP_ASSIGNMENT_ID")
    def pspAssignment = foreignKey("TASK_FK_PSP_ASSIGNMENT", pspAssignmentId, pspAssignments)(_.id.?)
    /** The foreign key to the parent task. NULL in case its a root task */
    def parentTaskId = column[Option[Long]]("PARENT_TASK_ID")
    def parentTask = foreignKey("TASK_FK_TASK", parentTaskId, tasks)(_.id.?)
    def * = (id, name, pspAssignmentId, parentTaskId) <> (taskConstructor, taskExtractor.lift)
  }
  val tasks = TableQuery[TaskTable]

  /**
   * The periods.
   */
  case class Period(id: Long, taskId: Long, startTime: Timestamp, endTime: Timestamp)
  class PeriodTable(tag: Tag) extends Table[Period](tag, "PERIOD") {
    /** The generated primary key */
    def id = column[Long]("PERIOD_ID", O.PrimaryKey, O.AutoInc)
    /** The foreign key to the task */
    def taskId = column[Long]("TASK_ID")
    def task = foreignKey("PERIOD_FK_TASK", taskId, tasks)(_.id)
    /** The start time. (inclusive) */
    def startTime = column[Timestamp]("START_TIME")
    /** The end time. (exclusive) */
    def endTime = column[Timestamp]("END_TIME")
    def * = (id, taskId, startTime, endTime) <> (Period.tupled, Period.unapply)
  }
  val periods = TableQuery[PeriodTable]

}