package nl.vsmeets.timetracker.backend.ess.model.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import nl.vsmeets.timetracker.backend.ess.model.Day;
import nl.vsmeets.timetracker.backend.ess.model.impl.DayImpl;

public interface DayConstants extends UserConstants {

	Class<DayImpl> CLASS_UNDER_TEST = DayImpl.class;

	LocalDate DAY_DATE_VALID = LocalDate.now();
	LocalDate DAY_OTHER_DATE_VALID = DAY_DATE_VALID.plusDays(2L);
	LocalTime DAY_START_TIME_1_VALID = LocalTime.now();
	LocalTime DAY_END_TIME_1_VALID = DAY_START_TIME_1_VALID.plusHours(4L);
	LocalTime DAY_START_TIME_2_VALID = DAY_END_TIME_1_VALID.plusHours(1L);
	LocalTime DAY_END_TIME_2_VALID = DAY_START_TIME_2_VALID.plusHours(4L);
	Duration DAY_TRAVEL_DURATION_VALID = Duration.ofHours(2L);
	Duration DAY_TRAVEL_DURATION_VALID_MINIMUM = Duration.ZERO;
	Duration DAY_TRAVEL_DURATION_VALID_MAXIMUM = Duration.ofDays(1L).minusNanos(1L);

	default Day getDay() {
		return new DayImpl(getUser(), DAY_DATE_VALID);
	}

	default Day getDayOtherDate() {
		return new DayImpl(getUser(), DAY_OTHER_DATE_VALID);
	}

	default Day getDayOtherUser() {
		return new DayImpl(getUserOtherName(), DAY_DATE_VALID);
	}

}
