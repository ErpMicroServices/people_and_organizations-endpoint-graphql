package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFacilityContactMechanism {
	private UUID facilityId;
	private UUID contactMechanismId;
	private LocalDate fromDate;
	private LocalDate thruDate;
}
