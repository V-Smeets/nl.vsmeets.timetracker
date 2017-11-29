/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model;

import java.time.Duration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * An entry for an assigned task or PSP element.
 *
 * @author s230984
 */
public interface Entry {

	/**
	 * Get the assignment.
	 *
	 * @return The assignment.
	 */
	@NotNull
	@Valid
	Assignment getAssignment();

	/**
	 * Get the comment;
	 *
	 * @return The comment.
	 */
	String getComment();

	/**
	 * Get the day.
	 *
	 * @return The day.
	 */
	@NotNull
	@Valid
	Day getDay();

	/**
	 * Get the duration.
	 *
	 * @return The duration.
	 */
	Duration getDuration();

	/**
	 * Set the comment.
	 *
	 * @param comment
	 *            The comment.
	 */
	void setComment(final String comment);

	/**
	 * Set the duration.
	 *
	 * @param duration
	 *            The duration.
	 */
	void setDuration(final Duration duration);

}
