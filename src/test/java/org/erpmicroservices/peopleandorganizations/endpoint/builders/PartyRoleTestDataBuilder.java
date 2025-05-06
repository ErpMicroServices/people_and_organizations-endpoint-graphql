package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRoleEntity;

import java.time.LocalDate;

public class PartyRoleTestDataBuilder {

	public static PartyRoleEntity.PartyRoleEntityBuilder completePartyRole() {
		return PartyRoleEntity.builder()
				       .fromDate(LocalDate.now());
	}
}
