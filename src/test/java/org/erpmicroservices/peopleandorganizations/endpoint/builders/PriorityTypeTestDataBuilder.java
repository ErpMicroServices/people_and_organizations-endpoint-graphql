package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PriorityType;

import java.util.UUID;

public class PriorityTypeTestDataBuilder {
	public static PriorityType.PriorityTypeBuilder completePriorityType() {
		return PriorityType.builder()
				       .description("PriorityTypeTestDataBuilder " + UUID.randomUUID());
	}
}
