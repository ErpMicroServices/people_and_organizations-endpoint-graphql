package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;

import java.util.UUID;

@Data
@Builder
public class CommunicationEventRole {
    private UUID id;
    private CommunicationEventRoleType communicationEventRoleType;
    private Party party;

}
