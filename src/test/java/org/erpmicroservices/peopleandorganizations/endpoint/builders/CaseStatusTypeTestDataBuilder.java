package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseStatusType;

import java.util.UUID;

public class CaseStatusTypeTestDataBuilder {
	public static CaseStatusType.CaseStatusTypeBuilder completeCaseStatusType() {
		return CaseStatusType.builder()
				       .description("CaseStatusTypeTestDataBuilder " + UUID.randomUUID());
	}
}
