package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationship;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
