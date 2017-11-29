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
	 * Set the first end time.
	 *
	 * @param endTime1
	 *            The first end time.
	 */
	void setEndTime1(final LocalTime endTime1);

	/**
	 * Set the second end time.
	 *
	 * @param endTime2
	 *            The second end time.
	 */
	void setEndTime2(final LocalTime endTime2);

	/**
	 * Set the first start time.
	 *
	 * @param startTime1
	 *            The first start time.
	 */
	void setStartTime1(final LocalTime startTime1);

	/**
	 * Set the second start time.
	 *
	 * @param startTime2
	 *            The second start time.
	 */
	void setStartTime2(final LocalTime startTime2);

	/**
	 * Set the travel duration.
	 *
	 * @param travelDuration
	 *            The travel duration.
	 */
	void setTravelDuration(final Duration travelDuration);

}
