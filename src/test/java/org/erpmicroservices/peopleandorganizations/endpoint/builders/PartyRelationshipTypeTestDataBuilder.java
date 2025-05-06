package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipTypeEntity;

import java.util.UUID;

public class PartyRelationshipTypeTestDataBuilder {

	public static PartyRelationshipTypeEntity.PartyRelationshipTypeBuilder completePartyRelationshipType() {
		return PartyRelationshipTypeEntity.builder()
				       .description("PartyRelationshipTypeTestDataBuilder " + UUID.randomUUID());
	}
}
