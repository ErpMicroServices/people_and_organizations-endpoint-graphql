package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "case_id")
	private Case kase;

	private ZonedDateTime started;
	private ZonedDateTime ended;
	private String note;

	@ManyToOne
	private ContactMechanismType contactMechanismType;

	@ManyToOne
	private PartyRelationship partyRelationship;

	@ManyToOne
	private CommunicationEventStatusType communicationEventStatusType;

	@ManyToOne
	private CommunicationEventType communicationEventType;
}
