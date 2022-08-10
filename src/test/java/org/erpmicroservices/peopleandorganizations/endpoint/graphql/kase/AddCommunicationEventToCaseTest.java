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
	public void givenTheFollowing() {
		super.givenTheFollowing();
		aCaseExists();
		aCommunicationEventTypeExists();
		aCommunicationEventStatusTypeExists();
		aPartyRelationshipExists();
		aContactMechanismTypeExists();
		communicationEvent = completeCommunicationEvent()
				                     .caseId(aCase.getId())
				                     .communicationEventStatusTypeId(communicationEventStatusType.getId())
				                     .communicationEventTypeId(communicationEventType.getId())
				                     .partyRelationshipId(partyRelationship.getId())
				                     .contactMechanismTypeId(contactMechanismType.getId())
				                     .build();
	}

	@Test
	@Override
	public void whenThisHappens() {
		response = this.graphQlTester.documentName("AddCommunicationEventToCase")
				           .operationName("AddCommunicationEventToCase")
				           .variable("caseId", aCase.getId())
				           .variable("newCommunicationEvent", Map.of(
						           "started", communicationEvent.getStarted().format(DateTimeFormatter.ISO_DATE_TIME),
						           "note", communicationEvent.getNote(),
						           "contactMechanismTypeId", communicationEvent.getContactMechanismTypeId(),
						           "communicationEventStatusTypeId", communicationEvent.getCommunicationEventStatusTypeId(),
						           "communicationEventTypeId", communicationEvent.getCommunicationEventTypeId(),
						           "partyRelationshipId", communicationEvent.getPartyRelationshipId()
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void thenThisIsExpected() {
		response
				.path("addCommunicationEventToCase.id").hasValue()
				.path("addCommunicationEventToCase.started").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(communicationEvent.getStarted()))
				.path("addCommunicationEventToCase.ended").valueIsNull()
				.path("addCommunicationEventToCase.note").entity(String.class).isEqualTo(communicationEvent.getNote())
				.path("addCommunicationEventToCase.contactMechanismType.id").entity(UUID.class).isEqualTo(contactMechanismType.getId())
				.path("addCommunicationEventToCase.contactMechanismType.description").entity(String.class).isEqualTo(contactMechanismType.getDescription())
				.path("addCommunicationEventToCase.communicationEventStatusType.id").entity(UUID.class).isEqualTo(communicationEventStatusType.getId())
				.path("addCommunicationEventToCase.communicationEventStatusType.description").entity(String.class).isEqualTo(communicationEventStatusType.getDescription())
				.path("addCommunicationEventToCase.communicationEventType.id").entity(UUID.class).isEqualTo(communicationEventType.getId())
				.path("addCommunicationEventToCase.communicationEventType.description").entity(String.class).isEqualTo(communicationEventType.getDescription());
	}
}
