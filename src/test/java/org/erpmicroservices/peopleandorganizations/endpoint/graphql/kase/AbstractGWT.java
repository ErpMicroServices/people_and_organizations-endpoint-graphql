package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.test.tester.GraphQlTester;

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
	protected CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;
	@Autowired
	protected CommunicationEventTypeRepository communicationEventTypeRepository;
	@Autowired
	protected PartyRoleTypeRepository partyRoleTypeRepository;
	@Autowired
	protected PartyRoleRepository partyRoleRepository;
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

	@BeforeEach
	public void givenTheFollowing() {
		emptyTheDatabase();
	}

	@Test
	abstract public void whenThisHappens();

	@AfterEach
	abstract public void thenThisIsExpected();

	private void emptyTheDatabase() {
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
}
