package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Data;

@Data
public class CaseNodeEdge implements Edge<CaseNode> {
    CaseNode node;
    ConnectionCursor cursor;
}
