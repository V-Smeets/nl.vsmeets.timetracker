/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nl.vsmeets.timetracker.backend.ess.model.Assignment;

/**
 * @author vincent
 */
public class ValidAssignmentValidator implements ConstraintValidator<ValidAssignment, Assignment> {

	@Override
	public boolean isValid(final Assignment assignment, final ConstraintValidatorContext context) {
		if (assignment == null) {
			return true;
		} else if (assignment.getStartDate() == null && assignment.getEndDate() == null) {
			return true;
		} else if (assignment.getStartDate() == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(getMessageTemplate("NoStartDate")).addPropertyNode("startDate")
					.addConstraintViolation();
			return false;
		} else if (assignment.getEndDate() == null) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(getMessageTemplate("NoEndDate")).addPropertyNode("endDate")
					.addConstraintViolation();
			return false;
		} else {
			return assignment.getStartDate().isBefore(assignment.getEndDate());
		}
	}

	private String getMessageTemplate(final String suffix) {
		final StringBuilder builder = new StringBuilder();
		builder.append('{');
		builder.append(ValidAssignment.class.getName());
		builder.append('.').append(suffix);
		builder.append('}');
		return builder.toString();
	}

}
