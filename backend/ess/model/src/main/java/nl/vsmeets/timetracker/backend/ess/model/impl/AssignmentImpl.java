/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import nl.vsmeets.timetracker.backend.ess.model.Assignment;
import nl.vsmeets.timetracker.backend.ess.model.Task;
import nl.vsmeets.timetracker.backend.ess.model.User;
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
	@NotNull
	@Valid
	private final User user;

	/**
	 * The task.
	 */
	@NotNull
	@Valid
	private final Task task;

	/**
	 * The start date.
	 */
	@NotNull
	private final LocalDate startDate;

	/**
	 * The end date.
	 */
	@NotNull
	private final LocalDate endDate;

	/**
	 * The entries.
	 */
	@NotNull
	@Valid
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
	public AssignmentImpl(@NotNull @Valid final User user, @NotNull @Valid final Task task,
			@NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
		super();
		this.user = user;
		this.task = task;
		this.startDate = startDate;
		this.endDate = endDate;
		if (this.user instanceof UserImpl) {
			final UserImpl userImpl = (UserImpl) this.user;
			userImpl.getAssignments().add(this);
		}
		if (this.task instanceof TaskImpl) {
			final TaskImpl taskImpl = (TaskImpl) this.task;
			taskImpl.getAssignments().add(this);
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
