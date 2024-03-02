package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRole;

import java.time.LocalDate;

public class PartyRoleTestDataBuilder {

	public static PartyRole.PartyRoleBuilder completePartyRole() {
		return PartyRole.builder()
				       .fromDate(LocalDate.now());
	}
}
