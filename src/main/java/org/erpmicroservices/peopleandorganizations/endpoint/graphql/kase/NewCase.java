package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
	private String startedAt;
	@NotNull
	private UUID caseStatusTypeId;
}
