/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import nl.vsmeets.timetracker.backend.ess.model.Assignment;
import nl.vsmeets.timetracker.backend.ess.model.Task;
import nl.vsmeets.timetracker.backend.ess.model.User;

/**
 * The assignment.
 *
 * @author s230984
 */
public class AssignmentImpl implements Assignment {

	/**
	 * The user.
	 */
	private final UserImpl user;

	/**
	 * The task.
	 */
	private final TaskImpl task;

	/**
	 * The start date.
	 */
	private final LocalDate startDate;

	/**
	 * The end date.
	 */
	private final LocalDate endDate;

	/**
	 * The entries.
	 */
	private final Set<EntryImpl> entries = new HashSet<>();

	/**
	 * Create a new assignment.
	 *
	 * @param user
	 *            The user.
	 * @param task
	 *            The task.
	 * @param startDate
	 *            The start date.
	 * @param endDate
	 *            The end date.
	 */
	public AssignmentImpl(final UserImpl user, final TaskImpl task, final LocalDate startDate,
			final LocalDate endDate) {
		super();
		this.user = Objects.requireNonNull(user, "user");
		this.user.getAssignments().add(this);
		this.task = Objects.requireNonNull(task, "task");
		this.task.getAssignments().add(this);
		this.startDate = Objects.requireNonNull(startDate, "startDate");
		this.endDate = Objects.requireNonNull(endDate, "endDate");
		if (startDate.isAfter(endDate)) {
			throw new IllegalArgumentException("startDate > endDate");
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
		if (!(obj instanceof AssignmentImpl)) {
			return false;
		}
		final AssignmentImpl other = (AssignmentImpl) obj;
		if (task == null) {
			if (other.task != null) {
				return false;
			}
		} else if (!task.equals(other.task)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public LocalDate getEndDate() {
		return endDate;
	}

	@Override
	public Set<EntryImpl> getEntries() {
		return entries;
	}

	@Override
	public LocalDate getStartDate() {
		return startDate;
	}

	@Override
	public Task getTask() {
		return task;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (task == null ? 0 : task.hashCode());
		result = prime * result + (user == null ? 0 : user.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("AssignmentImpl [startDate=%s, endDate=%s]", startDate, endDate);
	}

}
