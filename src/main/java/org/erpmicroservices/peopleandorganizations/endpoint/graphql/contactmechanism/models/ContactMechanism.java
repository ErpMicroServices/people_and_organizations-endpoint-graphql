package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ContactMechanism {
    private UUID id;
    private String endPoint;
    private String directions;
    private ContactMechanismType contactMechanismType;
}
