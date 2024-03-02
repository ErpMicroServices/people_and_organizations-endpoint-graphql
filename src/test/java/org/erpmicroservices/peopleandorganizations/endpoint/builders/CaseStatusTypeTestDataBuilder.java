package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseStatusType;

import java.util.UUID;

public class CaseStatusTypeTestDataBuilder {
	public static CaseStatusType.CaseStatusTypeBuilder completeCaseStatusType() {
		return CaseStatusType.builder()
				       .description("CaseStatusTypeTestDataBuilder " + UUID.randomUUID());
	}
}
