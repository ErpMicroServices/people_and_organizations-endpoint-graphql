package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NewParty {
	private String comment;
	private UUID partyTypeId;
}
