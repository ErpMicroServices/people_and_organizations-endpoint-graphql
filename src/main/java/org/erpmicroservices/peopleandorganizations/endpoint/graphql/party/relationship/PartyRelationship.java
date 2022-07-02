package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.PriorityType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "party_relationship")
public class PartyRelationship {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotNull
	private LocalDate fromDate;
	private LocalDate thruDate;
	private String comment;
	@ManyToOne
	@NotNull
	private PartyRole fromPartyRole;
	@ManyToOne
	@NotNull
	private PartyRole toPartyRole;

	@ManyToOne
	@JoinColumn(name = "party_relationship_type_id")
	@NotNull
	private PartyRelationshipType relationshipType;

	@ManyToOne
	@JoinColumn(name = "party_relationship_status_type_id")
	@NotNull
	private PartyRelationshipStatusType statusType;

	@ManyToOne
	@JoinColumn(name = "priority_type_id")
	@NotNull
	private PriorityType priorityType;
}
