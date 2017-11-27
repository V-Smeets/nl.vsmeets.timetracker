/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.Duration;

import nl.vsmeets.timetracker.backend.ess.model.Assignment;
import nl.vsmeets.timetracker.backend.ess.model.Day;
import nl.vsmeets.timetracker.backend.ess.model.Entry;

/**
 * @author s230984
 */
public class EntryImpl implements Entry {

	private final AssignmentImpl assignment = null;
	private final DayImpl day = null;
	private Duration duration = null;
	private String comment = null;

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
	public void setComment(final String comment) {
		this.comment = comment;
	}

	@Override
	public void setDuration(final Duration duration) {
		this.duration = duration;
	}

}
