package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleEntity;

@Data
@Builder
public class CaseRoleEdge implements Edge<CaseRoleEntity> {

	private CaseRoleEntity node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public CaseRoleEntity getNode() {
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
