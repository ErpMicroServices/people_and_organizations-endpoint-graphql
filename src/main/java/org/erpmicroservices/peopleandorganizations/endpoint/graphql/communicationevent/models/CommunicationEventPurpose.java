package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CommunicationEventPurpose {
    private UUID id;
    private CommunicationEventPurposeType communicationEventPurposeType;
    String description;
}
