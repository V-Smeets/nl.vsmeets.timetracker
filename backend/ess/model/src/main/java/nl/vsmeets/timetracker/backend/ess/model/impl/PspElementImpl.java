/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import nl.vsmeets.timetracker.backend.ess.model.Project;
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
	@NotNull
	@Valid
	private final ProjectImpl project;

	/**
	 * The name.
	 */
	@NotNull
	private final String name;

	/**
	 * The description.
	 */
	private final String description;

	/**
	 * The tasks.
	 */
	@NotNull
	@Valid
	private final Set<TaskImpl> tasks = new HashSet<>();

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
	public PspElementImpl(@NotNull @Valid final ProjectImpl project, @NotNull final String name,
			final String description) {
		super();
		this.project = project;
		this.name = name;
		this.description = description;
		this.project.getPspElements().add(this);
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
	public Project getProject() {
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
