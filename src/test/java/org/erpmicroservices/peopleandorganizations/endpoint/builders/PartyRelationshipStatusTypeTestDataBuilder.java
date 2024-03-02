package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipStatusType;

import java.util.UUID;

public class PartyRelationshipStatusTypeTestDataBuilder {

	public static PartyRelationshipStatusType.PartyRelationshipStatusTypeBuilder completePartyRelationshipStatusType() {
		return PartyRelationshipStatusType.builder()
				       .description("PartyRelationshipStatusTypeTestDataBuilder " + UUID.randomUUID());
	}
}
