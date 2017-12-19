/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
	private final AssignmentImpl assignment;

	/**
	 * The day.
	 */
	private final DayImpl day;

	/**
	 * The duration.
	 */
	private final Duration duration;

	/**
	 * The comment.
	 */
	private final String comment;

	/**
	 * Create a new entry.
	 *
	 * @param day
	 *            The day.
	 * @param assignment
	 *            The assignment.
	 */
	public EntryImpl(final DayImpl day, final AssignmentImpl assignment, final Duration duration,
			final String comment) {
		super();
		final Set<EntryImpl> assignmentEntities = new CopyOnWriteArraySet<>(assignment.getEntries());
		assignmentEntities.add(this);
		this.assignment = new AssignmentImpl(assignment.getUser(), assignment.getTask(), assignment.getStartDate(),
				assignment.getEndDate(), assignmentEntities);
		final Set<EntryImpl> dayEntities = new CopyOnWriteArraySet<>(day.getEntries());
		dayEntities.add(this);
		this.day = new DayImpl(day.getUser(), day.getDate(), day.getStartTime1(), day.getEndTime1(),
				day.getStartTime2(), day.getEndTime2(), day.getTravelDuration(), dayEntities);
		this.duration = duration;
		this.comment = comment;
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
	public AssignmentImpl getAssignment() {
		return assignment;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public DayImpl getDay() {
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
	public String toString() {
		return String.format("EntryImpl [duration=%s, comment=%s]", duration, comment);
	}

	@Override
	public EntryImpl withComment(final String comment) {
		return new EntryImpl(day, assignment, duration, comment);
	}

	@Override
	public EntryImpl withDuration(final Duration duration) {
		return new EntryImpl(day, assignment, duration, comment);
	}

}
