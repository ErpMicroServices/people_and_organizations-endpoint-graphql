package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEvent;

import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CommunicationEventTestDataBuilder {

	public static CommunicationEvent.CommunicationEventBuilder completeCommunicationEvent() {
		return CommunicationEvent.builder()
				       .note("CommunicationEventTestDataBuilder " + UUID.randomUUID())
				       .started(zonedDateTimeNow());
	}

}
