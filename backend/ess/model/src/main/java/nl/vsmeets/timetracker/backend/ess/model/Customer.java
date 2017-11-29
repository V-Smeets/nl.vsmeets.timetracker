/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The customer.
 *
 * @author s230984
 */
public interface Customer {

	/**
	 * Get the name of the customer.
	 *
	 * @return The name of the customer.
	 */
	@NotNull
	String getName();

	/**
	 * Get the projects that belong to the customer.
	 *
	 * @return The projects.
	 */
	@NotNull
	@Valid
	Set<? extends Project> getProjects();

}
