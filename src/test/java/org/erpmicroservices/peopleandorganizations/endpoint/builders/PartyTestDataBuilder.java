package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyEntity;

import java.util.UUID;

public class PartyTestDataBuilder {

	public static PartyEntity.PartyBuilder completeParty() {
		return PartyEntity.builder()
				       .comment("PartyTestDataBuilder " + UUID.randomUUID());
	}
}
