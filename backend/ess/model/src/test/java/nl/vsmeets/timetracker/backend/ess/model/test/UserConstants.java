package nl.vsmeets.timetracker.backend.ess.model.test;

import nl.vsmeets.timetracker.backend.ess.model.User;
import nl.vsmeets.timetracker.backend.ess.model.impl.UserImpl;

public interface UserConstants {

	Class<UserImpl> CLASS_UNDER_TEST = UserImpl.class;

	String USER_NAME_VALID = "User name";
	String USER_OTHER_NAME_VALID = "Other User name";

	default User getUser() {
		return new UserImpl(USER_NAME_VALID);
	}

	default User getUserOtherName() {
		return new UserImpl(USER_OTHER_NAME_VALID);
	}

}
