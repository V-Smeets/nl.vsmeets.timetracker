/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import nl.vsmeets.timetracker.backend.ess.model.Assignment;
import nl.vsmeets.timetracker.backend.ess.model.impl.validation.ValidAssignment;

/**
 * The assignment.
 *
 * @author s230984
 */
@ValidAssignment
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
	private final Set<EntryImpl> entries;

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
	public AssignmentImpl(final UserImpl user, final TaskImpl task, final LocalDate startDate, final LocalDate endDate,
			final Set<EntryImpl> entries) {
		super();
		final Set<AssignmentImpl> userAssignments = new CopyOnWriteArraySet<>(user.getAssignments());
		userAssignments.add(this);
		this.user = new UserImpl(user.getName(), userAssignments, user.getDays());
		final Set<AssignmentImpl> taskAssignments = new CopyOnWriteArraySet<>(task.getAssignments());
		taskAssignments.add(this);
		this.task = new TaskImpl(task.getPspElement(), task.getName(), task.getDescription(), taskAssignments);
		this.startDate = startDate;
		this.endDate = endDate;
		this.entries = Collections.unmodifiableSet(new CopyOnWriteArraySet<>(entries));
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
	public TaskImpl getTask() {
		return task;
	}

	@Override
	public UserImpl getUser() {
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
