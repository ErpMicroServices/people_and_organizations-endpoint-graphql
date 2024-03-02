package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipType;

import java.util.UUID;

public class PartyRelationshipTypeTestDataBuilder {

	public static PartyRelationshipType.PartyRelationshipTypeBuilder completePartyRelationshipType() {
		return PartyRelationshipType.builder()
				       .description("PartyRelationshipTypeTestDataBuilder " + UUID.randomUUID());
	}
}
