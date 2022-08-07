package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseType;

import java.util.UUID;

public class CaseTypeTestDataBuilder {

	public static CaseType.CaseTypeBuilder completeCaseType() {
		return CaseType.builder()
				       .description("CaseTypeTestDataBuilder " + UUID.randomUUID());
	}
}
