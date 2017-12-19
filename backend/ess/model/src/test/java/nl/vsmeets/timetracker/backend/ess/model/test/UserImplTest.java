package nl.vsmeets.timetracker.backend.ess.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nl.vsmeets.timetracker.backend.ess.model.impl.AssignmentImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.DayImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.UserImpl;

public class UserImplTest extends AbstractValidationTest implements UserConstants {

	@Nested
	class WithNew {

		@BeforeEach
		public void createUser() {
			user = new UserImpl(USER_NAME_VALID, Collections.emptySet(), Collections.emptySet());
			assertNotNull(user);
			validate(user);
		}

		@Test
		public void testEqualsObjectEqual() {
			assertTrue(user.equals(getUser()));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(user.equals(0));
		}

		@Test
		public void testEqualsObjectNotEqual() {
			assertFalse(user.equals(getUserOtherName()));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(user.equals(null));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(user.equals(user));
		}

		@Test
		public void testGetAssignments() {
			final Set<AssignmentImpl> assignments = user.getAssignments();
			assertNotNull(assignments);
			assertEquals(0, assignments.size());
		}

		@Test
		public void testGetDays() {
			final Set<DayImpl> days = user.getDays();
			assertNotNull(days);
			assertEquals(0, days.size());
		}

		@Test
		public void testGetName() {
			assertEquals(USER_NAME_VALID, user.getName());
		}

		@Test
		public void testHashCode() {
			assertNotEquals(0, user.hashCode());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = user.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

	}

	private UserImpl user;

	@Test
	public void testUserImpl() {
		user = new UserImpl(USER_NAME_VALID, Collections.emptySet(), Collections.emptySet());
		assertNotNull(user);
		validate(user);
	}

	@Test
	public void testUserImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			user = new UserImpl(null, Collections.emptySet(), Collections.emptySet());
			validate(user);
		});
	}

}
