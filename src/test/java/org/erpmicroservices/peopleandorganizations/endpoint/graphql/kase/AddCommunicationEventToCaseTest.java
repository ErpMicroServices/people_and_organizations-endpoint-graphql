package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CommunicationEventTestDataBuilder.completeCommunicationEvent;

@SpringBootTest
@AutoConfigureGraphQlTester
public class AddCommunicationEventToCaseTest extends KaseGwtTemplate {

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCaseExists();
		aCommunicationEventTypeExists();
		aCommunicationEventStatusTypeExists();
		aPartyRelationshipExists();
		aContactMechanismTypeExists();
		communicationEventEntity = completeCommunicationEvent()
				                     .caseId(aCaseEntity.getId())
				                     .communicationEventStatusTypeId(communicationEventStatusTypeEntity.getId())
				                     .communicationEventTypeId(communicationEventTypeEntity.getId())
				                     .partyRelationshipId(partyRelationshipEntity.getId())
				                     .contactMechanismTypeId(contactMechanismTypeEntity.getId())
				                     .build();
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CaseAddCommunicationEvent")
				           .operationName("AddCommunicationEventToCase")
				           .variable("caseId", aCaseEntity.getId())
				           .variable("newCommunicationEvent", Map.of(
						           "started", communicationEventEntity.getStarted().format(DateTimeFormatter.ISO_DATE_TIME),
						           "note", communicationEventEntity.getNote(),
						           "contactMechanismTypeId", communicationEventEntity.getContactMechanismTypeId(),
						           "communicationEventStatusTypeId", communicationEventEntity.getCommunicationEventStatusTypeId(),
						           "communicationEventTypeId", communicationEventEntity.getCommunicationEventTypeId(),
						           "partyRelationshipId", communicationEventEntity.getPartyRelationshipId()
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("addCommunicationEventToCase.id").hasValue()
				.path("addCommunicationEventToCase.started").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(communicationEventEntity.getStarted()))
				.path("addCommunicationEventToCase.ended").valueIsNull()
				.path("addCommunicationEventToCase.note").entity(String.class).isEqualTo(communicationEventEntity.getNote())
				.path("addCommunicationEventToCase.contactMechanismTypeEntity.id").entity(UUID.class).isEqualTo(contactMechanismTypeEntity.getId())
				.path("addCommunicationEventToCase.contactMechanismTypeEntity.description").entity(String.class).isEqualTo(contactMechanismTypeEntity.getDescription())
				.path("addCommunicationEventToCase.communicationEventStatusTypeEntity.id").entity(UUID.class).isEqualTo(communicationEventStatusTypeEntity.getId())
				.path("addCommunicationEventToCase.communicationEventStatusTypeEntity.description").entity(String.class).isEqualTo(communicationEventStatusTypeEntity.getDescription())
				.path("addCommunicationEventToCase.communicationEventTypeEntity.id").entity(UUID.class).isEqualTo(communicationEventTypeEntity.getId())
				.path("addCommunicationEventToCase.communicationEventTypeEntity.description").entity(String.class).isEqualTo(communicationEventTypeEntity.getDescription());
	}
}
