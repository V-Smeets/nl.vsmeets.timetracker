package nl.vsmeets.timetracker.backend.ess.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nl.vsmeets.timetracker.backend.ess.model.User;
import nl.vsmeets.timetracker.backend.ess.model.impl.DayImpl;

public class DayImplTest extends AbstractValidationTest implements DayConstants {

	@Nested
	class WithNew {

		@BeforeEach
		public void createDay() {
			day = new DayImpl(getUser(), DAY_DATE_VALID, null, null, null, null, null, Collections.emptySet());
			assertNotNull(day);
			validate(day);
		}

		@Test
		public void testEqualsObjectEqual() {
			assertTrue(day.equals(getDay()));
		}

		@Test
		@SuppressWarnings("unlikely-arg-type")
		public void testEqualsObjectInstance() {
			assertFalse(day.equals(0));
		}

		@Test
		public void testEqualsObjectNull() {
			assertFalse(day.equals(null));
		}

		@Test
		public void testEqualsObjectOtherDate() {
			assertFalse(day.equals(getDayOtherDate()));
		}

		@Test
		public void testEqualsObjectOtherUser() {
			assertFalse(day.equals(getDayOtherUser()));
		}

		@Test
		public void testEqualsObjectSame() {
			assertTrue(day.equals(day));
		}

		@Test
		public void testGetDate() {
			assertEquals(DAY_DATE_VALID, day.getDate());
		}

		@Test
		public void testGetEndTime1() {
			assertNull(day.getEndTime1());
		}

		@Test
		public void testGetEndTime2() {
			assertNull(day.getEndTime2());
		}

		@Test
		public void testGetStartTime1() {
			assertNull(day.getStartTime1());
		}

		@Test
		public void testGetStartTime2() {
			assertNull(day.getStartTime2());
		}

		@Test
		public void testGetTravelDuration() {
			assertNull(day.getTravelDuration());
		}

		@Test
		public void testGetUser() {
			final User user = day.getUser();
			assertEquals(getUser(), user);
			assertEquals(1, user.getDays().size());
			assertTrue(user.getDays().contains(day));
		}

		@Test
		public void testHashCode() {
			assertNotEquals(0, day.hashCode());
		}

		@Test
		public void testOnlyEndTime1() {
			assertThrows(ConstraintViolationException.class, () -> {
				validate(day.withTime1(null, DAY_END_TIME_1_VALID));
			});
		}

		@Test
		public void testOnlyEndTime2() {
			assertThrows(ConstraintViolationException.class, () -> {
				validate(day.withTime2(null, DAY_END_TIME_2_VALID));
			});
		}

		@Test
		public void testOnlyStartTime1() {
			assertThrows(ConstraintViolationException.class, () -> {
				validate(day.withTime1(DAY_START_TIME_1_VALID, null));
			});
		}

		@Test
		public void testOnlyStartTime2() {
			assertThrows(ConstraintViolationException.class, () -> {
				validate(day.withTime2(DAY_START_TIME_2_VALID, null));
			});
		}

		@Test
		public void testSetTravelDuration() {
			day = day.withTravelDuration(DAY_TRAVEL_DURATION_VALID);
			validate(day);
			assertEquals(DAY_TRAVEL_DURATION_VALID, day.getTravelDuration());
		}

		@Test
		public void testSetTravelDurationInvalidMaximum() {
			assertThrows(ConstraintViolationException.class, () -> {
				day = day.withTravelDuration(DAY_TRAVEL_DURATION_VALID_MAXIMUM.plusNanos(1L));
				validate(day);
			});
		}

		@Test
		public void testSetTravelDurationInvalidMinimum() {
			assertThrows(ConstraintViolationException.class, () -> {
				day = day.withTravelDuration(DAY_TRAVEL_DURATION_VALID_MINIMUM.minusNanos(1L));
				validate(day);
			});
		}

		@Test
		public void testSetTravelDurationNull() {
			day = day.withTravelDuration(null);
			validate(day);
			assertNull(day.getTravelDuration());
		}

		@Test
		public void testSetTravelDurationValidMaximum() {
			day = day.withTravelDuration(DAY_TRAVEL_DURATION_VALID_MAXIMUM);
			validate(day);
			assertEquals(DAY_TRAVEL_DURATION_VALID_MAXIMUM, day.getTravelDuration());
		}

		@Test
		public void testSetTravelDurationValidMinimum() {
			day = day.withTravelDuration(DAY_TRAVEL_DURATION_VALID_MINIMUM);
			validate(day);
			assertEquals(DAY_TRAVEL_DURATION_VALID_MINIMUM, day.getTravelDuration());
		}

		@Test
		public void testToString() {
			final String expected = CLASS_UNDER_TEST.getSimpleName();
			final String string = day.toString();
			assertNotNull(string);
			assertTrue(string.length() >= expected.length());
			assertEquals(expected, string.substring(0, expected.length()));
		}

		@Test
		public void testWithTime1() {
			day = day.withTime1(DAY_START_TIME_1_VALID, DAY_END_TIME_1_VALID);
			validate(day);
			assertEquals(DAY_START_TIME_1_VALID, day.getStartTime1());
			assertEquals(DAY_END_TIME_1_VALID, day.getEndTime1());
		}

		@Test
		public void testWithTime1Invalid() {
			assertThrows(ConstraintViolationException.class, () -> {
				day = day.withTime1(DAY_END_TIME_1_VALID, DAY_START_TIME_1_VALID);
				validate(day);
			});
		}

		@Test
		public void testWithTime1WithStartTime2Invalid() {
			assertThrows(ConstraintViolationException.class, () -> {
				day = day.withTime1(DAY_START_TIME_1_VALID, DAY_START_TIME_2_VALID);
				day = day.withTime2(DAY_END_TIME_1_VALID, DAY_END_TIME_2_VALID);
				validate(day);
			});
		}

		@Test
		public void testWithTime1WithTime2() {
			day = day.withTime1(DAY_START_TIME_1_VALID, DAY_END_TIME_1_VALID);
			day = day.withTime2(DAY_START_TIME_2_VALID, DAY_END_TIME_2_VALID);
			validate(day);
			assertEquals(DAY_START_TIME_2_VALID, day.getStartTime2());
			assertEquals(DAY_END_TIME_2_VALID, day.getEndTime2());
		}

		@Test
		public void testWithTime1WithTime2Invalid() {
			assertThrows(ConstraintViolationException.class, () -> {
				day = day.withTime1(DAY_START_TIME_1_VALID, DAY_END_TIME_1_VALID);
				day = day.withTime2(DAY_END_TIME_2_VALID, DAY_START_TIME_2_VALID);
				validate(day);
			});
		}

		@Test
		public void testWithTime2() {
			assertThrows(ConstraintViolationException.class, () -> {
				day = day.withTime2(DAY_START_TIME_2_VALID, DAY_END_TIME_2_VALID);
				validate(day);
			});
		}

	}

	private DayImpl day;

	@Test
	public void testDayImpl() {
		day = new DayImpl(getUser(), DAY_DATE_VALID, null, null, null, null, null, Collections.emptySet());
		assertNotNull(day);
		validate(day);
	}

	@Test
	public void testDayImplNullDate() {
		assertThrows(ConstraintViolationException.class, () -> {
			day = new DayImpl(getUser(), null, null, null, null, null, null, Collections.emptySet());
			assertNotNull(day);
			validate(day);
		});
	}

	@Test
	public void testDayImplNullUser() {
		assertThrows(NullPointerException.class, () -> {
			day = new DayImpl(null, DAY_DATE_VALID, null, null, null, null, null, Collections.emptySet());
		});
	}

}
