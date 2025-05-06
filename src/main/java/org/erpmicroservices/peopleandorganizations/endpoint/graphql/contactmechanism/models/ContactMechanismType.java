package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ContactMechanismType {
    private UUID id;
    private String description;
}
