package nl.vsmeets.timetracker.backend.ess.model.test;

import nl.vsmeets.timetracker.backend.ess.model.Project;
import nl.vsmeets.timetracker.backend.ess.model.impl.ProjectImpl;

public interface ProjectConstants extends CustomerConstants {

	Class<ProjectImpl> CLASS_UNDER_TEST = ProjectImpl.class;

	String PROJECT_NAME_VALID = "Project name";
	String PROJECT_OTHER_NAME_VALID = "Other Project name";

	default Project getProject() {
		return new ProjectImpl(getCustomer(), PROJECT_NAME_VALID);
	}

	default Project getProjectOtherCustomer() {
		return new ProjectImpl(getCustomerOtherName(), PROJECT_NAME_VALID);
	}

	default Project getProjectOtherName() {
		return new ProjectImpl(getCustomer(), PROJECT_OTHER_NAME_VALID);
	}

}
