package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseType;

import java.util.UUID;

public class CaseTypeTestDataBuilder {

	public static CaseType.CaseTypeBuilder completeCaseType() {
		return CaseType.builder()
				       .description("CaseTypeTestDataBuilder " + UUID.randomUUID());
	}
}
