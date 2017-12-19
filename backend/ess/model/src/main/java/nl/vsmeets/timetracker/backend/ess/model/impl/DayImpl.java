/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import nl.vsmeets.timetracker.backend.ess.model.Day;
import nl.vsmeets.timetracker.backend.ess.model.impl.validation.ValidDay;

/**
 * The implementation of a day.
 *
 * @author s230984
 */
@ValidDay
public class DayImpl implements Day {

	/**
	 * The user.
	 */
	private final UserImpl user;

	/**
	 * The date.
	 */
	private final LocalDate date;

	/**
	 * The first start time.
	 */
	private final LocalTime startTime1;

	/**
	 * The first end time.
	 */
	private final LocalTime endTime1;

	/**
	 * The second start time.
	 */
	private final LocalTime startTime2;

	/**
	 * The second end time.
	 */
	private final LocalTime endTime2;

	/**
	 * The travel duration.
	 */
	private final Duration travelDuration;

	/**
	 * The entries.
	 */
	private final Set<EntryImpl> entries;

	/**
	 * Create a new day.
	 *
	 * @param user
	 *            The user.
	 * @param date
	 *            The date.
	 */
	public DayImpl(final UserImpl user, final LocalDate date, final LocalTime startTime1, final LocalTime endTime1,
			final LocalTime startTime2, final LocalTime endTime2, final Duration travelDuration,
			final Set<EntryImpl> entries) {
		super();
		final Set<DayImpl> userDays = new CopyOnWriteArraySet<>(user.getDays());
		userDays.add(this);
		this.user = new UserImpl(user.getName(), user.getAssignments(), userDays);
		this.date = date;
		this.startTime1 = startTime1;
		this.endTime1 = endTime1;
		this.startTime2 = startTime2;
		this.endTime2 = endTime2;
		this.travelDuration = travelDuration;
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
		if (!(obj instanceof DayImpl)) {
			return false;
		}
		final DayImpl other = (DayImpl) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
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
	public LocalDate getDate() {
		return date;
	}

	@Override
	public LocalTime getEndTime1() {
		return endTime1;
	}

	@Override
	public LocalTime getEndTime2() {
		return endTime2;
	}

	@Override
	public Set<EntryImpl> getEntries() {
		return entries;
	}

	@Override
	public LocalTime getStartTime1() {
		return startTime1;
	}

	@Override
	public LocalTime getStartTime2() {
		return startTime2;
	}

	@Override
	public Duration getTravelDuration() {
		return travelDuration;
	}

	@Override
	public UserImpl getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (date == null ? 0 : date.hashCode());
		result = prime * result + (user == null ? 0 : user.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return String.format(
				"DayImpl [date=%s, startTime1=%s, endTime1=%s, startTime2=%s, endTime2=%s, travelDuration=%s]", date,
				startTime1, endTime1, startTime2, endTime2, travelDuration);
	}

	@Override
	public DayImpl withTime1(final LocalTime startTime, final LocalTime endTime) {
		return new DayImpl(user, date, startTime, endTime, startTime2, endTime2, travelDuration, entries);
	}

	@Override
	public DayImpl withTime2(final LocalTime startTime, final LocalTime endTime) {
		return new DayImpl(user, date, startTime1, endTime1, startTime, endTime, travelDuration, entries);
	}

	@Override
	public DayImpl withTravelDuration(final Duration travelDuration) {
		return new DayImpl(user, date, startTime1, endTime1, startTime2, endTime2, travelDuration, entries);
	}

}
