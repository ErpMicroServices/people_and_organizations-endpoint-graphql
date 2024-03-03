package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleTypeEntity;

import java.util.UUID;

public class CaseRoleTypeTestDataBuilder {

	public static CaseRoleTypeEntity.CaseRoleTypeEntityBuilder completeCaseRoleType() {
		return CaseRoleTypeEntity.builder()
				       .description("CaseRoleTypeTestDataBuilder" + UUID.randomUUID());
	}
}
