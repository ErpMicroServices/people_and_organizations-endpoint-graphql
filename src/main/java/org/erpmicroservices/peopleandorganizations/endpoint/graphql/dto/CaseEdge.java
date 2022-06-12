package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;

@Data
@Builder
public class CaseEdge implements Edge<Case> {
	private Case node;
	private ConnectionCursor connectionCursor;

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
		return connectionCursor;
	}
}
