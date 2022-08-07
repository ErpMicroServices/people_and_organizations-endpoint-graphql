package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismType;

import java.util.UUID;

public class ContactMechanismTypeDataBuilder {

	public static ContactMechanismType.ContactMechanismTypeBuilder completeContactMechanismType() {
		return ContactMechanismType
				       .builder()
				       .description("ContactMechanismTypeDataBuilder " + UUID.randomUUID());
	}
}
