package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventTypeEntity;

import java.util.UUID;

public class CommunicationEventTypeDataBuilder {

	public static CommunicationEventTypeEntity.CommunicationEventTypeBuilder completeCommunicationEventType() {
		return CommunicationEventTypeEntity.builder()
				       .description("CommunicationEventTypeDataBuilder " + UUID.randomUUID());
	}
}
