/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import nl.vsmeets.timetracker.backend.ess.model.Day;
import nl.vsmeets.timetracker.backend.ess.model.User;

/**
 * The implementation of a day.
 *
 * @author s230984
 */
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
	private LocalTime startTime1;

	/**
	 * The first end time.
	 */
	private LocalTime endTime1;

	/**
	 * The second start time.
	 */
	private LocalTime startTime2;

	/**
	 * The second end time.
	 */
	private LocalTime endTime2;

	/**
	 * The travel duration.
	 */
	private Duration travelDuration;

	/**
	 * The entries.
	 */
	private final Set<EntryImpl> entries = new HashSet<>();

	/**
	 * Create a new day.
	 *
	 * @param user
	 *            The user.
	 * @param date
	 *            The date.
	 */
	public DayImpl(final UserImpl user, final LocalDate date) {
		super();
		this.user = Objects.requireNonNull(user, "user");
		this.user.getDays().add(this);
		this.date = Objects.requireNonNull(date, "date");
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
	public User getUser() {
		return user;
	}

	@Override
	public void setEndTime1(final LocalTime endTime1) {
		this.endTime1 = endTime1;
	}

	@Override
	public void setEndTime2(final LocalTime endTime2) {
		this.endTime2 = endTime2;
	}

	@Override
	public void setStartTime1(final LocalTime startTime1) {
		this.startTime1 = startTime1;
	}

	@Override
	public void setStartTime2(final LocalTime startTime2) {
		this.startTime2 = startTime2;
	}

	@Override
	public void setTravelDuration(final Duration travelDuration) {
		this.travelDuration = travelDuration;
		if (travelDuration != null) {
			checkRange(travelDuration, Duration.ZERO, Duration.ofHours(24), "travelDuration");
		}
	}

	/**
	 * Check that the value is in the range between minimumAllowed and
	 * maximumAllowed inclusive.
	 *
	 * @param value
	 *            The value to check. (not null)
	 * @param minimumAllowed
	 *            The minimum allowed value. (not null)
	 * @param maximumAllowed
	 *            The maximum allowed value. (not null)
	 * @param name
	 *            The name of the value.
	 */
	private <T> void checkRange(final Comparable<T> value, final T minimumAllowed, final T maximumAllowed,
			final String name) {
		Objects.requireNonNull(value, "value");
		Objects.requireNonNull(minimumAllowed, "minimumAllowed");
		Objects.requireNonNull(maximumAllowed, "maximumAllowed");
		if (value.compareTo(minimumAllowed) < 0) {
			throw new IllegalArgumentException(String.format("%s < %s", name, minimumAllowed));
		} else if (value.compareTo(maximumAllowed) > 0) {
			throw new IllegalArgumentException(String.format("%s > %s", name, maximumAllowed));
		}
	}

}
