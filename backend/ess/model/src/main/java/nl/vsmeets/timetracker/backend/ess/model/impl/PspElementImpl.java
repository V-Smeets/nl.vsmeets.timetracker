/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import nl.vsmeets.timetracker.backend.ess.model.PspElement;

/**
 * The implementation of the PSP element.
 *
 * @author s230984
 */
public class PspElementImpl implements PspElement {

	/**
	 * The project.
	 */
	private final ProjectImpl project;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The description.
	 */
	private final String description;

	/**
	 * The tasks.
	 */
	private final Set<TaskImpl> tasks;

	/**
	 * Create a new instance.
	 *
	 * @param project
	 *            The project.
	 * @param name
	 *            The name.
	 * @param description
	 *            The description.
	 */
	public PspElementImpl(final ProjectImpl project, final String name, final String description,
			final Set<TaskImpl> tasks) {
		super();
		final Set<PspElementImpl> projectPspElements = new CopyOnWriteArraySet<>(project.getPspElements());
		projectPspElements.add(this);
		this.project = new ProjectImpl(project.getCustomer(), project.getName(), projectPspElements);
		this.name = name;
		this.description = description;
		this.tasks = Collections.unmodifiableSet(new CopyOnWriteArraySet<>(tasks));
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PspElementImpl)) {
			return false;
		}
		final PspElementImpl other = (PspElementImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (project == null) {
			if (other.project != null) {
				return false;
			}
		} else if (!project.equals(other.project)) {
			return false;
		}
		return true;
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
	public ProjectImpl getProject() {
		return project;
	}

	@Override
	public Set<TaskImpl> getTasks() {
		return tasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (name == null ? 0 : name.hashCode());
		result = prime * result + (project == null ? 0 : project.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format("PspElementImpl [name=%s, description=%s]", name, description);
	}

}
