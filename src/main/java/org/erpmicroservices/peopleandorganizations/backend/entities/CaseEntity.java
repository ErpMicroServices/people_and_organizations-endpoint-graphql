package org.erpmicroservices.peopleandorganizations.backend.entities;

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
public class CaseEntity {
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
