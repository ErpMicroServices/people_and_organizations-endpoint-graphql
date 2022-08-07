package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleType;

import java.util.UUID;

public class PartyRoleTypeDataBuilder {

	public static PartyRoleType.PartyRoleTypeBuilder completePartyRoleType() {
		return PartyRoleType.builder()
				       .description("PartyRoleTypeDataBuilder " + UUID.randomUUID());
	}
}
