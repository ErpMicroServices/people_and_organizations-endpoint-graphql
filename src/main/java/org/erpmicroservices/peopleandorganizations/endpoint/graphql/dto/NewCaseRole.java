package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCaseRole {
	private UUID caseId;
	private UUID caseRoleTypeId;
	private UUID partyId;
	private String fromDate;
}
