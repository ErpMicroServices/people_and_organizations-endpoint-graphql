package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CommunicationEventWorkEffort {
    private UUID id;
    private CommunicationEvent communicationEvent;
    private UUID workEffortId;
}
