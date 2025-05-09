package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyTypeEntity;

import java.util.UUID;

public class PartyTypeTestDataBuilder {

	public static PartyTypeEntity.PartyTypeBuilder completePartyType() {
		return PartyTypeEntity
				       .builder()
				       .description("PartyTypeTestDataBuilder " + UUID.randomUUID());
	}
}
