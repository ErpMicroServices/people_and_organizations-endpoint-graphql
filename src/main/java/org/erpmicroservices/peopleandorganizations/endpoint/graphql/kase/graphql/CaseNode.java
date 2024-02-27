package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CaseNode {
    private UUID id;
    private String description;
    private LocalDateTime startedAt;
    private CaseTypeNode caseType;
    private CaseStatusTypeNode caseStatusTypeNode;
}
