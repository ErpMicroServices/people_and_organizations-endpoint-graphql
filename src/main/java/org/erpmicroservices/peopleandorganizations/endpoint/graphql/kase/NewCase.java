package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCase {
	@NotEmpty
	private String description;
	@NotNull
	private UUID caseTypeId;
	@NotNull
	private ZonedDateTime startedAt;
	@NotNull
	private UUID caseStatusTypeId;
}
