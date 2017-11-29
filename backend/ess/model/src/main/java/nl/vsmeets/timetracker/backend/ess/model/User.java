/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The user.
 *
 * @author s230984
 */
public interface User {

	/**
	 * Get the assignments that belong to this user.
	 *
	 * @return The assignments.
	 */
	@NotNull
	@Valid
	Set<? extends Assignment> getAssignments();

	/**
	 * Get the days that belong to this user.
	 *
	 * @return The days.
	 */
	@NotNull
	@Valid
	Set<? extends Day> getDays();

	/**
	 * Get the name.
	 *
	 * @return The name.
	 */
	@NotNull
	String getName();

}
