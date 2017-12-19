/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import nl.vsmeets.timetracker.backend.ess.model.Task;

/**
 * The implementation of the task.
 *
 * @author s230984
 */
public class TaskImpl implements Task {

	/**
	 * The PSP element.
	 */
	private final PspElementImpl pspElement;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The description.
	 */
	private final String description;

	/**
	 * The assignments.
	 */
	private final Set<AssignmentImpl> assignments;

	/**
	 * Create a new task.
	 *
	 * @param pspElement
	 *            The PSP element.
	 * @param name
	 *            The name.
	 * @param description
	 *            The description.
	 */
	public TaskImpl(final PspElementImpl pspElement, final String name, final String description,
			final Set<AssignmentImpl> assignments) {
		super();
		final Set<TaskImpl> pspElementTasks = new CopyOnWriteArraySet<>(pspElement.getTasks());
		pspElementTasks.add(this);
		this.pspElement = new PspElementImpl(pspElement.getProject(), pspElement.getName(), pspElement.getDescription(),
				pspElementTasks);
		this.name = name;
		this.description = description;
		this.assignments = Collections.unmodifiableSet(new CopyOnWriteArraySet<>(assignments));
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TaskImpl)) {
			return false;
		}
		final TaskImpl other = (TaskImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pspElement == null) {
			if (other.pspElement != null) {
				return false;
			}
		} else if (!pspElement.equals(other.pspElement)) {
			return false;
		}
		return true;
	}

	@Override
	public Set<AssignmentImpl> getAssignments() {
		return assignments;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public PspElementImpl getPspElement() {
		return pspElement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		result = prime * result + (pspElement == null ? 0 : pspElement.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("TaskImpl [name=%s, description=%s]", name, description);
	}

}
