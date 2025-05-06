package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseTypeEntity;

import java.util.UUID;

public class CaseTypeTestDataBuilder {

	public static CaseTypeEntity.CaseTypeBuilder completeCaseType() {
		return CaseTypeEntity.builder()
				       .description("CaseTypeTestDataBuilder " + UUID.randomUUID());
	}
}
