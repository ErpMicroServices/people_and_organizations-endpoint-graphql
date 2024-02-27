package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRoleType;


@Data
@Builder
public class CaseRoleTypeEdge implements Edge<CaseRoleType> {

	private CaseRoleType node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public CaseRoleType getNode() {
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
