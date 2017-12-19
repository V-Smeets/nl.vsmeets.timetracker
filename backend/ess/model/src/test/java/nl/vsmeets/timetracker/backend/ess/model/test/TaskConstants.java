package nl.vsmeets.timetracker.backend.ess.model.test;

import java.util.Collections;

import nl.vsmeets.timetracker.backend.ess.model.impl.TaskImpl;

public interface TaskConstants extends PspElementConstants {

	Class<TaskImpl> CLASS_UNDER_TEST = TaskImpl.class;

	String TASK_NAME_VALID = "Task name";
	String TASK_OTHER_NAME_VALID = "Other Task name";
	String TASK_DESCRIPTION_VALID = "Task description";

	default TaskImpl getTask() {
		return new TaskImpl(getPspElement(), TASK_NAME_VALID, TASK_DESCRIPTION_VALID, Collections.emptySet());
	}

	default TaskImpl getTaskOtherName() {
		return new TaskImpl(getPspElement(), TASK_OTHER_NAME_VALID, TASK_DESCRIPTION_VALID, Collections.emptySet());
	}

	default TaskImpl getTaskOtherPspElement() {
		return new TaskImpl(getPspElementOtherProject(), TASK_NAME_VALID, TASK_DESCRIPTION_VALID,
				Collections.emptySet());
	}

}
