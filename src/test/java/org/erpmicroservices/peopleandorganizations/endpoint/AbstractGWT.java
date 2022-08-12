package org.erpmicroservices.peopleandorganizations.endpoint;

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
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Map;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.ContactMechanismTypeDataBuilder.completeContactMechanismType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRoleTestDataBuilder.completePartyRole;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRoleTypeDataBuilder.completePartyRoleType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTestDataBuilder.completeParty;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTypeTestDataBuilder.completePartyType;

abstract public class AbstractGWT {
	protected GraphQlTester.Response response;
	@Autowired
	protected GraphQlTester graphQlTester;
	@Autowired
	protected CaseStatusTypeRepository caseStatusTypeRepository;
	@Autowired
	protected CaseTypeRepository caseTypeRepository;
	@Autowired
	protected CaseRepository caseRepository;
	@Autowired
	protected PartyTypeRepository partyTypeRepository;
	@Autowired
	protected PartyRepository partyRepository;
	@Autowired
	protected CaseRoleTypeRepository caseRoleTypeRepository;
	@Autowired
	protected CaseRoleRepository caseRoleRepository;
	@Autowired
	protected ContactMechanismTypeRepository contactMechanismTypeRepository;
	@Autowired
	protected PartyRoleTypeRepository partyRoleTypeRepository;
	@Autowired
	protected PartyRoleRepository partyRoleRepository;
	@Autowired
	protected CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;
	@Autowired
	protected CommunicationEventTypeRepository communicationEventTypeRepository;
	@Autowired
	protected PartyRelationshipTypeRepository partyRelationshipTypeRepository;
	@Autowired
	protected PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository;
	@Autowired
	protected PriorityTypeRepository priorityTypeRepository;
	@Autowired
	protected PartyRelationshipRepository partyRelationshipRepository;
	@Autowired
	protected CommunicationEventRepository communicationEventRepository;
	@Autowired
	protected FacilityRepository facilityRepository;
	@Autowired
	protected FacilityTypeRepository facilityTypeRepository;
	@Autowired
	protected FacilityRoleTypeRepository facilityRoleTypeRepository;
	@Autowired
	protected FacilityRoleRepository facilityRoleRepository;
	@Autowired
	protected FacilityContactMechanismRepository facilityContactMechanismRepository;
	protected PartyType partyType;
	@Autowired
	protected ContactMechanismRepository contactMechanismRepository;

	@Autowired
	protected GeographicBoundaryRepository geographicBoundaryRepository;

	@Autowired
	protected GeographicBoundaryTypeRepository geographicBoundaryTypeRepository;

	@Autowired
	protected ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository;
	protected Party party1;
	protected ContactMechanismType contactMechanismType;
	protected PartyRole partyRole1;
	protected CommunicationEventStatusType communicationEventStatusType;
	protected CommunicationEventType communicationEventType;
	protected CommunicationEvent communicationEvent;
	protected PartyRoleType partyRoleType;
	protected Party party2;
	protected PartyRole partyRole2;
	protected PartyRelationship partyRelationship;
	protected PartyRelationshipType partyRelationshipType;
	protected PartyRelationshipStatusType partyRelationshipStatusType;
	protected PriorityType priorityType;


	@BeforeEach
	public void givenTheFollowing() {
		emptyTheDatabase();
	}


	@Test
	abstract public void whenThisHappens();

	@AfterEach
	abstract public void thenThisIsExpected();

	@NotNull
	protected static Map<String, String> pageInfoSortingOn(final String fromDate) {
		return Map.of("pageNumber", "0"
				, "pageSize", "100"
				, "sortBy", fromDate
				, "sortDirection", "ASC");
	}

	private void emptyTheDatabase() {
		communicationEventRepository.deleteAll();
		partyRelationshipRepository.deleteAll();
		caseRoleRepository.deleteAll();
		partyRoleRepository.deleteAll();
		facilityRoleRepository.deleteAll();
		facilityContactMechanismRepository.deleteAll();
		contactMechanismGeographicBoundaryRepository.deleteAll();
		contactMechanismRepository.deleteAll();
		geographicBoundaryRepository.deleteAll();

		partyRepository.deleteAll();
		caseRepository.deleteAll();
		communicationEventRepository.deleteAll();
		facilityRepository.deleteAll();

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
		facilityRoleTypeRepository.deleteAll();
		facilityTypeRepository.deleteAll();
		geographicBoundaryTypeRepository.deleteAll();
	}

	protected Party aPartyExists() {
		if (partyType == null) {
			aPartyTypeExists();
		}
		return partyRepository.save(completeParty()
				                            .partyTypeId(partyType.getId())
				                            .build());
	}

	protected void aPartyTypeExists() {
		partyType = partyTypeRepository.save(completePartyType().build());
	}

	protected PartyRole aPartyRoleExists(final Party party) {
		if (partyRoleType == null) {
			aPartyRoleTypeExists();
		}
		return partyRoleRepository.save(completePartyRole()
				                                .partyId(party.getId())
				                                .partyRoleTypeId(partyRoleType.getId())
				                                .build());
	}

	protected void aPartyRoleTypeExists() {
		partyRoleType = partyRoleTypeRepository.save(completePartyRoleType().build());
	}

	protected void aContactMechanismTypeExists() {
		contactMechanismType = contactMechanismTypeRepository.save(completeContactMechanismType().build());
	}

}
