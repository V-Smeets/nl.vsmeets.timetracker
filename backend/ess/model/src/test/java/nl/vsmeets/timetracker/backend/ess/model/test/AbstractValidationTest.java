/**
 *
 */
package nl.vsmeets.timetracker.backend.ess.model.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;

/**
 * @author s230984
 */
public abstract class AbstractValidationTest {

	private static Validator validator;

	@BeforeAll
	public static void createValidator() {
		final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	protected <T> void validate(final T object) {
		assertNotNull(object);
		final Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
		assertNotNull(constraintViolations);
		if (constraintViolations.size() != 0) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

}
