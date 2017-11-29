/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	@NotNull
	LocalDate getEndDate();

	/**
	 * Get the entries that belong to this assignment.
	 *
	 * @return The entries.
	 */
	@NotNull
	@Valid
	Set<? extends Entry> getEntries();

	/**
	 * Get the start date.
	 *
	 * @return The start date.
	 */
	@NotNull
	LocalDate getStartDate();

	/**
	 * Get the task.
	 *
	 * @return The task.
	 */
	@NotNull
	@Valid
	Task getTask();

	/**
	 * Get the user.
	 *
	 * @return The user.
	 */
	@NotNull
	@Valid
	User getUser();

}
