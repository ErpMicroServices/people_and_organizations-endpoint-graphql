package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseEntity;

@Data
@Builder
public class CaseEdge implements Edge<CaseEntity> {
	private CaseEntity node;
	private ConnectionCursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public CaseEntity getNode() {
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
