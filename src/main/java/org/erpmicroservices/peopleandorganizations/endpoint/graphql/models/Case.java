package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "kase")
@Data
public class Case {
	@Id
	@NotNull
	private UUID id;

	@NotEmpty
	private String description;

	private ZonedDateTime startedAt;

	@ManyToOne
	private CaseType caseType;

	@ManyToOne
	private CaseStatusType caseStatusType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "case_id")
	private List<CaseRole> roles = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "case_id")
	private List<CommunicationEvent> communicationEvents;
}
