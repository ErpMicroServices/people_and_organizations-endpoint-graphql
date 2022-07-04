package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PartyClassificationUpdate {
	@NotNull
	private UUID id;
	@NotNull
	private String value;
	@NotNull
	private LocalDate fromDate;
	private LocalDate thruDate;
	@NotNull
	private UUID partyClassificationTypeId;
}
