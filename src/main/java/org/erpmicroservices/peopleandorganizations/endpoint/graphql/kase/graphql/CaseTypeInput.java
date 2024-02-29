package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CaseTypeInput {
    private UUID id;
    private String description;
}
