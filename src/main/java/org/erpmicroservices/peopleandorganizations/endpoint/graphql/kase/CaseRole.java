package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
