/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import nl.vsmeets.timetracker.backend.ess.model.User;

/**
 * The implementation of a user.
 *
 * @author s230984
 */
public class UserImpl implements User {

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The assignments.
	 */
	private final Set<AssignmentImpl> assignments;

	/**
	 * The days.
	 */
	private final Set<DayImpl> days;

	/**
	 * Create a user.
	 *
	 * @param name
	 *            The name.
	 */
	public UserImpl(final String name, final Set<AssignmentImpl> assignments, final Set<DayImpl> days) {
		super();
		this.name = name;
		this.assignments = Collections.unmodifiableSet(new CopyOnWriteArraySet<>(assignments));
		this.days = Collections.unmodifiableSet(new CopyOnWriteArraySet<>(days));
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserImpl)) {
			return false;
		}
		final UserImpl other = (UserImpl) obj;
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
	public Set<AssignmentImpl> getAssignments() {
		return assignments;
	}

	@Override
	public Set<DayImpl> getDays() {
		return days;
	}

	@Override
	public String getName() {
		return name;
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
		return String.format("UserImpl [name=%s]", name);
	}

}
