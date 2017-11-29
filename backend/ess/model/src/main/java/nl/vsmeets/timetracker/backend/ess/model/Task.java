/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The task.
 *
 * @author s230984
 */
public interface Task {

	/**
	 * Get the assignments that belong to this task.
	 *
	 * @return The assignments.
	 */
	@NotNull
	@Valid
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
	@NotNull
	String getName();

	/**
	 * Get the PSP element.
	 *
	 * @return The PSP element.
	 */
	@NotNull
	@Valid
	PspElement getPspElement();
}
