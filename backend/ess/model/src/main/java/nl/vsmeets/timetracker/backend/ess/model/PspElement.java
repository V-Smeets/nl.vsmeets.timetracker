/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	 * @return The name.
	 */
	@NotNull
	String getName();

	/**
	 * Get the project.
	 *
	 * @return The project.
	 */
	@NotNull
	@Valid
	Project getProject();

	/**
	 * Get the tasks that belong to this PSP element.
	 *
	 * @return The tasks.
	 */
	@NotNull
	@Valid
	Set<? extends Task> getTasks();

}
