/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

/**
 * The user.
 *
 * @author s230984
 */
public interface User {

	/**
	 * Get the assignments that belong to this user.
	 *
	 * @return The assignments. The result can be empty.
	 */
	Set<? extends Assignment> getAssignments();

	/**
	 * Get the days that belong to this user.
	 *
	 * @return The days. The result can be empty.
	 */
	Set<? extends Day> getDays();

	/**
	 * Get the name.
	 *
	 * @return The name.
	 */
	String getName();

}
