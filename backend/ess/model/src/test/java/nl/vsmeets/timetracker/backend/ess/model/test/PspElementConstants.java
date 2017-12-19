package nl.vsmeets.timetracker.backend.ess.model.test;

import java.util.Collections;

import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;

public interface PspElementConstants extends ProjectConstants {

	Class<PspElementImpl> CLASS_UNDER_TEST = PspElementImpl.class;

	String PSP_ELEMENT_NAME_VALID = "PSP Element name";
	String PSP_ELEMENT_OTHER_NAME_VALID = "Other PSP Element name";
	String PSP_ELEMENT_DESCRIPTION_VALID = "PSP Element description";

	default PspElementImpl getPspElement() {
		return new PspElementImpl(getProject(), PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID,
				Collections.emptySet());
	}

	default PspElementImpl getPspElementOtherName() {
		return new PspElementImpl(getProject(), PSP_ELEMENT_OTHER_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID,
				Collections.emptySet());
	}

	default PspElementImpl getPspElementOtherProject() {
		return new PspElementImpl(getProjectOtherName(), PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID,
				Collections.emptySet());
	}

}
