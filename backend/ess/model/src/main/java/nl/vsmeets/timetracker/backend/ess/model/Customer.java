/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

/**
 * The customer.
 *
 * @author s230984
 */
public interface Customer {

	/**
	 * Get the name of the customer.
	 *
	 * @return The name of the customer. (not null)
	 */
	String getName();

	/**
	 * Get the projects that belong to the customer.
	 *
	 * @return The projects. (not null)
	 */
	Set<? extends Project> getProjects();

}
