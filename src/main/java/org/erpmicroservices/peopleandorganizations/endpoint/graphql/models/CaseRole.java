package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

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
public class CaseRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "case_id")
	private Case kase;

	@ManyToOne
	private CaseRoleType caseRoleType;

	@ManyToOne
	private Party party;

	@NotNull
	private LocalDate fromDate;

	private LocalDate thruDate;
}
