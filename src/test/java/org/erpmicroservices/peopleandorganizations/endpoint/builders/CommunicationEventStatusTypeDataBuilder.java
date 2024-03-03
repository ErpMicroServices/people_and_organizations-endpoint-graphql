package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventStatusTypeEntity;

import java.util.UUID;

public class CommunicationEventStatusTypeDataBuilder {

	public static CommunicationEventStatusTypeEntity.CommunicationEventStatusTypeBuilder completeCommunicationEventStatusType() {
		return CommunicationEventStatusTypeEntity.builder()
				       .description("CommunicationEventStatusTypeDataBuilder " + UUID.randomUUID());
	}
}
