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

import nl.vsmeets.timetracker.backend.ess.model.Project;
import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.TaskImpl;

public class PspElementImplTest extends AbstractValidationTest implements PspElementConstants {

	@Nested
	class WithNew {

		@BeforeEach
		public void createPspElement() {
			pspElement = new PspElementImpl(getProject(), PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID,
					Collections.emptySet());
			assertNotNull(pspElement);
			validate(pspElement);
		}

		@Test
		public void testEqualsObjectEqual() {
			assertTrue(pspElement.equals(getPspElement()));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(pspElement.equals(0));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(pspElement.equals(null));
		}

		@Test
		public void testEqualsObjectOtherName() {
			assertFalse(pspElement.equals(getPspElementOtherName()));
		}

		@Test
		public void testEqualsObjectOtherProject() {
			assertFalse(pspElement.equals(getPspElementOtherProject()));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(pspElement.equals(pspElement));
		}

		@Test
		public void testGetDescription() {
			assertEquals(PSP_ELEMENT_DESCRIPTION_VALID, pspElement.getDescription());
		}

		@Test
		public void testGetName() {
			assertEquals(PSP_ELEMENT_NAME_VALID, pspElement.getName());
		}

		@Test
		public void testGetProject() {
			final Project actualProject = pspElement.getProject();
			assertEquals(getProject(), actualProject);
			assertEquals(1, actualProject.getPspElements().size());
			assertTrue(actualProject.getPspElements().contains(pspElement));
		}

		@Test
		public void testGetTasks() {
			final Set<TaskImpl> tasks = pspElement.getTasks();
			assertNotNull(tasks);
			assertEquals(0, tasks.size());
		}

		@Test
		public void testHashCode() {
			assertNotEquals(0, pspElement.hashCode());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = pspElement.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

	}

	private PspElementImpl pspElement;

	@Test
	public void testPspElementImpl() {
		pspElement = new PspElementImpl(getProject(), PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID,
				Collections.emptySet());
		assertNotNull(pspElement);
		validate(pspElement);
	}

	@Test
	public void testPspElementImplNullDescription() {
		pspElement = new PspElementImpl(getProject(), PSP_ELEMENT_NAME_VALID, null, Collections.emptySet());
		assertNotNull(pspElement);
		validate(pspElement);
	}

	@Test
	public void testPspElementImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			pspElement = new PspElementImpl(getProject(), null, PSP_ELEMENT_DESCRIPTION_VALID, Collections.emptySet());
			assertNotNull(pspElement);
			validate(pspElement);
		});
	}

	@Test
	public void testPspElementImplNullProject() {
		assertThrows(NullPointerException.class, () -> {
			pspElement = new PspElementImpl(null, PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID,
					Collections.emptySet());
		});
	}

}
