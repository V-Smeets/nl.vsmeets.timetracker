package nl.vsmeets.timetracker.backend.ess.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nl.vsmeets.timetracker.backend.ess.model.impl.EntryImpl;

public class EntryImplTest extends AbstractValidationTest implements EntryConstants {

	@Nested
	class WithNew {

		@BeforeEach
		public void createEntry() {
			entry = new EntryImpl(getDay(), getAssignment());
			assertNotNull(entry);
			validate(entry);
		}

		@Test
		public void testEqualsObjectEqual() {
			assertTrue(entry.equals(getEntry()));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(entry.equals(0));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(entry.equals(null));
		}

		@Test
		public void testEqualsObjectOtherAssignment() {
			assertFalse(entry.equals(getEntryOtherAssignment()));
		}

		@Test
		public void testEqualsObjectOtherDay() {
			assertFalse(entry.equals(getEntryOtherDay()));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(entry.equals(entry));
		}

		@Test
		public void testGetAssignment() {
			assertEquals(getAssignment(), entry.getAssignment());
		}

		@Test
		public void testGetComment() {
			assertNull(entry.getComment());
		}

		@Test
		public void testGetDay() {
			assertEquals(getDay(), entry.getDay());
		}

		@Test
		public void testGetDuration() {
			assertNull(entry.getDuration());
		}

		@Test
		public void testSetComment() {
			entry.setComment(ENTRY_COMMENT_VALID);
			assertEquals(ENTRY_COMMENT_VALID, entry.getComment());
		}

		@Test
		public void testSetDuration() {
			entry.setDuration(ENTRY_DURATION_VALID);
			assertEquals(ENTRY_DURATION_VALID, entry.getDuration());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = entry.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

	}

	private EntryImpl entry;

	@Test
	public void testEntryImpl() {
		entry = new EntryImpl(getDay(), getAssignment());
		assertNotNull(entry);
		validate(entry);
	}

	@Test
	public void testEntryImplNullAssignment() {
		assertThrows(ConstraintViolationException.class, () -> {
			entry = new EntryImpl(getDay(), null);
			assertNotNull(entry);
			validate(entry);
		});
	}

	@Test
	public void testEntryImplNullDay() {
		assertThrows(ConstraintViolationException.class, () -> {
			entry = new EntryImpl(null, getAssignment());
			assertNotNull(entry);
			validate(entry);
		});
	}

}
