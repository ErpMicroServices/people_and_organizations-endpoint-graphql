package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismNew;

import javax.validation.constraints.NotNull;
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
	private ContactMechanismNew contactMechanism;

	@NotNull
	private PartyContactMechanismPurposeNew newPurpose;

}
