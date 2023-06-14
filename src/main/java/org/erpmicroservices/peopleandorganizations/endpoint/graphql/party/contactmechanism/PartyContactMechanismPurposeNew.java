package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class PartyContactMechanismPurposeNew {

	@NotNull
	private LocalDate fromDate;
    private LocalDate thruDate;
    @NotNull
    private UUID purposeType;
}
