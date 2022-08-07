package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEvent;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CommunicationEventTestDataBuilder {

	public static CommunicationEvent.CommunicationEventBuilder completeCommunicationEvent() {
		return CommunicationEvent.builder()
				       .note("CommunicationEventTestDataBuilder " + UUID.randomUUID())
				       .started(ZonedDateTime.now());
	}

}
