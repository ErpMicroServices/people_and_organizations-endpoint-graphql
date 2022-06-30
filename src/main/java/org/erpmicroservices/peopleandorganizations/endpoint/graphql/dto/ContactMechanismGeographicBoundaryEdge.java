package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.ContactMechanismGeographicBoundary;


@Data
@Builder
public class ContactMechanismGeographicBoundaryEdge implements Edge<ContactMechanismGeographicBoundary> {

	private ContactMechanismGeographicBoundary node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public ContactMechanismGeographicBoundary getNode() {
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
