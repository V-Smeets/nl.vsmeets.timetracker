package nl.vsmeets.timetracker.backend.ess.model.test;

import nl.vsmeets.timetracker.backend.ess.model.PspElement;
import nl.vsmeets.timetracker.backend.ess.model.impl.PspElementImpl;

public interface PspElementConstants extends ProjectConstants {

	Class<PspElementImpl> CLASS_UNDER_TEST = PspElementImpl.class;

	String PSP_ELEMENT_NAME_VALID = "PSP Element name";
	String PSP_ELEMENT_OTHER_NAME_VALID = "Other PSP Element name";
	String PSP_ELEMENT_DESCRIPTION_VALID = "PSP Element description";

	default PspElement getPspElement() {
		return new PspElementImpl(getProject(), PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
	}

	default PspElement getPspElementOtherName() {
		return new PspElementImpl(getProject(), PSP_ELEMENT_OTHER_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
	}

	default PspElement getPspElementOtherProject() {
		return new PspElementImpl(getProjectOtherName(), PSP_ELEMENT_NAME_VALID, PSP_ELEMENT_DESCRIPTION_VALID);
	}

}
