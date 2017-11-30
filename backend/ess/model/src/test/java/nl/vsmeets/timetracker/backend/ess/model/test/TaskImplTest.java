package nl.vsmeets.timetracker.backend.ess.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nl.vsmeets.timetracker.backend.ess.model.PspElement;
import nl.vsmeets.timetracker.backend.ess.model.impl.AssignmentImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.CustomerImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.ProjectImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.TaskImpl;

public class TaskImplTest extends AbstractValidationTest {

	@Nested
	class WithNew {

		@BeforeEach
		public void createTask() {
			task = new TaskImpl(pspElement, TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
			assertNotNull(task);
			validate(task);
		}

		@Test
		public void testEqualsObjectEqual() {
			final TaskImpl task2 = new TaskImpl(pspElement, TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
			assertTrue(task.equals(task2));
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
			final TaskImpl task2 = new TaskImpl(pspElement, TASK_NAME_VALID + TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
			assertFalse(task.equals(task2));
		}

		@Test
		public void testEqualsObjectOtherPspElement() {
			final TaskImpl task2 = new TaskImpl(
					new PspElementImpl(new ProjectImpl(new CustomerImpl(CUSTOMER_NAME_VALID), PROJECT_NAME_VALID),
							PSP_ELEMENT_NAME_VALID + PSP_ELEMENT_NAME_VALID, null),
					TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
			assertFalse(task.equals(task2));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(task.equals(task));
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
		public void testGetProjects() {
			final Set<AssignmentImpl> assignments = task.getAssignments();
			assertNotNull(assignments);
			assertEquals(0, assignments.size());
		}

		@Test
		public void testGetPspElement() {
			final PspElement actualPspElement = task.getPspElement();
			assertSame(pspElement, actualPspElement);
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

	private static final Class<TaskImpl> CLASS_UNDER_TEST = TaskImpl.class;

	private static final String CUSTOMER_NAME_VALID = "Customer name";
	private static final String PROJECT_NAME_VALID = "Project name";
	private static final String PSP_ELEMENT_NAME_VALID = "PSP Element name";
	private static final String TASK_NAME_VALID = "Task name";
	private static final String TASK_DESCRIPTION_VALID = "Task description";

	private PspElementImpl pspElement;
	private TaskImpl task;

	@BeforeEach
	public void createPspElement() {
		pspElement = new PspElementImpl(new ProjectImpl(new CustomerImpl(CUSTOMER_NAME_VALID), PROJECT_NAME_VALID),
				PSP_ELEMENT_NAME_VALID, null);
		assertNotNull(pspElement);
		validate(pspElement);
	}

	@Test
	public void testTaskImpl() {
		task = new TaskImpl(pspElement, TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
		assertNotNull(task);
		validate(task);
	}

	@Test
	public void testTaskImplNullDescription() {
		task = new TaskImpl(pspElement, TASK_NAME_VALID, null);
		assertNotNull(task);
		validate(task);
	}

	@Test
	public void testTaskImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			task = new TaskImpl(pspElement, null, TASK_DESCRIPTION_VALID);
			assertNotNull(task);
			validate(task);
		});
	}

	@Test
	public void testTaskImplNullPspsElement() {
		assertThrows(NullPointerException.class, () -> {
			task = new TaskImpl(null, TASK_NAME_VALID, TASK_DESCRIPTION_VALID);
			assertNotNull(task);
			validate(task);
		});
	}

}
