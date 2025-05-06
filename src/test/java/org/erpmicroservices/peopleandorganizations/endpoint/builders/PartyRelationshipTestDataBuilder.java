package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipEntity;

import java.time.LocalDate;
import java.util.UUID;

public class PartyRelationshipTestDataBuilder {

	public static PartyRelationshipEntity.PartyRelationshipEntityBuilder completePartyRelationship() {
		return PartyRelationshipEntity.builder()
				       .fromDate(LocalDate.now());
	}
}
