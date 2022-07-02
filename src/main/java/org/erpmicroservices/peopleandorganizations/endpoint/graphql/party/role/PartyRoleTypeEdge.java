package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class PartyRoleTypeEdge implements Edge<PartyRoleType> {

	private PartyRoleType node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public PartyRoleType getNode() {
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
