package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.Case;

@Data
@Builder
public class CaseEdge implements Edge<Case> {
	private Case node;
	private ConnectionCursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public Case getNode() {
		return node;
	}

	/**
	 * @return the cursor for this edge node
	 */
	@Override
	public ConnectionCursor getCursor() {
		return cursor;
	}
}
