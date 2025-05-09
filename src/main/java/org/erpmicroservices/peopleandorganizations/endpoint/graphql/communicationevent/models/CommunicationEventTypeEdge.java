package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class CommunicationEventTypeEdge implements Edge<CommunicationEventType> {

	private CommunicationEventType node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public CommunicationEventType getNode() {
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
