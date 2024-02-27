package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRole;

import java.time.LocalDate;

public class CaseRoleTestDataBuilder {

	public static CaseRole.CaseRoleBuilder completeCaseRole() {
		return CaseRole.builder()
				       .fromDate(LocalDate.now());
	}
}
