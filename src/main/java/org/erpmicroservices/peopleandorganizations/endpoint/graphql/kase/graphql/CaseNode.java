package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseType;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class CaseNode {
    private UUID id;
    private String description;
    private ZonedDateTime startedAt;
    private CaseTypeNode caseType;
    private CaseStatusTypeNode caseStatusType;
}
