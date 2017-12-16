package nl.vsmeets.timetracker.backend.ess.model.test;

import java.time.Duration;

import nl.vsmeets.timetracker.backend.ess.model.Entry;
import nl.vsmeets.timetracker.backend.ess.model.impl.EntryImpl;

public interface EntryConstants extends DayConstants, AssignmentConstants {

	Class<EntryImpl> CLASS_UNDER_TEST = EntryImpl.class;

	Duration ENTRY_DURATION_VALID = Duration.ofHours(2);
	Duration ENTRY_DURATION_VALID_MINIMUM = Duration.ZERO;
	Duration ENTRY_DURATION_VALID_MAXIMUM = Duration.ofDays(1L).minusNanos(1L);
	String ENTRY_COMMENT_VALID = "A comment";

	default Entry getEntry() {
		return new EntryImpl(getDay(), getAssignment());
	}

	default Entry getEntryOtherAssignment() {
		return new EntryImpl(getDay(), getAssignmentOtherTask());
	}

	default Entry getEntryOtherDay() {
		return new EntryImpl(getDayOtherDate(), getAssignment());
	}

}
