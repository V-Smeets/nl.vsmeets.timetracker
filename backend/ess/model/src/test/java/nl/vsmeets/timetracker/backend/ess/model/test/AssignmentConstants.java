package nl.vsmeets.timetracker.backend.ess.model.test;

import java.time.LocalDate;
import java.util.Collections;

import nl.vsmeets.timetracker.backend.ess.model.impl.AssignmentImpl;

public interface AssignmentConstants extends UserConstants, TaskConstants {

	Class<AssignmentImpl> CLASS_UNDER_TEST = AssignmentImpl.class;

	LocalDate ASSIGNMENT_START_DATE = LocalDate.now();
	LocalDate ASSIGNMENT_END_DATE = ASSIGNMENT_START_DATE.plusDays(10);

	default AssignmentImpl getAssignment() {
		return new AssignmentImpl(getUser(), getTask(), ASSIGNMENT_START_DATE, ASSIGNMENT_END_DATE,
				Collections.emptySet());
	}

	default AssignmentImpl getAssignmentOtherTask() {
		return new AssignmentImpl(getUser(), getTaskOtherName(), ASSIGNMENT_START_DATE, ASSIGNMENT_END_DATE,
				Collections.emptySet());
	}

	default AssignmentImpl getAssignmentOtherUser() {
		return new AssignmentImpl(getUserOtherName(), getTask(), ASSIGNMENT_START_DATE, ASSIGNMENT_END_DATE,
				Collections.emptySet());
	}

}
