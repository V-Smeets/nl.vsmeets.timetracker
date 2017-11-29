/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The project.
 *
 * @author s230984
 */
public interface Project {

	/**
	 * Get the customer.
	 *
	 * @return The customer.
	 */
	@NotNull
	@Valid
	Customer getCustomer();

	/**
	 * Get the name.
	 *
	 * @return The name.
	 */
	@NotNull
	String getName();

	/**
	 * Get the PSP elements that belong to this project.
	 *
	 * @return The PSP elements.
	 */
	@NotNull
	@Valid
	Set<? extends PspElement> getPspElements();

}
