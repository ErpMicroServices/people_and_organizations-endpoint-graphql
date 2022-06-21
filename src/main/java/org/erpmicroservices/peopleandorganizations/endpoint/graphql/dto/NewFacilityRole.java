package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFacilityRole {
	private UUID partyId;
	private UUID facilityId;
	private UUID facilityRoleTypeId;
	private LocalDate fromDate;
	private LocalDate thruDate;
}
