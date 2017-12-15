/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl.validation;

import java.time.LocalTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nl.vsmeets.timetracker.backend.ess.model.Day;

/**
 * @author vincent
 */
public class ValidDayValidator implements ConstraintValidator<ValidDay, Day> {

	@Override
	public boolean isValid(final Day day, final ConstraintValidatorContext context) {
		if (day == null) {
			return true;
		} else if (day.getStartTime1() == null && day.getEndTime1() == null && day.getStartTime2() == null
				&& day.getEndTime2() == null) {
			return true;
		} else if (day.getStartTime1() != null && day.getEndTime1() != null && day.getStartTime2() == null
				&& day.getEndTime2() == null) {
			return isValidTimeSequence(day.getStartTime1(), day.getEndTime1(), "endTime1", "IllegalEndTime1", context);
		} else if (day.getStartTime1() != null && day.getEndTime1() != null && day.getStartTime2() != null
				&& day.getEndTime2() != null) {
			return isValidTimeSequence(day.getStartTime1(), day.getEndTime1(), "endTime1", "IllegalEndTime1", context)
					&& isValidTimeSequence(day.getStartTime2(), day.getEndTime2(), "endTime2", "IllegalEndTime2",
							context)
					&& isValidTimeSequence(day.getEndTime1(), day.getStartTime2(), "startTime2", "IllegalStartTime2",
							context);
		} else {
			return false;
		}
	}

	private String getMessageTemplate(final String suffix) {
		final StringBuilder builder = new StringBuilder();
		builder.append('{');
		builder.append(ValidDay.class.getName());
		builder.append('.').append(suffix);
		builder.append('}');
		return builder.toString();
	}

	private boolean isValidTimeSequence(final LocalTime startTime, final LocalTime endTime, final String propertyName,
			final String suffix, final ConstraintValidatorContext context) {
		if (startTime.isBefore(endTime)) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(getMessageTemplate(suffix)).addPropertyNode(propertyName)
					.addConstraintViolation();
			return false;
		}
	}

}
