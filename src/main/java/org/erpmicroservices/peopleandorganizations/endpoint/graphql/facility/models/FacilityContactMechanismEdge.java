package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityContactMechanismEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;

@Data
@Builder
public class FacilityContactMechanismEdge implements Edge<FacilityContactMechanism> {

	private FacilityContactMechanism node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public FacilityContactMechanism getNode() {
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
