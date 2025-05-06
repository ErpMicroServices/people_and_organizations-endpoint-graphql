package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
