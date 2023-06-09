package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@NotNull
	private UUID fromPartyRoleId;

	@NotNull
	private UUID toPartyRoleId;

	@NotNull
	private UUID partyRelationshipTypeId;

	@NotNull
	private UUID partyRelationshipStatusTypeId;

	@NotNull
	private UUID priorityTypeId;
}
