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

import nl.vsmeets.timetracker.backend.ess.model.Project;
import nl.vsmeets.timetracker.backend.ess.model.impl.CustomerImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.ProjectImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.TaskImpl;

public class PspElementImplTest extends AbstractValidationTest {

	@Nested
	class WithNew {

		@BeforeEach
		public void createPspElement() {
			pspElement = new PspElementImpl(project, PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
			assertNotNull(pspElement);
			validate(pspElement);
		}

		@Test
		public void testEqualsObjectEqual() {
			final PspElementImpl pspElement2 =
					new PspElementImpl(project, PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
			assertTrue(pspElement.equals(pspElement2));
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
			final PspElementImpl pspElement2 = new PspElementImpl(project,
					PSP_ELEMENT_NAME_VALID + PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
			assertFalse(pspElement.equals(pspElement2));
		}

		@Test
		public void testEqualsObjectOtherProject() {
			final PspElementImpl pspElement2 = new PspElementImpl(
					new ProjectImpl(new CustomerImpl(CUSTOMER_NAME_VALID), PROJECT_NAME_VALID + PROJECT_NAME_VALID),
					PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
			assertFalse(pspElement.equals(pspElement2));
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
			assertSame(project, actualProject);
			assertEquals(1, actualProject.getPspElements().size());
			assertTrue(actualProject.getPspElements().contains(pspElement));
		}

		@Test
		public void testGetProjects() {
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

	private static final Class<PspElementImpl> CLASS_UNDER_TEST = PspElementImpl.class;

	private static final String CUSTOMER_NAME_VALID = "Customer name";
	private static final String PROJECT_NAME_VALID = "Project name";
	private static final String PSP_ELEMENT_NAME_VALID = "PSP Element name";
	private static final String PSP_ELEMENT_DESCRIPTION_VALID = "PSP Element description";

	private ProjectImpl project;
	private PspElementImpl pspElement;

	@BeforeEach
	public void createProject() {
		project = new ProjectImpl(new CustomerImpl(CUSTOMER_NAME_VALID), PROJECT_NAME_VALID);
		assertNotNull(project);
		validate(project);
	}

	@Test
	public void testPspElementImpl() {
		pspElement = new PspElementImpl(project, PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
		assertNotNull(pspElement);
		validate(pspElement);
	}

	@Test
	public void testPspElementImplNullDescription() {
		pspElement = new PspElementImpl(project, PSP_ELEMENT_NAME_VALID, null);
		assertNotNull(pspElement);
		validate(pspElement);
	}

	@Test
	public void testPspElementImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			pspElement = new PspElementImpl(project, null, PSP_ELEMENT_DESCRIPTION_VALID);
			assertNotNull(pspElement);
			validate(pspElement);
		});
	}

	@Test
	public void testPspElementImplNullProject() {
		assertThrows(NullPointerException.class, () -> {
			pspElement = new PspElementImpl(null, PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
			assertNotNull(pspElement);
			validate(pspElement);
		});
	}

}
