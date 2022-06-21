package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFacility {
	@NotNull
	private UUID facilityTypeId;
	@NotEmpty
	private String description;
	private UUID partOfId;
	private Long squareFootage;
}
