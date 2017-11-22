/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

/**
 * The task.
 *
 * @author s230984
 */
public interface Task {

	/**
	 * Get the assignments that belong to this task.
	 *
	 * @return The assignments. The result can be empty.
	 */
	Set<? extends Assignment> getAssignments();

	/**
	 * Get the description.
	 *
	 * @return The description.
	 */
	String getDescription();

	/**
	 * Get the name.
	 *
	 * @return The name.
	 */
	String getName();

	/**
	 * Get the PSP element.
	 *
	 * @return The PSP element.
	 */
	PspElement getPspElement();
}
