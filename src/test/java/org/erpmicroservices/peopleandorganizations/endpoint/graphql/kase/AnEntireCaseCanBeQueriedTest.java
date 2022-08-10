package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationship;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationshipStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationshipType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PriorityType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRole;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseRoleTestDataBuilder.completeCaseRole;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseRoleTypeTestDataBuilder.completeCaseRoleType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseStatusTypeTestDataBuilder.completeCaseStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTestDataBuilder.completeCase;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTypeTestDataBuilder.completeCaseType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CommunicationEventStatusTypeDataBuilder.completeCommunicationEventStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CommunicationEventTestDataBuilder.completeCommunicationEvent;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CommunicationEventTypeDataBuilder.completeCommunicationEventType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.ContactMechanismTypeDataBuilder.completeContactMechanismType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRelationshipStatusTypeTestDataBuilder.completePartyRelationshipStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRelationshipTestDataBuilder.completePartyRelationship;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRelationshipTypeTestDataBuilder.completePartyRelationshipType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRoleTestDataBuilder.completePartyRole;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRoleTypeDataBuilder.completePartyRoleType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTestDataBuilder.completeParty;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTypeTestDataBuilder.completePartyType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PriorityTypeTestDataBuilder.completePriorityType;


@SpringBootTest
@AutoConfigureGraphQlTester
public class AnEntireCaseCanBeQueriedTest extends AbstractGWT {
	private final String casesGraphQlPath = "cases.edges[0].node.";
	private final String caseRolesGraphQlPath = casesGraphQlPath + "roles.edges[0].node.";
	private final String communicationEventsGraphQlPath = casesGraphQlPath + "communicationEvents.edges[0].node.";


	private CaseType caseType;
	private CaseStatusType caseStatusType;
	private Case aCase;
	private PartyType partyType;
	private Party party1;
	private Party party2;
	private CaseRoleType caseRoleType;
	private CaseRole caseRole;
	private ContactMechanismType contactMechanismType;
	private CommunicationEventStatusType communicationEventStatusType;
	private CommunicationEventType communicationEventType;
	private PartyRoleType partyRoleType;
	private PartyRole partyRole1;
	private PartyRole partyRole2;
	private PartyRelationshipType partyRelationshipType;
	private PartyRelationshipStatusType partyRelationshipStatusType;
	private PriorityType priorityType;
	private PartyRelationship partyRelationship;
	private CommunicationEvent communicationEvent;

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		caseType = caseTypeRepository.save(completeCaseType().build());
		caseStatusType = caseStatusTypeRepository.save(completeCaseStatusType().build());
		aCase = caseRepository.save(completeCase()
				                            .caseStatusTypeId(caseStatusType.getId())
				                            .caseTypeId(caseType.getId())
				                            .build());
		partyType = partyTypeRepository.save(completePartyType().build());
		party1 = partyRepository.save(completeParty()
				                              .partyTypeId(partyType.getId())
				                              .build());
		party2 = partyRepository.save(completeParty()
				                              .partyTypeId(partyType.getId())
				                              .build());
		caseRoleType = caseRoleTypeRepository.save(completeCaseRoleType()
				                                           .build());
		caseRole = caseRoleRepository.save(completeCaseRole()
				                                   .caseId(aCase.getId())
				                                   .partyId(party1.getId())
				                                   .caseRoleTypeId(caseRoleType.getId())
				                                   .build());
		contactMechanismType = contactMechanismTypeRepository.save(completeContactMechanismType().build());
		communicationEventStatusType = communicationEventStatusTypeRepository.save(completeCommunicationEventStatusType().build());
		communicationEventType = communicationEventTypeRepository.save(completeCommunicationEventType().build());

		partyRoleType = partyRoleTypeRepository.save(completePartyRoleType().build());
		partyRole1 = partyRoleRepository.save(completePartyRole()
				                                      .partyId(party1.getId())
				                                      .partyRoleTypeId(partyRoleType.getId())
				                                      .build());
		partyRole2 = partyRoleRepository.save(completePartyRole()
				                                      .partyId(party2.getId())
				                                      .partyRoleTypeId(partyRoleType.getId())
				                                      .build());
		partyRelationshipType = partyRelationshipTypeRepository.save(completePartyRelationshipType().build());
		partyRelationshipStatusType = partyRelationshipStatusTypeRepository.save(completePartyRelationshipStatusType().build());
		priorityType = priorityTypeRepository.save(completePriorityType().build());
		partyRelationship = partyRelationshipRepository.save(completePartyRelationship()
				                                                     .fromPartyRoleId(partyRole1.getId())
				                                                     .toPartyRoleId(partyRole2.getId())
				                                                     .partyRelationshipTypeId(partyRelationshipType.getId())
				                                                     .partyRelationshipStatusTypeId(partyRelationshipStatusType.getId())
				                                                     .priorityTypeId(priorityType.getId())
				                                                     .build());
		communicationEvent = communicationEventRepository.save(completeCommunicationEvent()
				                                                       .caseId(aCase.getId())
				                                                       .communicationEventStatusTypeId(communicationEventStatusType.getId())
				                                                       .communicationEventTypeId(communicationEventType.getId())
				                                                       .contactMechanismTypeId(contactMechanismType.getId())
				                                                       .partyRelationshipId(partyRelationship.getId())
				                                                       .build());


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
