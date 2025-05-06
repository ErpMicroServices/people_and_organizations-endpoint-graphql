package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CaseStatusType {
    private UUID id;
    private String description;
}
