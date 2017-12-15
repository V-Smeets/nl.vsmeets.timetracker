/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.impl.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The day has to be valid.
 *
 * @author vincent
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = { ValidDayValidator.class })
public @interface ValidDay {

	Class<?>[] groups() default {};

	String message() default "{nl.vsmeets.timetracker.backend.ess.model.impl.validation.ValidDay}";

	Class<? extends Payload>[] payload() default {};

}
