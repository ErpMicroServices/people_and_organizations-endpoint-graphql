package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PartyContactMechanismNew {
	@NotNull
	private LocalDate fromDate;

	private LocalDate thruDate;

	@NotNull
	private boolean doNotSolicitIndicator;

	private String comment;

	@NotNull
	private UUID partyId;

	@NotNull
	private UUID contactMechanismId;

	@NotNull
	private UUID purposeId;

}
