package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.endpoint.AbstractGWT;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseRoleTestDataBuilder.completeCaseRole;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseRoleTypeTestDataBuilder.completeCaseRoleType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseStatusTypeTestDataBuilder.completeCaseStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTestDataBuilder.completeCase;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTypeTestDataBuilder.completeCaseType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CommunicationEventStatusTypeDataBuilder.completeCommunicationEventStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CommunicationEventTestDataBuilder.completeCommunicationEvent;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CommunicationEventTypeDataBuilder.completeCommunicationEventType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRelationshipStatusTypeTestDataBuilder.completePartyRelationshipStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRelationshipTestDataBuilder.completePartyRelationship;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRelationshipTypeTestDataBuilder.completePartyRelationshipType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PriorityTypeTestDataBuilder.completePriorityType;

public abstract class KaseGwtTemplate extends AbstractGWT {
	protected Case aCase;
	protected CaseRoleType caseRoleType;
	protected CaseRole caseRole;
	protected CaseType caseType;
	protected CaseStatusType caseStatusType;

	protected void aCaseRoleTypeExists() {
		caseRoleType = caseRoleTypeRepository.save(completeCaseRoleType().build());
	}

	protected void aCaseExists() {
		aCaseTypeExists();
		aCaseStatusTypeExists();
		aCase = caseRepository.save(completeCase()
				                            .caseTypeId(caseType.getId())
				                            .caseStatusTypeId(caseStatusType.getId())
				                            .build());
	}

	protected void aCaseTypeExists() {
		caseType = caseTypeRepository.save(completeCaseType().build());
	}

	protected void aCaseStatusTypeExists() {
		caseStatusType = caseStatusTypeRepository.save(completeCaseStatusType().build());
	}

	protected void aCaseRoleExists() {
		if (party1 == null) {
			party1 = aPartyExists();
		}
		if (caseRoleType == null) {
			aCaseRoleTypeExists();
		}
		caseRole = caseRoleRepository.save(completeCaseRole()
				                                   .caseId(aCase.getId())
				                                   .partyId(party1.getId())
				                                   .caseRoleTypeId(caseRoleType.getId())
				                                   .build());
	}

	protected void aCommunicationEventExists() {
		if (communicationEventStatusType == null) {
			aCommunicationEventStatusTypeExists();
		}
		if (communicationEventType == null) {
			aCommunicationEventTypeExists();
		}
		if (contactMechanismType == null) {
			aContactMechanismTypeExists();
		}
		if (partyRelationship == null) {
			aPartyRelationshipExists();
		}
		communicationEvent = communicationEventRepository.save(completeCommunicationEvent()
				                                                       .caseId(aCase.getId())
				                                                       .communicationEventStatusTypeId(communicationEventStatusType.getId())
				                                                       .communicationEventTypeId(communicationEventType.getId())
				                                                       .contactMechanismTypeId(contactMechanismType.getId())
				                                                       .partyRelationshipId(partyRelationship.getId())
				                                                       .build());
	}

	protected void aCommunicationEventTypeExists() {
		communicationEventType = communicationEventTypeRepository.save(completeCommunicationEventType().build());
	}

	protected void aCommunicationEventStatusTypeExists() {
		communicationEventStatusType = communicationEventStatusTypeRepository.save(completeCommunicationEventStatusType().build());
	}

	protected void aPartyRelationshipExists() {
		if (party1 == null) {
			party1 = aPartyExists();
		}
		if (party2 == null) {
			party2 = aPartyExists();
		}
		if (partyRole1 == null) {
			partyRole1 = aPartyRoleExists(party1);
		}
		if (partyRole2 == null) {
			partyRole2 = aPartyRoleExists(party2);
		}
		if (partyRelationshipType == null) {
			aPartyRelationshipTypeExists();
		}
		if (partyRelationshipStatusType == null) {
			aPartyRelationshiopStatusTypeExists();
		}
		if (priorityType == null) {
			aPriorityTypeExists();
		}
		partyRelationship = partyRelationshipRepository.save(completePartyRelationship()
				                                                     .fromPartyRoleId(partyRole1.getId())
				                                                     .toPartyRoleId(partyRole2.getId())
				                                                     .partyRelationshipTypeId(partyRelationshipType.getId())
				                                                     .partyRelationshipStatusTypeId(partyRelationshipStatusType.getId())
				                                                     .priorityTypeId(priorityType.getId())
				                                                     .build());
	}

	protected void aPriorityTypeExists() {
		priorityType = priorityTypeRepository.save(completePriorityType().build());
	}

	protected void aPartyRelationshiopStatusTypeExists() {
		partyRelationshipStatusType = partyRelationshipStatusTypeRepository.save(completePartyRelationshipStatusType().build());
	}

	protected void aPartyRelationshipTypeExists() {
		partyRelationshipType = partyRelationshipTypeRepository.save(completePartyRelationshipType().build());
	}
}
