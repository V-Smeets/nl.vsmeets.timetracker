/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

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
	@NotNull
	@Valid
	private final User user;

	/**
	 * The date.
	 */
	@NotNull
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
	@DurationMin
	@DurationMax(days = 1, inclusive = false)
	private Duration travelDuration;

	/**
	 * The entries.
	 */
	@NotNull
	@Valid
	private final Set<EntryImpl> entries = new HashSet<>();

	/**
	 * Create a new day.
	 *
	 * @param user
	 *            The user.
	 * @param date
	 *            The date.
	 */
	public DayImpl(@NotNull @Valid final User user, @NotNull final LocalDate date) {
		super();
		this.user = user;
		this.date = date;
		if (this.user instanceof UserImpl) {
			final UserImpl userImpl = (UserImpl) this.user;
			userImpl.getDays().add(this);
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
	public User getUser() {
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
	}

	@Override
	public String toString() {
		return String.format(
				"DayImpl [date=%s, startTime1=%s, endTime1=%s, startTime2=%s, endTime2=%s, travelDuration=%s]", date,
				startTime1, endTime1, startTime2, endTime2, travelDuration);
	}

}
