package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipEntity;

import java.time.LocalDate;
import java.util.UUID;

public class PartyRelationshipTestDataBuilder {

	public static PartyRelationshipEntity.PartyRelationshipBuilder completePartyRelationship() {
		return PartyRelationshipEntity.builder()
				       .comment("PartyRelationshipTestDataBuilder " + UUID.randomUUID())
				       .fromDate(LocalDate.now());
	}
}
