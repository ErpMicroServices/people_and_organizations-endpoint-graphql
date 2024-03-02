package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.Party;

import java.util.UUID;

public class PartyTestDataBuilder {

	public static Party.PartyBuilder completeParty() {
		return Party.builder()
				       .comment("PartyTestDataBuilder " + UUID.randomUUID());
	}
}
