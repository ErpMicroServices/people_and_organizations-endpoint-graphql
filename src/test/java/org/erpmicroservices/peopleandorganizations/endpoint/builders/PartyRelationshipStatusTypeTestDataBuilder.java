package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipStatusTypeEntity;

import java.util.UUID;

public class PartyRelationshipStatusTypeTestDataBuilder {

	public static PartyRelationshipStatusTypeEntity.PartyRelationshipStatusTypeBuilder completePartyRelationshipStatusType() {
		return PartyRelationshipStatusTypeEntity.builder()
				       .description("PartyRelationshipStatusTypeTestDataBuilder " + UUID.randomUUID());
	}
}
