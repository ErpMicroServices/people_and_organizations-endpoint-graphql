package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;
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
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseRoleTypeTestDataBuilder.completeCaseRoleType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseStatusTypeTestDataBuilder.completeCaseStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTestDataBuilder.completeCase;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTypeTestDataBuilder.completeCaseType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTestDataBuilder.completeParty;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTypeTestDataBuilder.completePartyType;

@SpringBootTest
@AutoConfigureGraphQlTester
public class ARoleCanBeAddedToACaseTest extends AbstractGWT {
	private static final String addCaseRoleGraphQlPath = "addCaseRole.";
	private static final String rolesGraphQlPath = addCaseRoleGraphQlPath + "roles.edges[0].node.";
	private CaseType caseType;
	private CaseStatusType caseStatusType;
	private Case aCase;
	private CaseRoleType caseRoleType;
	private Party party;
	private CaseRole caseRole;
	private PartyType partyType;

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		caseType = caseTypeRepository.save(completeCaseType().build());
		caseStatusType = caseStatusTypeRepository.save(completeCaseStatusType().build());
		aCase = completeCase()
				        .caseTypeId(caseType.getId())
				        .caseStatusTypeId(caseStatusType.getId())
				        .build();
		aCase = caseRepository.save(aCase);
		caseRoleType = caseRoleTypeRepository.save(completeCaseRoleType().build());
		partyType = partyTypeRepository.save(completePartyType().build());
		party = partyRepository.save(completeParty()
				                             .partyTypeId(partyType.getId())
				                             .build());
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
