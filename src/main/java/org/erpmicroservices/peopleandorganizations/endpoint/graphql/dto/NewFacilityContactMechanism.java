package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFacilityContactMechanism {
	private UUID facilityId;
	private UUID contactMechanismId;
}
