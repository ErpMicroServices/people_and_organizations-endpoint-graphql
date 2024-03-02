package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseNodeEdge implements Edge<CaseNode> {
    CaseNode node;
    ConnectionCursor cursor;
}
