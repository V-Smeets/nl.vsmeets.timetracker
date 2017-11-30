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

import nl.vsmeets.timetracker.backend.ess.model.Customer;
import nl.vsmeets.timetracker.backend.ess.model.impl.CustomerImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.ProjectImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;

class ProjectImplTest extends AbstractValidationTest {

	@Nested
	class WithNew {

		@BeforeEach
		public void createProject() {
			project = new ProjectImpl(customer, PROJECT_NAME_VALID);
			assertNotNull(project);
			validate(project);
		}

		@Test
		public void testEqualsObjectEqual() {
			final ProjectImpl project2 = new ProjectImpl(customer, PROJECT_NAME_VALID);
			assertTrue(project.equals(project2));
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
			final CustomerImpl customer2 = new CustomerImpl(CUSTOMER_NAME_VALID + CUSTOMER_NAME_VALID);
			final ProjectImpl project2 = new ProjectImpl(customer2, PROJECT_NAME_VALID);
			assertFalse(project.equals(project2));
		}

		@Test
		public void testEqualsObjectOtherName() {
			final ProjectImpl project2 = new ProjectImpl(customer, PROJECT_NAME_VALID + PROJECT_NAME_VALID);
			assertFalse(project.equals(project2));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(project.equals(project));
		}

		@Test
		public void testGetCustomer() {
			final Customer actualCustomer = project.getCustomer();
			assertSame(customer, actualCustomer);
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

	private static final Class<ProjectImpl> CLASS_UNDER_TEST = ProjectImpl.class;

	private static final String CUSTOMER_NAME_VALID = "Customer name";
	private static final String PROJECT_NAME_VALID = "Project name";

	private CustomerImpl customer;
	private ProjectImpl project;

	@BeforeEach
	public void createCustomer() {
		customer = new CustomerImpl(CUSTOMER_NAME_VALID);
		assertNotNull(customer);
		validate(customer);
	}

	@Test
	public void testProjectImpl() {
		project = new ProjectImpl(customer, PROJECT_NAME_VALID);
		assertNotNull(project);
		validate(project);
	}

	@Test
	public void testProjectImplNullCustomer() {
		assertThrows(NullPointerException.class, () -> {
			project = new ProjectImpl(null, PROJECT_NAME_VALID);
			assertNotNull(project);
			validate(project);
		});
	}

	@Test
	public void testProjectImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			project = new ProjectImpl(customer, null);
			assertNotNull(project);
			validate(project);
		});
	}

}
