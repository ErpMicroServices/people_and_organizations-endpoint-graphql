package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

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
@Table(name = "case_role")
public class CaseRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotNull
	private UUID caseId;

	@NotNull
	private UUID caseRoleTypeId;

	@NotNull
	private UUID partyId;

	@NotNull
	private LocalDate fromDate;

	private LocalDate thruDate;
}
