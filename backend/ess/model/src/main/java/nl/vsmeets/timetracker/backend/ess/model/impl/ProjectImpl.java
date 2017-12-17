/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.util.HashSet;
import java.util.Set;

import nl.vsmeets.timetracker.backend.ess.model.Customer;
import nl.vsmeets.timetracker.backend.ess.model.Project;

/**
 * The implementation of a project.
 *
 * @author s230984
 */
public class ProjectImpl implements Project {

	/**
	 * The customer.
	 */
	private final Customer customer;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The PSP elements.
	 */
	private final Set<PspElementImpl> pspElements = new HashSet<>();

	/**
	 * Create a new project.
	 *
	 * @param customer
	 *            The customer.
	 * @param name
	 *            The name.
	 */
	public ProjectImpl(final Customer customer, final String name) {
		super();
		this.customer = customer;
		this.name = name;
		if (this.customer instanceof CustomerImpl) {
			final CustomerImpl customerImpl = (CustomerImpl) this.customer;
			customerImpl.getProjects().add(this);
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProjectImpl)) {
			return false;
		}
		final ProjectImpl other = (ProjectImpl) obj;
		if (customer == null) {
			if (other.customer != null) {
				return false;
			}
		} else if (!customer.equals(other.customer)) {
			return false;
		}
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
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Set<PspElementImpl> getPspElements() {
		return pspElements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (customer == null ? 0 : customer.hashCode());
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("ProjectImpl [name=%s]", name);
	}

}
