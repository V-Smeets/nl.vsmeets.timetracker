/**
 *
 */
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

import nl.vsmeets.timetracker.backend.ess.model.impl.CustomerImpl;
import nl.vsmeets.timetracker.backend.ess.model.impl.ProjectImpl;

class CustomerImplTest extends AbstractValidationTest {

	@Nested
	class WithNew {

		@BeforeEach
		public void createCustomer() {
			customer = new CustomerImpl(CUSTOMER_NAME_VALID);
			assertNotNull(customer);
			validate(customer);
		}

		@Test
		public void testEqualsObjectEqual() {
			final CustomerImpl customer2 = new CustomerImpl(CUSTOMER_NAME_VALID);
			assertTrue(customer.equals(customer2));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(customer.equals(0));
		}

		@Test
		public void testEqualsObjectNotEqual() {
			final CustomerImpl customer2 = new CustomerImpl(CUSTOMER_NAME_VALID + CUSTOMER_NAME_VALID);
			assertFalse(customer.equals(customer2));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(customer.equals(null));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(customer.equals(customer));
		}

		@Test
		public void testGetName() {
			assertEquals(CUSTOMER_NAME_VALID, customer.getName());
		}

		@Test
		public void testGetProjects() {
			final Set<ProjectImpl> projects = customer.getProjects();
			assertNotNull(projects);
			assertEquals(0, projects.size());
		}

		@Test
		public void testHashCode() {
			assertNotEquals(0, customer.hashCode());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = customer.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

	}

	private static final Class<CustomerImpl> CLASS_UNDER_TEST = CustomerImpl.class;

	private static final String CUSTOMER_NAME_VALID = "Customer name";

	private CustomerImpl customer;

	@Test
	public void testCustomerImpl() {
		customer = new CustomerImpl(CUSTOMER_NAME_VALID);
		assertNotNull(customer);
		validate(customer);
	}

	@Test
	public void testCustomerImplNullName() {
		assertThrows(ConstraintViolationException.class, () -> {
			customer = new CustomerImpl(null);
			validate(customer);
		});
	}

}
