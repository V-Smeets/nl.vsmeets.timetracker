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

import nl.vsmeets.timetracker.backend.ess.model.Customer;
import nl.vsmeets.timetracker.backend.ess.model.impl.ProjectImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;

class ProjectImplTest extends AbstractValidationTest implements ProjectConstants {

	@Nested
	class WithNew {

		@BeforeEach
		public void createProject() {
			project = new ProjectImpl(getCustomer(), PROJECT_NAME_VALID);
			assertNotNull(project);
			validate(project);
		}

		@Test
		public void testEqualsObjectEqual() {
			assertTrue(project.equals(getProject()));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(project.equals(0));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(project.equals(null));
		}

		@Test
		public void testEqualsObjectOtherCustomer() {
			assertFalse(project.equals(getProjectOtherCustomer()));
		}

		@Test
		public void testEqualsObjectOtherName() {
			assertFalse(project.equals(getProjectOtherName()));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(project.equals(project));
		}

		@Test
		public void testGetCustomer() {
			final Customer actualCustomer = project.getCustomer();
			assertEquals(getCustomer(), actualCustomer);
			assertEquals(1, actualCustomer.getProjects().size());
			assertTrue(actualCustomer.getProjects().contains(project));
		}

		@Test
		public void testGetName() {
			assertEquals(PROJECT_NAME_VALID, project.getName());
		}

		@Test
		public void testGetProjects() {
			final Set<PspElementImpl> pspElements = project.getPspElements();
			assertNotNull(pspElements);
			assertEquals(0, pspElements.size());
		}

		@Test
		public void testHashCode() {
			assertNotEquals(0, project.hashCode());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = project.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

	}

	private ProjectImpl project;

	@Test
	public void testProjectImpl() {
		project = new ProjectImpl(getCustomer(), PROJECT_NAME_VALID);
		assertNotNull(project);
		validate(project);
	}

	@Test
	public void testProjectImplNullCustomer() {
		assertThrows(ConstraintViolationException.class, () -> {
			project = new ProjectImpl(null, PROJECT_NAME_VALID);
			assertNotNull(project);
			validate(project);
		});
	}

	@Test
	public void testProjectImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			project = new ProjectImpl(getCustomer(), null);
			assertNotNull(project);
			validate(project);
		});
	}

}
