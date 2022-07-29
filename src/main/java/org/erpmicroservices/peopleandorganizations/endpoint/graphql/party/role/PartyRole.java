package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "party_role")
public class PartyRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotNull
	private LocalDate fromDate;

	private LocalDate thruDate;

	@NotNull
	private UUID partyRoleTypeId;

	@NotNull
	private UUID partyId;

}
