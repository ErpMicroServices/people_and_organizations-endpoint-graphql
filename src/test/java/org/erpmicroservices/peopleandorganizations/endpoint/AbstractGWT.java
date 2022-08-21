package org.erpmicroservices.peopleandorganizations.endpoint;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurposeRepository;
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

	@Autowired
	protected PartyContactMechanismRepository partyContactMechanismRepository;

	@Autowired
	protected PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository;

	@Autowired
	protected PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository;

	protected Party party;

	protected ContactMechanismType contactMechanismType;
	protected PartyRole partyRole;
	protected CommunicationEventStatusType communicationEventStatusType;
	protected CommunicationEventType communicationEventType;
	protected CommunicationEvent communicationEvent;
	protected PartyRoleType partyRoleType;
	protected PartyRelationship partyRelationship;
	protected PartyRelationshipType partyRelationshipType;
	protected PartyRelationshipStatusType partyRelationshipStatusType;
	protected PriorityType priorityType;


	@BeforeEach
	public void given() {
		theDatabaseIsEmpty();
	}


	@Test
	abstract public void when();

	@AfterEach
	abstract public void then();

	@NotNull
	protected static Map<String, String> pageInfoSortingOn(final String sortBy) {
		return Map.of("pageNumber", "0"
				, "pageSize", "100"
				, "sortBy", sortBy
				, "sortDirection", "ASC");
	}

	private void theDatabaseIsEmpty() {
		communicationEventRepository.deleteAll();
		partyRelationshipRepository.deleteAll();
		caseRoleRepository.deleteAll();
		partyRoleRepository.deleteAll();
		facilityRoleRepository.deleteAll();
		facilityContactMechanismRepository.deleteAll();
		partyContactMechanismRepository.deleteAll();
		contactMechanismGeographicBoundaryRepository.deleteAll();
		contactMechanismRepository.deleteAll();
		geographicBoundaryRepository.deleteAll();


		partyRepository.deleteAll();
		caseRepository.deleteAll();
		communicationEventRepository.deleteAll();
		facilityRepository.deleteAll();

		caseRoleTypeRepository.deleteAll(caseRoleTypeRepository.findCaseRoleTypeByParentIdIsNotNull().stream().toList());
		caseRoleTypeRepository.deleteAll();
		partyTypeRepository.deleteAll();
		caseStatusTypeRepository.deleteAll(caseStatusTypeRepository.findCaseStatusTypeByParentIdIsNotNull().stream().toList());
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
		partyContactMechanismPurposeRepository.deleteAll();
	}

	protected Party aPartyExists() {
		if (partyType == null) {
			aPartyTypeExists();
		}
		party = partyRepository.save(completeParty()
				                             .partyTypeId(partyType.getId())
				                             .build());
		return party;
	}

	protected PartyType aPartyTypeExists() {
		partyType = partyTypeRepository.save(completePartyType().build());
		return partyType;
	}

	protected PartyRole aPartyRoleExists(final Party party) {
		if (partyRoleType == null) {
			aPartyRoleTypeExists();
		}
		partyRole = partyRoleRepository.save(completePartyRole()
				                                     .partyId(party.getId())
				                                     .partyRoleTypeId(partyRoleType.getId())
				                                     .build());
		return partyRole;
	}

	protected PartyRoleType aPartyRoleTypeExists() {
		partyRoleType = partyRoleTypeRepository.save(completePartyRoleType().build());
		return partyRoleType;
	}

	protected ContactMechanismType aContactMechanismTypeExists() {
		contactMechanismType = contactMechanismTypeRepository.save(completeContactMechanismType().build());
		return contactMechanismType;
	}

}
