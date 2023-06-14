package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "communication_event")
public class CommunicationEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	private ZonedDateTime started;
	private ZonedDateTime ended;
	private String note;

	@NotNull
	private UUID contactMechanismTypeId;

	private UUID partyRelationshipId;

	@NotNull
	private UUID communicationEventStatusTypeId;

	@NotNull
	private UUID communicationEventTypeId;

	private UUID caseId;
}
