package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import lombok.Data;

import java.util.UUID;

@Data
public class CaseStatusTypeNode {
    private UUID id;
    private String description;
    private CaseStatusTypeNode parent;
}
