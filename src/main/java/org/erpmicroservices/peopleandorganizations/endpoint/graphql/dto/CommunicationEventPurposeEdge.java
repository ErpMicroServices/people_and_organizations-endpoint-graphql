package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEventPurpose;


@Data
@Builder
public class CommunicationEventPurposeEdge implements Edge<CommunicationEventPurpose> {

	private CommunicationEventPurpose node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public CommunicationEventPurpose getNode() {
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
