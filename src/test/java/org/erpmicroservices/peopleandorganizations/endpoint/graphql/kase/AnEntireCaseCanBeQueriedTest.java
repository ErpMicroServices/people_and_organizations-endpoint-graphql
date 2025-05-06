package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;


@SpringBootTest
@AutoConfigureGraphQlTester
public class AnEntireCaseCanBeQueriedTest extends KaseGwtTemplate {
	private final String casesGraphQlPath = "cases.edges[0].node.";

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCaseExists();
		partyEntity1 = aPartyExists();
		partyEntity2 = aPartyExists();
		aCaseRoleExists();
		aContactMechanismTypeExists();
		aCommunicationEventStatusTypeExists();
		aCommunicationEventTypeExists();
		aPartyRoleTypeExists();
		partyRoleEntity1 = aPartyRoleExists(partyEntity1);
		partyRoleEntity2 = aPartyRoleExists(partyEntity2);
		aPartyRelationshipExists();
		aCommunicationEventExists();


	}

	@Test
	@Override
	public void when() {

		response = this.graphQlTester.documentName("caseQuery")
				           .operationName("caseQuery")
				           .variable("rolesPageInfo", pageInfoSortingOn("fromDate"))
				           .variable("communicationEventPageInfo", pageInfoSortingOn("started"))
				           .variable("casePageInfo", pageInfoSortingOn("description"))
				           .execute();

	}

	@AfterEach
	@Override
	public void then() {
		String caseRolesGraphQlPath = casesGraphQlPath + "roles.edges[0].node.";
		String communicationEventsGraphQlPath = casesGraphQlPath + "communicationEvents.edges[0].node.";
		response
				.path(casesGraphQlPath + "id").entity(UUID.class).isEqualTo(aCaseEntity.getId())
				.path(casesGraphQlPath + "description").entity(String.class).isEqualTo(aCaseEntity.getDescription())
				.path(casesGraphQlPath + "startedAt").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(aCaseEntity.getStartedAt()))
				.path(casesGraphQlPath + "caseTypeEntity.id").entity(UUID.class).isEqualTo(caseTypeEntity.getId())
				.path(casesGraphQlPath + "caseTypeEntity.description").entity(String.class).isEqualTo(caseTypeEntity.getDescription())
				.path(casesGraphQlPath + "description").entity(String.class).isEqualTo(aCaseEntity.getDescription())
				.path(casesGraphQlPath + "caseStatusTypeEntity.id").entity(UUID.class).isEqualTo(caseStatusTypeEntity.getId())
				.path(casesGraphQlPath + "caseStatusTypeEntity.description").entity(String.class).isEqualTo(caseStatusTypeEntity.getDescription())
				.path(caseRolesGraphQlPath + "id").entity(UUID.class).isEqualTo(caseRoleEntity.getId())
				.path(caseRolesGraphQlPath + "caseRoleTypeEntity.id").entity(UUID.class).isEqualTo(caseRoleTypeEntity.getId())
				.path(caseRolesGraphQlPath + "caseRoleTypeEntity.description").entity(String.class).isEqualTo(caseRoleTypeEntity.getDescription())
				.path(caseRolesGraphQlPath + "partyEntity.id").entity(UUID.class).isEqualTo(partyEntity.getId())
				.path(caseRolesGraphQlPath + "fromDate").entity(LocalDate.class).isEqualTo(caseRoleEntity.getFromDate())
				.path(caseRolesGraphQlPath + "thruDate").valueIsNull()
				.path(communicationEventsGraphQlPath + "id").entity(UUID.class).isEqualTo(communicationEventEntity.getId())
				.path(communicationEventsGraphQlPath + "started").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(communicationEventEntity.getStarted()))
				.path(communicationEventsGraphQlPath + "ended").valueIsNull()
				.path(communicationEventsGraphQlPath + "note").entity(String.class).isEqualTo(communicationEventEntity.getNote())
				.path(communicationEventsGraphQlPath + "contactMechanismTypeEntity.id").entity(UUID.class).isEqualTo(contactMechanismTypeEntity.getId())
				.path(communicationEventsGraphQlPath + "contactMechanismTypeEntity.description").entity(String.class).isEqualTo(contactMechanismTypeEntity.getDescription())
				.path(communicationEventsGraphQlPath + "communicationEventStatusTypeEntity.id").entity(UUID.class).isEqualTo(communicationEventStatusTypeEntity.getId())
				.path(communicationEventsGraphQlPath + "communicationEventStatusTypeEntity.description").entity(String.class).isEqualTo(communicationEventStatusTypeEntity.getDescription())
				.path(communicationEventsGraphQlPath + "communicationEventTypeEntity.id").entity(UUID.class).isEqualTo(communicationEventTypeEntity.getId())
				.path(communicationEventsGraphQlPath + "communicationEventTypeEntity.description").entity(String.class).isEqualTo(communicationEventTypeEntity.getDescription())
				.path(communicationEventsGraphQlPath + "partyRelationshipEntity.id").entity(UUID.class).isEqualTo(partyRelationshipEntity.getId());
	}

}
