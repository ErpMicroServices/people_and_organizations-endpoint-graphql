package org.erpmicroservices.peopleandorganizations.endpoint.graphql.name.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class NameType {
    private UUID id;
    private String description;
}
