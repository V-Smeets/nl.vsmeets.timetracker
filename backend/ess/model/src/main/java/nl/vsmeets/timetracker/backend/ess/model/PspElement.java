/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

/**
 * The PSP element.
 *
 * @author s230984
 */
public interface PspElement {

	/**
	 * Get the description.
	 *
	 * @return The description.
	 */
	String getDescription();

	/**
	 * Get the name.
	 *
	 * @return The name. (not null)
	 */
	String getName();

	/**
	 * Get the project.
	 *
	 * @return The project. (not null)
	 */
	Project getProject();

	/**
	 * Get the tasks that belong to this PSP element.
	 *
	 * @return The tasks. (not null)
	 */
	Set<? extends Task> getTasks();

}
