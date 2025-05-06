package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRoleEntity;

import java.time.LocalDate;

public class PartyRoleTestDataBuilder {

	public static PartyRoleEntity.PartyRoleBuilder completePartyRole() {
		return PartyRoleEntity.builder()
				       .fromDate(LocalDate.now());
	}
}
