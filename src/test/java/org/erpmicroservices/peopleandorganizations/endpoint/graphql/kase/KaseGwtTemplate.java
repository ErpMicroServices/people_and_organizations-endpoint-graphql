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

public abstract class KaseGwtTemplate extends AbstractGWT {
	protected Case aCase;
	protected CaseRoleType caseRoleType;
	protected Party party;
	protected CaseRole caseRole;
	protected CaseType caseType;
	protected CaseStatusType caseStatusType;
	protected PartyType partyType;

	protected Party party1;
	protected Party party2;
	protected ContactMechanismType contactMechanismType;
	protected CommunicationEventStatusType communicationEventStatusType;
	protected CommunicationEventType communicationEventType;
	protected PartyRole partyRole1;
	protected PartyRole partyRole2;
	protected PartyRelationship partyRelationship;
	protected CommunicationEvent communicationEvent;
	protected PartyRoleType partyRoleType;
	protected PartyRelationshipType partyRelationshipType;
	protected PartyRelationshipStatusType partyRelationshipStatusType;
	protected PriorityType priorityType;

	protected Party aPartyExists() {
		return partyRepository.save(completeParty()
				                            .partyTypeId(partyType.getId())
				                            .build());
	}

	protected void aPartyTypeExists() {
		partyType = partyTypeRepository.save(completePartyType().build());
	}

	protected void aCaseRoleTypeExists() {
		caseRoleType = caseRoleTypeRepository.save(completeCaseRoleType().build());
	}

	protected void aCaseExists() {
		caseType = caseTypeRepository.save(completeCaseType().build());
		caseStatusType = caseStatusTypeRepository.save(completeCaseStatusType().build());
		aCase = completeCase()
				        .caseTypeId(caseType.getId())
				        .caseStatusTypeId(caseStatusType.getId())
				        .build();
		aCase = caseRepository.save(aCase);
	}

	protected void aCaseTypeExists() {
		caseType = caseTypeRepository.save(completeCaseType().build());
	}

	protected void aCaseStatusTypeExists() {
		caseStatusType = caseStatusTypeRepository.save(completeCaseStatusType().build());
	}

	protected void aCommunicationEventExists() {
		communicationEvent = communicationEventRepository.save(completeCommunicationEvent()
				                                                       .caseId(aCase.getId())
				                                                       .communicationEventStatusTypeId(communicationEventStatusType.getId())
				                                                       .communicationEventTypeId(communicationEventType.getId())
				                                                       .contactMechanismTypeId(contactMechanismType.getId())
				                                                       .partyRelationshipId(partyRelationship.getId())
				                                                       .build());
	}

	protected void aPartyRelationshipExists() {
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

	protected PartyRole aPartyRoleExists(final Party party) {
		return partyRoleRepository.save(completePartyRole()
				                                .partyId(party1.getId())
				                                .partyRoleTypeId(partyRoleType.getId())
				                                .build());
	}

	protected void aPartyRoleTypeExists() {
		partyRoleType = partyRoleTypeRepository.save(completePartyRoleType().build());
	}

	protected void aCommunicationEventTypeExists() {
		communicationEventType = communicationEventTypeRepository.save(completeCommunicationEventType().build());
	}

	protected void aCommunicationEventStatusTypeRepositoryExists() {
		communicationEventStatusType = communicationEventStatusTypeRepository.save(completeCommunicationEventStatusType().build());
	}

	protected void aContactMechanismTypeExists() {
		contactMechanismType = contactMechanismTypeRepository.save(completeContactMechanismType().build());
	}

	protected void aCaseRoleExists() {
		caseRole = caseRoleRepository.save(completeCaseRole()
				                                   .caseId(aCase.getId())
				                                   .partyId(party1.getId())
				                                   .caseRoleTypeId(caseRoleType.getId())
				                                   .build());
	}

}
