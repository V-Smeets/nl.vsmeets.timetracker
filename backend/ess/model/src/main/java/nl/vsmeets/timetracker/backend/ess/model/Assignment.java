/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.time.LocalDate;
import java.util.Set;

/**
 * The assignment
 *
 * @author s230984
 */
public interface Assignment {

	/**
	 * Get the end date.
	 *
	 * @return The end date.
	 */
	LocalDate getEndDate();

	/**
	 * Get the entries that belong to this assignment.
	 *
	 * @return The entries. The result can be empty.
	 */
	Set<? extends Entry> getEntries();

	/**
	 * Get the start date.
	 *
	 * @return The start date.
	 */
	LocalDate getStartDate();

	/**
	 * Get the task.
	 *
	 * @return The task.
	 */
	Task getTask();

	/**
	 * Get the user.
	 *
	 * @return The user.
	 */
	User getUser();

}
