package nl.vsmeets.timetracker.backend.ess.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nl.vsmeets.timetracker.backend.ess.model.Task;
import nl.vsmeets.timetracker.backend.ess.model.User;
import nl.vsmeets.timetracker.backend.ess.model.impl.AssignmentImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.EntryImpl;

public class AssignmentImplTest extends AbstractValidationTest implements AssignmentConstants {

	@Nested
	class WithNew {

		@BeforeEach
		public void createAssignment() {
			assignment = new AssignmentImpl(getUser(), getTask(), ASSIGNMENT_START_DATE, ASSIGNMENT_END_DATE);
			assertNotNull(assignment);
			validate(assignment);
		}

		@Test
		public void testEqualsObjectEqual() {
			assertTrue(assignment.equals(getAssignment()));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(assignment.equals(0));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(assignment.equals(null));
		}

		@Test
		public void testEqualsObjectOtherTask() {
			assertFalse(assignment.equals(getAssignmentOtherTask()));
		}

		@Test
		public void testEqualsObjectOtherUser() {
			assertFalse(assignment.equals(getAssignmentOtherUser()));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(assignment.equals(assignment));
		}

		@Test
		public void testGetEndDate() {
			assertEquals(ASSIGNMENT_END_DATE, assignment.getEndDate());
		}

		@Test
		public void testGetEntries() {
			final Set<EntryImpl> entries = assignment.getEntries();
			assertNotNull(entries);
			assertEquals(0, entries.size());
		}

		@Test
		public void testGetStartDate() {
			assertEquals(ASSIGNMENT_START_DATE, assignment.getStartDate());
		}

		@Test
		public void testGetTask() {
			final Task task = assignment.getTask();
			assertEquals(getTask(), task);
			assertEquals(1, task.getAssignments().size());
			assertTrue(task.getAssignments().contains(assignment));
		}

		@Test
		public void testGetUser() {
			final User user = assignment.getUser();
			assertEquals(getUser(), user);
			assertEquals(1, user.getAssignments().size());
			assertTrue(user.getAssignments().contains(assignment));
		}

		@Test
		public void testHashCode() {
			assertNotEquals(0, assignment.hashCode());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = assignment.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

	}

	private AssignmentImpl assignment;

	@Test
	public void testAssignmentImpl() {
		assignment = new AssignmentImpl(getUser(), getTask(), ASSIGNMENT_START_DATE, ASSIGNMENT_END_DATE);
		assertNotNull(assignment);
		validate(assignment);
	}

	@Test
	public void testAssignmentImplDateOrder() {
		assertThrows(IllegalArgumentException.class, () -> {
			assignment = new AssignmentImpl(getUser(), getTask(), ASSIGNMENT_END_DATE, ASSIGNMENT_START_DATE);
			assertNotNull(assignment);
			validate(assignment);
		});
	}

	@Test
	public void testAssignmentImplNullEndDate() {
		assertThrows(NullPointerException.class, () -> {
			assignment = new AssignmentImpl(getUser(), getTask(), ASSIGNMENT_START_DATE, null);
			assertNotNull(assignment);
			validate(assignment);
		});
	}

	@Test
	public void testAssignmentImplNullStartDate() {
		assertThrows(NullPointerException.class, () -> {
			assignment = new AssignmentImpl(getUser(), getTask(), null, ASSIGNMENT_END_DATE);
			assertNotNull(assignment);
			validate(assignment);
		});
	}

	@Test
	public void testAssignmentImplNullTask() {
		assertThrows(ConstraintViolationException.class, () -> {
			assignment = new AssignmentImpl(getUser(), null, ASSIGNMENT_START_DATE, ASSIGNMENT_END_DATE);
			assertNotNull(assignment);
			validate(assignment);
		});
	}

	@Test
	public void testAssignmentImplNullUser() {
		assertThrows(ConstraintViolationException.class, () -> {
			assignment = new AssignmentImpl(null, getTask(), ASSIGNMENT_START_DATE, ASSIGNMENT_END_DATE);
			assertNotNull(assignment);
			validate(assignment);
		});
	}

}
