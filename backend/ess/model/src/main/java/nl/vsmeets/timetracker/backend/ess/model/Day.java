/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

/**
 * A day on which some entries are made.
 *
 * @author s230984
 */
public interface Day {

	/**
	 * The date of this day.
	 *
	 * @return The date.
	 */
	@NotNull
	LocalDate getDate();

	/**
	 * Get the first end time.
	 *
	 * @return The first end time.
	 */
	LocalTime getEndTime1();

	/**
	 * Get the second end time.
	 *
	 * @return The second end time.
	 */
	LocalTime getEndTime2();

	/**
	 * Get the entries that belong to this day.
	 *
	 * @return The entries.
	 */
	@NotNull
	@Valid
	Set<? extends Entry> getEntries();

	/**
	 * Get the first start time.
	 *
	 * @return The first start time.
	 */
	LocalTime getStartTime1();

	/**
	 * Get the second start time.
	 *
	 * @return The second start time.
	 */
	LocalTime getStartTime2();

	/**
	 * Get the travel duration.
	 *
	 * @return The travel duration.
	 */
	@DurationMin
	@DurationMax(days = 1, inclusive = false)
	Duration getTravelDuration();

	/**
	 * Get the user.
	 *
	 * @return The user.
	 */
	@NotNull
	@Valid
	User getUser();

	/**
	 * Create a new day with the first start and end times set.
	 *
	 * @param startTime
	 *            The start time.
	 * @param endTime
	 *            The end time.
	 * @return A new object with the first times set as specified.
	 */
	Day withTime1(final LocalTime startTime, final LocalTime endTime);

	/**
	 * Create a new day with the second start and end times set.
	 *
	 * @param startTime
	 *            The start time.
	 * @param endTime
	 *            The end time.
	 * @return A new object with the second times set as specified.
	 */
	Day withTime2(final LocalTime startTime, final LocalTime endTime);

	/**
	 * Create a new day with the travel duration set.
	 *
	 * @param travelDuration
	 *            The travel duration.
	 * @return A new object with the travel duration set as specified.
	 */
	Day withTravelDuration(final Duration travelDuration);

}
