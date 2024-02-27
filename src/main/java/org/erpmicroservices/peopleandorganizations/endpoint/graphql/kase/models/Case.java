package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
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

	@NotNull
	private UUID caseTypeId;

	@NotNull
	private UUID caseStatusTypeId;
}
