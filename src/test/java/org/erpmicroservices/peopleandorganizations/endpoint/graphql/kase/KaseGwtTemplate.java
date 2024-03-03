package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.backend.entities.*;
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
	protected CaseEntity aCaseEntity;
	protected CaseRoleTypeEntity caseRoleTypeEntity;
	protected CaseRoleEntity caseRoleEntity;
	protected CaseTypeEntity caseTypeEntity;
	protected CaseStatusTypeEntity caseStatusTypeEntity;

	protected PartyEntity partyEntity1;

	protected PartyEntity partyEntity2;

	protected PartyRoleEntity partyRoleEntity1;

	protected PartyRoleEntity partyRoleEntity2;

	protected CaseRoleTypeEntity aCaseRoleTypeExists() {
		caseRoleTypeEntity = caseRoleTypeRepository.save(completeCaseRoleType().build());
		return caseRoleTypeEntity;
	}

	protected CaseEntity aCaseExists() {
		aCaseTypeExists();
		aCaseStatusTypeExists();
		aCaseEntity = caseRepository.save(completeCase()
				                            .caseTypeId(caseTypeEntity.getId())
				                            .caseStatusTypeId(caseStatusTypeEntity.getId())
				                            .build());
		return aCaseEntity;
	}

	protected CaseTypeEntity aCaseTypeExists() {
		caseTypeEntity = caseTypeRepository.save(completeCaseType().build());
		return caseTypeEntity;
	}

	protected CaseStatusTypeEntity aCaseStatusTypeExists() {
		caseStatusTypeEntity = caseStatusTypeRepository.save(completeCaseStatusType().build());
		return caseStatusTypeEntity;
	}

	protected CaseRoleEntity aCaseRoleExists() {
		if (partyEntity == null) {
			partyEntity = aPartyExists();
		}
		if (caseRoleTypeEntity == null) {
			aCaseRoleTypeExists();
		}
		caseRoleEntity = caseRoleRepository.save(completeCaseRole()
				                                   .caseId(aCaseEntity.getId())
				                                   .partyId(partyEntity.getId())
				                                   .caseRoleTypeId(caseRoleTypeEntity.getId())
				                                   .build());
		return caseRoleEntity;
	}

	protected CommunicationEventEntity aCommunicationEventExists() {
		if (communicationEventStatusTypeEntity == null) {
			aCommunicationEventStatusTypeExists();
		}
		if (communicationEventTypeEntity == null) {
			aCommunicationEventTypeExists();
		}
		if (contactMechanismTypeEntity == null) {
			aContactMechanismTypeExists();
		}
		if (partyRelationshipEntity == null) {
			aPartyRelationshipExists();
		}
		communicationEventEntity = communicationEventRepository.save(completeCommunicationEvent()
				                                                       .caseId(aCaseEntity.getId())
				                                                       .communicationEventStatusTypeId(communicationEventStatusTypeEntity.getId())
				                                                       .communicationEventTypeId(communicationEventTypeEntity.getId())
				                                                       .contactMechanismTypeId(contactMechanismTypeEntity.getId())
				                                                       .partyRelationshipId(partyRelationshipEntity.getId())
				                                                       .build());
		return communicationEventEntity;
	}

	protected CommunicationEventTypeEntity aCommunicationEventTypeExists() {
		communicationEventTypeEntity = communicationEventTypeRepository.save(completeCommunicationEventType().build());
		return communicationEventTypeEntity;
	}

	protected CommunicationEventStatusTypeEntity aCommunicationEventStatusTypeExists() {
		communicationEventStatusTypeEntity = communicationEventStatusTypeRepository.save(completeCommunicationEventStatusType().build());
		return communicationEventStatusTypeEntity;
	}

	protected void aPartyRelationshipExists() {
		if (partyEntity1 == null) {
			partyEntity1 = aPartyExists();
		}
		if (partyEntity2 == null) {
			partyEntity2 = aPartyExists();
		}
		if (partyRoleEntity1 == null) {
			partyRoleEntity1 = aPartyRoleExists(partyEntity1);
		}
		if (partyRoleEntity2 == null) {
			partyRoleEntity2 = aPartyRoleExists(partyEntity2);
		}
		if (partyRelationshipTypeEntity == null) {
			aPartyRelationshipTypeExists();
		}
		if (partyRelationshipStatusTypeEntity == null) {
			aPartyRelationshiopStatusTypeExists();
		}
		if (priorityTypeEntity == null) {
			aPriorityTypeExists();
		}
		partyRelationshipEntity = partyRelationshipRepository.save(completePartyRelationship()
				                                                     .fromPartyRoleId(partyRoleEntity1.getId())
				                                                     .toPartyRoleId(partyRoleEntity2.getId())
				                                                     .partyRelationshipTypeId(partyRelationshipTypeEntity.getId())
				                                                     .partyRelationshipStatusTypeId(partyRelationshipStatusTypeEntity.getId())
				                                                     .priorityTypeId(priorityTypeEntity.getId())
				                                                     .build());
	}

	protected void aPriorityTypeExists() {
		priorityTypeEntity = priorityTypeRepository.save(completePriorityType().build());
	}

	protected void aPartyRelationshiopStatusTypeExists() {
		partyRelationshipStatusTypeEntity = partyRelationshipStatusTypeRepository.save(completePartyRelationshipStatusType().build());
	}

	protected void aPartyRelationshipTypeExists() {
		partyRelationshipTypeEntity = partyRelationshipTypeRepository.save(completePartyRelationshipType().build());
	}
}
