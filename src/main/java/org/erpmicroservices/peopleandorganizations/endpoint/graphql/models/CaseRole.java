package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class CaseRole {

	@Id
	@NotNull
	private UUID id;

	@ManyToOne
	private CaseRoleType caseRoleType;

	@ManyToOne
	private Party party;

	@NotNull
	private LocalDate fromDate;

	private LocalDate thruDate;
}
