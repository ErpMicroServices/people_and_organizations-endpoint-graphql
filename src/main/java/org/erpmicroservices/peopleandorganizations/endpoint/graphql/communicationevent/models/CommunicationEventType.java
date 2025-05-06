package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CommunicationEventType {
    private UUID id;
    private String description;
}
