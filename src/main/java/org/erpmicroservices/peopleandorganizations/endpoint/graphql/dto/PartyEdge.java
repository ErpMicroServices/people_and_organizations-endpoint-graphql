package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Party;

@Data
@Builder
public class PartyEdge implements Edge<Party> {
	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public Party getNode() {
		return null;
	}

	/**
	 * @return the cursor for this edge node
	 */
	@Override
	public ConnectionCursor getCursor() {
		return null;
	}
}
