package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismTypeEntity;

import java.util.UUID;

public class ContactMechanismTypeDataBuilder {

	public static ContactMechanismTypeEntity.ContactMechanismTypeEntityBuilder completeContactMechanismType() {
		return ContactMechanismTypeEntity
				       .builder()
				       .description("ContactMechanismTypeDataBuilder " + UUID.randomUUID());
	}
}
