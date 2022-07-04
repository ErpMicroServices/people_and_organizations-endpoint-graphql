package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class UpdateParty {
	@NotNull
	private UUID id;
	private String comment;
	@NotNull
	private UUID partyTypeId;
}
