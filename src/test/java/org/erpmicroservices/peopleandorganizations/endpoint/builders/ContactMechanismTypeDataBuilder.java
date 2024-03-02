package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismType;

import java.util.UUID;

public class ContactMechanismTypeDataBuilder {

	public static ContactMechanismType.ContactMechanismTypeBuilder completeContactMechanismType() {
		return ContactMechanismType
				       .builder()
				       .description("ContactMechanismTypeDataBuilder " + UUID.randomUUID());
	}
}
