package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCommunicationEvent {

	@NotNull
	private ZonedDateTime started;
	@NotNull
	private ZonedDateTime ended;
	@NotEmpty
	private String note;
	@NotNull
	private UUID contactMechanismTypeId;
	@NotNull
	private UUID communicationEventStatusTypeId;
	@NotNull
	private UUID communicationEventTypeId;
	@NotNull
	private UUID partyRelationshipId;
}
