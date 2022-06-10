package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "kase")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Case {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotEmpty
	private String description;

	private ZonedDateTime startedAt;

	@ManyToOne
	private CaseType caseType;

	@ManyToOne
	private CaseStatusType caseStatusType;

	@OneToMany(mappedBy = "kase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CaseRole> roles = new ArrayList<>();

	@OneToMany(mappedBy = "kase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommunicationEvent> communicationEvents;

	public void addRole(final CaseRole caseRole) {
		caseRole.setKase(this);
		roles.add(caseRole);
	}

	public void expireRoleWithId(final UUID caseRoleId) {
		roles.stream().filter(r -> r.getId().equals(caseRoleId)).forEach(role -> role.setThruDate(LocalDate.now()));
	}
}
