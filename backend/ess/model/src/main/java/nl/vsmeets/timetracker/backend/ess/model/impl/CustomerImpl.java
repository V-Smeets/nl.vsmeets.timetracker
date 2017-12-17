/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.util.HashSet;
import java.util.Set;

import nl.vsmeets.timetracker.backend.ess.model.Customer;

/**
 * The implementation of a customer.
 *
 * @author s230984
 */
public class CustomerImpl implements Customer {

	// TODO Make all classes immutable.

	/**
	 * The name of the customer.
	 */
	private final String name;

	/**
	 * The projects of this customer.
	 */
	private final Set<ProjectImpl> projects = new HashSet<>();

	/**
	 * Create a new customer.
	 *
	 * @param name
	 *            The name of the customer.
	 */
	public CustomerImpl(final String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CustomerImpl)) {
			return false;
		}
		final CustomerImpl other = (CustomerImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Set<ProjectImpl> getProjects() {
		return projects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("CustomerImpl [name=%s]", name);
	}

}
