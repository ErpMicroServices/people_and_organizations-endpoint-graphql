package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;


@SpringBootTest
@AutoConfigureGraphQlTester
public class AnEntireCaseCanBeQueriedTest extends KaseGwtTemplate {
	private final String casesGraphQlPath = "cases.edges[0].node.";
	private final String caseRolesGraphQlPath = casesGraphQlPath + "roles.edges[0].node.";
	private final String communicationEventsGraphQlPath = casesGraphQlPath + "communicationEvents.edges[0].node.";

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		aCaseTypeExists();
		aCaseStatusTypeExists();
		aCaseExists();
		aPartyTypeExists();
		aPartyExists();
		party1 = aPartyExists();
		party2 = aPartyExists();
		aCaseRoleTypeExists();
		aCaseRoleExists();
		aContactMechanismTypeExists();
		aCommunicationEventStatusTypeExists();
		aCommunicationEventTypeExists();
		aPartyRoleTypeExists();
		partyRole1 = aPartyRoleExists(party1);
		partyRole2 = aPartyRoleExists(party2);
		aPartyRelationshipTypeExists();
		aPartyRelationshiopStatusTypeExists();
		aPriorityTypeExists();
		aPartyRelationshipExists();
		aCommunicationEventExists();


	}

	@Test
	@Override
	public void whenThisHappens() {

		response = this.graphQlTester.documentName("caseQuery")
				           .operationName("caseQuery")
				           .variable("rolesPageInfo", Map.of("pageNumber", "0"
						           , "pageSize", "100"
						           , "sortBy", "fromDate"
						           , "sortDirection", "ASC"))
				           .variable("communicationEventPageInfo", Map.of("pageNumber", "0"
						           , "pageSize", "100"
						           , "sortBy", "started"
						           , "sortDirection", "ASC"))
				           .variable("casePageInfo", Map.of("pageNumber", "0"
						           , "pageSize", "100"
						           , "sortBy", "description"
						           , "sortDirection", "ASC"))
				           .execute();

	}

	@AfterEach
	@Override
	public void thenThisIsExpected() {
		response
				.path(casesGraphQlPath + "id").entity(UUID.class).isEqualTo(aCase.getId())
				.path(casesGraphQlPath + "description").entity(String.class).isEqualTo(aCase.getDescription())
				.path(casesGraphQlPath + "startedAt").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(aCase.getStartedAt()))
				.path(casesGraphQlPath + "caseType.id").entity(UUID.class).isEqualTo(caseType.getId())
				.path(casesGraphQlPath + "caseType.description").entity(String.class).isEqualTo(caseType.getDescription())
				.path(casesGraphQlPath + "description").entity(String.class).isEqualTo(aCase.getDescription())
				.path(casesGraphQlPath + "caseStatusType.id").entity(UUID.class).isEqualTo(caseStatusType.getId())
				.path(casesGraphQlPath + "caseStatusType.description").entity(String.class).isEqualTo(caseStatusType.getDescription())
				.path(caseRolesGraphQlPath + "id").entity(UUID.class).isEqualTo(caseRole.getId())
				.path(caseRolesGraphQlPath + "caseRoleType.id").entity(UUID.class).isEqualTo(caseRoleType.getId())
				.path(caseRolesGraphQlPath + "caseRoleType.description").entity(String.class).isEqualTo(caseRoleType.getDescription())
				.path(caseRolesGraphQlPath + "party.id").entity(UUID.class).isEqualTo(party1.getId())
				.path(caseRolesGraphQlPath + "fromDate").entity(LocalDate.class).isEqualTo(caseRole.getFromDate())
				.path(caseRolesGraphQlPath + "thruDate").valueIsNull()
				.path(communicationEventsGraphQlPath + "id").entity(UUID.class).isEqualTo(communicationEvent.getId())
				.path(communicationEventsGraphQlPath + "started").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(communicationEvent.getStarted()))
				.path(communicationEventsGraphQlPath + "ended").valueIsNull()
				.path(communicationEventsGraphQlPath + "note").entity(String.class).isEqualTo(communicationEvent.getNote())
				.path(communicationEventsGraphQlPath + "contactMechanismType.id").entity(UUID.class).isEqualTo(contactMechanismType.getId())
				.path(communicationEventsGraphQlPath + "contactMechanismType.description").entity(String.class).isEqualTo(contactMechanismType.getDescription())
				.path(communicationEventsGraphQlPath + "communicationEventStatusType.id").entity(UUID.class).isEqualTo(communicationEventStatusType.getId())
				.path(communicationEventsGraphQlPath + "communicationEventStatusType.description").entity(String.class).isEqualTo(communicationEventStatusType.getDescription())
				.path(communicationEventsGraphQlPath + "communicationEventType.id").entity(UUID.class).isEqualTo(communicationEventType.getId())
				.path(communicationEventsGraphQlPath + "communicationEventType.description").entity(String.class).isEqualTo(communicationEventType.getDescription())
				.path(communicationEventsGraphQlPath + "partyRelationship.id").entity(UUID.class).isEqualTo(partyRelationship.getId());
	}

}
