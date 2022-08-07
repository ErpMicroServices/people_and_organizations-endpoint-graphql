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
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.time.LocalDate;
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
public class CaseControllerTest {
	private final String casesGraphQlPath = "cases.edges[0].node.";
	private final String caseRolesGraphQlPath = casesGraphQlPath + "roles.edges[0].node.";
	private final String communicationEventsGraphQlPath = casesGraphQlPath + "communicationEvents.edges[0].node.";
	@Autowired
	private GraphQlTester graphQlTester;
	@Autowired
	private CaseStatusTypeRepository caseStatusTypeRepository;

	@Autowired
	private CaseTypeRepository caseTypeRepository;

	@Autowired
	private CaseRepository caseRepository;

	@Autowired
	private PartyTypeRepository partyTypeRepository;

	@Autowired
	private PartyRepository partyRepository;

	@Autowired
	private CaseRoleTypeRepository caseRoleTypeRepository;

	@Autowired
	private CaseRoleRepository caseRoleRepository;

	@Autowired
	private ContactMechanismTypeRepository contactMechanismTypeRepository;

	@Autowired
	private CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

	@Autowired
	private CommunicationEventTypeRepository communicationEventTypeRepository;

	@Autowired
	private PartyRoleTypeRepository partyRoleTypeRepository;

	@Autowired
	private PartyRoleRepository partyRoleRepository;

	@Autowired
	private PartyRelationshipTypeRepository partyRelationshipTypeRepository;

	@Autowired
	private PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository;

	@Autowired
	private PriorityTypeRepository priorityTypeRepository;

	@Autowired
	private PartyRelationshipRepository partyRelationshipRepository;

	@Autowired
	private CommunicationEventRepository communicationEventRepository;

	@BeforeEach
	public void beforeEach() {
		communicationEventRepository.deleteAll();
		partyRelationshipRepository.deleteAll();
		caseRoleRepository.deleteAll();
		partyRoleRepository.deleteAll();

		partyRepository.deleteAll();
		caseRepository.deleteAll();
		communicationEventRepository.deleteAll();

		caseRoleTypeRepository.deleteAll();
		partyTypeRepository.deleteAll();
		caseStatusTypeRepository.deleteAll();
		caseTypeRepository.deleteAll();
		partyRelationshipTypeRepository.deleteAll();
		priorityTypeRepository.deleteAll();
		partyRelationshipStatusTypeRepository.deleteAll();
		partyRelationshipTypeRepository.deleteAll();
		partyRoleTypeRepository.deleteAll();
		communicationEventStatusTypeRepository.deleteAll();
		communicationEventStatusTypeRepository.deleteAll();
		contactMechanismTypeRepository.deleteAll();
	}

	@Test
	void caseQuery() {
		final CaseType caseType = caseTypeRepository.save(completeCaseType().build());
		final CaseStatusType caseStatusType = caseStatusTypeRepository.save(completeCaseStatusType().build());
		final Case aCase = caseRepository.save(completeCase()
				                                       .caseStatusTypeId(caseStatusType.getId())
				                                       .caseTypeId(caseType.getId())
				                                       .build());
		final PartyType partyType = partyTypeRepository.save(completePartyType().build());
		final Party party1 = partyRepository.save(completeParty()
				                                          .partyTypeId(partyType.getId())
				                                          .build());
		final Party party2 = partyRepository.save(completeParty()
				                                          .partyTypeId(partyType.getId())
				                                          .build());
		final CaseRoleType caseRoleType = caseRoleTypeRepository.save(completeCaseRoleType()
				                                                              .build());
		final CaseRole caseRole = caseRoleRepository.save(completeCaseRole()
				                                                  .caseId(aCase.getId())
				                                                  .partyId(party1.getId())
				                                                  .caseRoleTypeId(caseRoleType.getId())
				                                                  .build());
		final ContactMechanismType contactMechanismType = contactMechanismTypeRepository.save(completeContactMechanismType().build());
		final CommunicationEventStatusType communicationEventStatusType = communicationEventStatusTypeRepository.save(completeCommunicationEventStatusType().build());
		final CommunicationEventType communicationEventType = communicationEventTypeRepository.save(completeCommunicationEventType().build());

		final PartyRoleType partyRoleType = partyRoleTypeRepository.save(completePartyRoleType().build());
		final PartyRole partyRole1 = partyRoleRepository.save(completePartyRole()
				                                                      .partyId(party1.getId())
				                                                      .partyRoleTypeId(partyRoleType.getId())
				                                                      .build());
		final PartyRole partyRole2 = partyRoleRepository.save(completePartyRole()
				                                                      .partyId(party2.getId())
				                                                      .partyRoleTypeId(partyRoleType.getId())
				                                                      .build());
		final PartyRelationshipType partyRelationshipType = partyRelationshipTypeRepository.save(completePartyRelationshipType().build());
		final PartyRelationshipStatusType partyRelationshipStatusType = partyRelationshipStatusTypeRepository.save(completePartyRelationshipStatusType().build());
		final PriorityType priorityType = priorityTypeRepository.save(completePriorityType().build());
		final PartyRelationship partyRelationship = partyRelationshipRepository.save(completePartyRelationship()
				                                                                             .fromPartyRoleId(partyRole1.getId())
				                                                                             .toPartyRoleId(partyRole2.getId())
				                                                                             .partyRelationshipTypeId(partyRelationshipType.getId())
				                                                                             .partyRelationshipStatusTypeId(partyRelationshipStatusType.getId())
				                                                                             .priorityTypeId(priorityType.getId())
				                                                                             .build());
		final CommunicationEvent communicationEvent = communicationEventRepository.save(completeCommunicationEvent()
				                                                                                .caseId(aCase.getId())
				                                                                                .communicationEventStatusTypeId(communicationEventStatusType.getId())
				                                                                                .communicationEventTypeId(communicationEventType.getId())
				                                                                                .contactMechanismTypeId(contactMechanismType.getId())
				                                                                                .partyRelationshipId(partyRelationship.getId())
				                                                                                .build());

		this.graphQlTester.documentName("case")
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
				.execute()
				.path(casesGraphQlPath + "id").entity(UUID.class).isEqualTo(aCase.getId())
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
				.path(communicationEventsGraphQlPath + "started").entity(String.class).isEqualTo(communicationEvent.getStarted().toString())
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
