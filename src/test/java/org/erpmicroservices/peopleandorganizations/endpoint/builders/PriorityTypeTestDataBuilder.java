package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PriorityTypeEntity;

import java.util.UUID;

public class PriorityTypeTestDataBuilder {
	public static PriorityTypeEntity.PriorityTypeBuilder completePriorityType() {
		return PriorityTypeEntity.builder()
				       .description("PriorityTypeTestDataBuilder " + UUID.randomUUID());
	}
}
