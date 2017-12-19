package nl.vsmeets.timetracker.backend.ess.model.test;

import java.util.Collections;

import nl.vsmeets.timetracker.backend.ess.model.impl.CustomerImpl;

public interface CustomerConstants {

	Class<CustomerImpl> CLASS_UNDER_TEST = CustomerImpl.class;

	String CUSTOMER_NAME_VALID = "Customer name";
	String CUSTOMER_OTHER_NAME_VALID = "Other Customer name";

	default CustomerImpl getCustomer() {
		return new CustomerImpl(CUSTOMER_NAME_VALID, Collections.emptySet());
	}

	default CustomerImpl getCustomerOtherName() {
		return new CustomerImpl(CUSTOMER_OTHER_NAME_VALID, Collections.emptySet());
	}

}
