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

import nl.vsmeets.timetracker.backend.ess.model.PspElement;
import nl.vsmeets.timetracker.backend.ess.model.impl.AssignmentImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.TaskImpl;

public class TaskImplTest extends AbstractValidationTest implements TaskConstants {

	@Nested
	class WithNew {

		@BeforeEach
		public void createTask() {
			task = new TaskImpl(getPspElement(), TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
			assertNotNull(task);
			validate(task);
		}

		@Test
		public void testEqualsObjectEqual() {
			assertTrue(task.equals(getTask()));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(task.equals(0));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(task.equals(null));
		}

		@Test
		public void testEqualsObjectOtherName() {
			assertFalse(task.equals(getTaskOtherName()));
		}

		@Test
		public void testEqualsObjectOtherPspElement() {
			assertFalse(task.equals(getTaskOtherPspElement()));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(task.equals(task));
		}

		@Test
		public void testGetAssignments() {
			final Set<AssignmentImpl> assignments = task.getAssignments();
			assertNotNull(assignments);
			assertEquals(0, assignments.size());
		}

		@Test
		public void testGetDescription() {
			assertEquals(TASK_DESCRIPTION_VALID, task.getDescription());
		}

		@Test
		public void testGetName() {
			assertEquals(TASK_NAME_VALID, task.getName());
		}

		@Test
		public void testGetPspElement() {
			final PspElement actualPspElement = task.getPspElement();
			assertEquals(getPspElement(), actualPspElement);
			assertEquals(1, actualPspElement.getTasks().size());
			assertTrue(actualPspElement.getTasks().contains(task));
		}

		@Test
		public void testHashCode() {
			assertNotEquals(0, task.hashCode());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = task.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

	}

	private TaskImpl task;

	@Test
	public void testTaskImpl() {
		task = new TaskImpl(getPspElement(), TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
		assertNotNull(task);
		validate(task);
	}

	@Test
	public void testTaskImplNullDescription() {
		task = new TaskImpl(getPspElement(), TASK_NAME_VALID, null);
		assertNotNull(task);
		validate(task);
	}

	@Test
	public void testTaskImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			task = new TaskImpl(getPspElement(), null, TASK_DESCRIPTION_VALID);
			assertNotNull(task);
			validate(task);
		});
	}

	@Test
	public void testTaskImplNullPspElement() {
		assertThrows(ConstraintViolationException.class, () -> {
			task = new TaskImpl(null, TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
			assertNotNull(task);
			validate(task);
		});
	}

}
