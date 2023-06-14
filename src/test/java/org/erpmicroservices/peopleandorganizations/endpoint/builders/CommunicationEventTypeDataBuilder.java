package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventType;

import java.util.UUID;

public class CommunicationEventTypeDataBuilder {

	public static CommunicationEventType.CommunicationEventTypeBuilder completeCommunicationEventType() {
		return CommunicationEventType.builder()
				       .description("CommunicationEventTypeDataBuilder " + UUID.randomUUID());
	}
}
