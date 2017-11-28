package nl.vsmeets.timetracker.backend.ess.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nl.vsmeets.timetracker.backend.ess.model.Customer;
import nl.vsmeets.timetracker.backend.ess.model.impl.CustomerImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.ProjectImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;

class ProjectImplTest {

	@Nested
	class WithNew {

		private ProjectImpl project;

		@BeforeEach
		void createProject() {
			project = new ProjectImpl(customer, PROJECT_NAME_VALID);
			assertNotNull(project);
		}

		@Test
		void testEqualsObjectEqual() {
			final ProjectImpl project2 = new ProjectImpl(customer, PROJECT_NAME_VALID);
			assertTrue(project.equals(project2));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		void testEqualsObjectInstance() {
			assertFalse(project.equals(0));
		}

		@Test
		void testEqualsObjectNull() {
			assertFalse(project.equals(null));
		}

		@Test
		void testEqualsObjectOtherCustomer() {
			final CustomerImpl customer2 = new CustomerImpl(CUSTOMER_NAME_VALID + CUSTOMER_NAME_VALID);
			final ProjectImpl project2 = new ProjectImpl(customer2, PROJECT_NAME_VALID);
			assertFalse(project.equals(project2));
		}

		@Test
		void testEqualsObjectOtherName() {
			final ProjectImpl project2 = new ProjectImpl(customer, PROJECT_NAME_VALID + PROJECT_NAME_VALID);
			assertFalse(project.equals(project2));
		}

		@Test
		void testEqualsObjectSame() {
			assertTrue(project.equals(project));
		}

		@Test
		void testGetCustomer() {
			final Customer actualCustomer = project.getCustomer();
			assertSame(customer, actualCustomer);
			assertEquals(1, actualCustomer.getProjects().size());
			assertTrue(actualCustomer.getProjects().contains(project));
		}

		@Test
		void testGetName() {
			assertEquals(PROJECT_NAME_VALID, project.getName());
		}

		@Test
		void testGetProjects() {
			final Set<PspElementImpl> pspElements = project.getPspElements();
			assertNotNull(pspElements);
			assertEquals(0, pspElements.size());
		}

		@Test
		void testHashCode() {
			assertNotEquals(0, project.hashCode());
		}

		@Test
		void testToString() {
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

	private final CustomerImpl customer = new CustomerImpl(CUSTOMER_NAME_VALID);

	@Test
	void testProjectImpl() {
		assertNotNull(new ProjectImpl(customer, PROJECT_NAME_VALID));
	}

	@Test
	void testProjectImplNullCustomer() {
		assertThrows(NullPointerException.class, () -> new ProjectImpl(null, PROJECT_NAME_VALID));
	}

	@Test
	void testProjectImplNullCustomerNullName() {
		assertThrows(NullPointerException.class, () -> new ProjectImpl(null, null));
	}

	@Test
	void testProjectImplNullName() {
		assertThrows(NullPointerException.class, () -> new ProjectImpl(customer, null));
	}

}
