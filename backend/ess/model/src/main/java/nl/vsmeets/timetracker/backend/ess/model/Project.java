/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

/**
 * The project.
 *
 * @author s230984
 */
public interface Project {

	/**
	 * Get the customer.
	 *
	 * @return The customer. (not null)
	 */
	Customer getCustomer();

	/**
	 * Get the name.
	 *
	 * @return The name. (not null)
	 */
	String getName();

	/**
	 * Get the PSP elements that belong to this project.
	 *
	 * @return The PSP elements. (not null)
	 */
	Set<? extends PspElement> getPspElements();

}
