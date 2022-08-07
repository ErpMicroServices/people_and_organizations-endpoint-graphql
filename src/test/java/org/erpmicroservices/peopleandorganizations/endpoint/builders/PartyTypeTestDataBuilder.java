package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;

import java.util.UUID;

public class PartyTypeTestDataBuilder {

	public static PartyType.PartyTypeBuilder completePartyType() {
		return PartyType
				       .builder()
				       .description("PartyTypeTestDataBuilder " + UUID.randomUUID());
	}
}
