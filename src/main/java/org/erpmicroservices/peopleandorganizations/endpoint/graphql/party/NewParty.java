package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NewParty {
	private String comment;
	@NotNull
	private UUID partyTypeId;
}
