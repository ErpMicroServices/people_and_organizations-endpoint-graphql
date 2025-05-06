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
	public void given() {
		super.given();
		aCaseExists();
		aCaseRoleTypeExists();
		aPartyTypeExists();
		partyEntity1 = aPartyExists();
		caseRoleEntity = completeCaseRole()
				           .caseId(aCaseEntity.getId())
				           .caseRoleTypeId(caseRoleTypeEntity.getId())
				           .partyId(partyEntity1.getId())
				           .build();
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CaseAddRole")
				           .operationName("AddCaseRole")
				           .variable("newCaseRole", Map.of(
						           "caseId", caseRoleEntity.getCaseId(),
						           "caseRoleTypeId", caseRoleEntity.getCaseRoleTypeId(),
						           "partyId", caseRoleEntity.getPartyId(),
						           "fromDate", caseRoleEntity.getFromDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
				           ))
				           .variable("rolesPageInfo", pageInfoSortingOn("fromDate"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response.path(addCaseRoleGraphQlPath + "id").hasValue()
				.path(addCaseRoleGraphQlPath + "description").entity(String.class).isEqualTo(aCaseEntity.getDescription())
				.path(addCaseRoleGraphQlPath + "startedAt").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(aCaseEntity.getStartedAt()))
				.path(rolesGraphQlPath + "id").hasValue()
				.path(rolesGraphQlPath + "fromDate").entity(LocalDate.class).isEqualTo(caseRoleEntity.getFromDate())
				.path(rolesGraphQlPath + "caseRoleTypeEntity.id").entity(UUID.class).isEqualTo(caseRoleTypeEntity.getId())
				.path(rolesGraphQlPath + "caseRoleTypeEntity.description").entity(String.class).isEqualTo(caseRoleTypeEntity.getDescription())
				.path(rolesGraphQlPath + "partyEntity.id").entity(UUID.class).isEqualTo(caseRoleEntity.getPartyId());

	}
}
