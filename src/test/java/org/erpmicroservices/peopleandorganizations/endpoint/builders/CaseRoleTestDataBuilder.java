package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleEntity;

import java.time.LocalDate;

public class CaseRoleTestDataBuilder {

	public static CaseRoleEntity.CaseRoleBuilder completeCaseRole() {
		return CaseRoleEntity.builder()
				       .fromDate(LocalDate.now());
	}
}
