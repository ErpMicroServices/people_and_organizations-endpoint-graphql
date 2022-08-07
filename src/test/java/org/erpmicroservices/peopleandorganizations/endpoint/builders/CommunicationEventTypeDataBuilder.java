package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventType;

import java.util.UUID;

public class CommunicationEventTypeDataBuilder {

	public static CommunicationEventType.CommunicationEventTypeBuilder completeCommunicationEventType() {
		return CommunicationEventType.builder()
				       .description("CommunicationEventTypeDataBuilder " + UUID.randomUUID());
	}
}
