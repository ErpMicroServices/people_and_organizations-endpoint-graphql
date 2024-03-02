package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationship;

import java.time.LocalDate;
import java.util.UUID;

public class PartyRelationshipTestDataBuilder {

	public static PartyRelationship.PartyRelationshipBuilder completePartyRelationship() {
		return PartyRelationship.builder()
				       .comment("PartyRelationshipTestDataBuilder " + UUID.randomUUID())
				       .fromDate(LocalDate.now());
	}
}
