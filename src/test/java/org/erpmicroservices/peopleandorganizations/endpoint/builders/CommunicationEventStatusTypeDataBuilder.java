package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventStatusType;

import java.util.UUID;

public class CommunicationEventStatusTypeDataBuilder {

	public static CommunicationEventStatusType.CommunicationEventStatusTypeBuilder completeCommunicationEventStatusType() {
		return CommunicationEventStatusType.builder()
				       .description("CommunicationEventStatusTypeDataBuilder " + UUID.randomUUID());
	}
}
