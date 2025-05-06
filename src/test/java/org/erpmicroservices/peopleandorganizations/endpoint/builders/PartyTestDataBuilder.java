package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyEntity;

import java.util.UUID;

public class PartyTestDataBuilder {

	public static PartyEntity.PartyEntityBuilder completeParty() {
		return PartyEntity.builder()
				       .comment("PartyTestDataBuilder " + UUID.randomUUID());
	}
}
