package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventEntity;

import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CommunicationEventTestDataBuilder {

	public static CommunicationEventEntity.CommunicationEventEntityBuilder completeCommunicationEvent() {
		return CommunicationEventEntity.builder()
				       .note("CommunicationEventTestDataBuilder " + UUID.randomUUID())
				       .started(zonedDateTimeNow());
	}

}
