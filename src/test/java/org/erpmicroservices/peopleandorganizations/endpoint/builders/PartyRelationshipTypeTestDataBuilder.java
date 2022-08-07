package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationshipType;

import java.util.UUID;

public class PartyRelationshipTypeTestDataBuilder {

	public static PartyRelationshipType.PartyRelationshipTypeBuilder completePartyRelationshipType() {
		return PartyRelationshipType.builder()
				       .description("PartyRelationshipTypeTestDataBuilder " + UUID.randomUUID());
	}
}
