package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseRoleTestDataBuilder.completeCaseRole;

@SpringBootTest
@AutoConfigureGraphQlTester
public class ARoleCanBeAddedToACaseTest extends KaseGwtTemplate {
	private static final String addCaseRoleGraphQlPath = "addCaseRole.";
	private static final String rolesGraphQlPath = addCaseRoleGraphQlPath + "roles.edges[0].node.";

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		aCaseExists();
		aCaseRoleTypeExists();
		aPartyTypeExists();
		party = aPartyExists();
		caseRole = completeCaseRole()
				           .caseId(aCase.getId())
				           .caseRoleTypeId(caseRoleType.getId())
				           .partyId(party.getId())
				           .build();
	}

	@Test
	@Override
	public void whenThisHappens() {
		response = this.graphQlTester.documentName("AddRoleToCase")
				           .operationName("AddCaseRole")
				           .variable("newCaseRole", Map.of(
						           "caseId", caseRole.getCaseId(),
						           "caseRoleTypeId", caseRole.getCaseRoleTypeId(),
						           "partyId", caseRole.getPartyId(),
						           "fromDate", caseRole.getFromDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
				           ))
				           .variable("rolesPageInfo", Map.of("pageNumber", "0"
						           , "pageSize", "100"
						           , "sortBy", "fromDate"
						           , "sortDirection", "ASC"))
				           .execute();
	}

	@AfterEach
	@Override
	public void thenThisIsExpected() {
		response.path(addCaseRoleGraphQlPath + "id").hasValue()
				.path(addCaseRoleGraphQlPath + "description").entity(String.class).isEqualTo(aCase.getDescription())
				.path(addCaseRoleGraphQlPath + "startedAt").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(aCase.getStartedAt()))
				.path(rolesGraphQlPath + "id").hasValue()
				.path(rolesGraphQlPath + "fromDate").entity(LocalDate.class).isEqualTo(caseRole.getFromDate())
				.path(rolesGraphQlPath + "caseRoleType.id").entity(UUID.class).isEqualTo(caseRoleType.getId())
				.path(rolesGraphQlPath + "caseRoleType.description").entity(String.class).isEqualTo(caseRoleType.getDescription())
				.path(rolesGraphQlPath + "party.id").entity(UUID.class).isEqualTo(caseRole.getPartyId());

	}
}
