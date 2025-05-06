package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationship;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class CommunicationEvent {
    private UUID id;
    private ZonedDateTime started;
    private ZonedDateTime ended;
    private String note;
    private ContactMechanismType contactMechanismType;
    private PartyRelationship partyRelationship;
    private CommunicationEventStatusType communicationEventStatusType;
    private CommunicationEventType communicationEventType;
    private Case kase;

}
