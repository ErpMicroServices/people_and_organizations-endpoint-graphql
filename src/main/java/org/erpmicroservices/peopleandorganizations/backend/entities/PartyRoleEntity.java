package org.erpmicroservices.peopleandorganizations.backend.entities;

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
@Table(name = "party_role")
public class PartyRoleEntity {
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
