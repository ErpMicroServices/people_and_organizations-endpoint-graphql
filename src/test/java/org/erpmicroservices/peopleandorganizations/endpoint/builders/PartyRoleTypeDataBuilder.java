package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRoleTypeEntity;

import java.util.UUID;

public class PartyRoleTypeDataBuilder {

	public static PartyRoleTypeEntity.PartyRoleTypeBuilder completePartyRoleType() {
		return PartyRoleTypeEntity.builder()
				       .description("PartyRoleTypeDataBuilder " + UUID.randomUUID());
	}
}
