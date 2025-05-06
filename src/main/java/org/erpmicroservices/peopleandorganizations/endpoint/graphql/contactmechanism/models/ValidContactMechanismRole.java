package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleType;

import java.util.UUID;

@Data
@Builder
public class ValidContactMechanismRole {
    private UUID id;
    private ContactMechanismType contactMechanismType;
    private CommunicationEventRoleType communicationEventRoleType;
}
