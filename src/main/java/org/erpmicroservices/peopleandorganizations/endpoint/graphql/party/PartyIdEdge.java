package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class PartyIdEdge implements Edge<PartyId> {

	private PartyId node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public PartyId getNode() {
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
