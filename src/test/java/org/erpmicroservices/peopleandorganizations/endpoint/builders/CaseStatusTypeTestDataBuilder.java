package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseStatusTypeEntity;

import java.util.UUID;

public class CaseStatusTypeTestDataBuilder {
	public static CaseStatusTypeEntity.CaseStatusTypeBuilder completeCaseStatusType() {
		return CaseStatusTypeEntity.builder()
				       .description("CaseStatusTypeTestDataBuilder " + UUID.randomUUID());
	}
}
