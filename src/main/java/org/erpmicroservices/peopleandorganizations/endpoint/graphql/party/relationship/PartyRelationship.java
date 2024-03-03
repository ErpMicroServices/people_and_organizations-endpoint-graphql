package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PartyRelationship {
    private UUID id;
}
