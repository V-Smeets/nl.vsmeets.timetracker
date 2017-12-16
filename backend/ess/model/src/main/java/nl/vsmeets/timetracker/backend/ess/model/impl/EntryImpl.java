/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.Duration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

import nl.vsmeets.timetracker.backend.ess.model.Assignment;
import nl.vsmeets.timetracker.backend.ess.model.Day;
import nl.vsmeets.timetracker.backend.ess.model.Entry;

/**
 * The implementation of an entry.
 *
 * @author s230984
 */
public class EntryImpl implements Entry {

	/**
	 * The assignment.
	 */
	@NotNull
	@Valid
	private final Assignment assignment;

	/**
	 * The day.
	 */
	@NotNull
	@Valid
	private final Day day;

	/**
	 * The duration.
	 */
	@DurationMin
	@DurationMax(days = 1, inclusive = false)
	private Duration duration = null;

	/**
	 * The comment.
	 */
	private String comment = null;

	/**
	 * Create a new entry.
	 *
	 * @param day
	 *            The day.
	 * @param assignment
	 *            The assignment.
	 */
	public EntryImpl(final Day day, final Assignment assignment) {
		super();
		this.day = day;
		this.assignment = assignment;
		if (this.day instanceof DayImpl) {
			final DayImpl dayImpl = (DayImpl) this.day;
			dayImpl.getEntries().add(this);
		}
		if (this.assignment instanceof AssignmentImpl) {
			final AssignmentImpl assignmentImpl = (AssignmentImpl) this.assignment;
			assignmentImpl.getEntries().add(this);
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
		if (!(obj instanceof EntryImpl)) {
			return false;
		}
		final EntryImpl other = (EntryImpl) obj;
		if (assignment == null) {
			if (other.assignment != null) {
				return false;
			}
		} else if (!assignment.equals(other.assignment)) {
			return false;
		}
		if (day == null) {
			if (other.day != null) {
				return false;
			}
		} else if (!day.equals(other.day)) {
			return false;
		}
		return true;
	}

	@Override
	public Assignment getAssignment() {
		return assignment;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public Day getDay() {
		return day;
	}

	@Override
	public Duration getDuration() {
		return duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (assignment == null ? 0 : assignment.hashCode());
		result = prime * result + (day == null ? 0 : day.hashCode());
		return result;
	}

	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}

	@Override
	public void setDuration(final Duration duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return String.format("EntryImpl [duration=%s, comment=%s]", duration, comment);
	}

}
