package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleType;

import java.util.UUID;

public class CaseRoleTypeTestDataBuilder {

	public static CaseRoleType.CaseRoleTypeBuilder completeCaseRoleType() {
		return CaseRoleType.builder()
				       .description("CaseRoleTypeTestDataBuilder" + UUID.randomUUID());
	}
}
