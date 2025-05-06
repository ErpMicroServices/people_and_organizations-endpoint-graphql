package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;

@Data
@Builder
public class FacilityTypeEdge implements Edge<FacilityType> {

	private FacilityType node;

	private Cursor cursor;

	@Override
	public FacilityType getNode() {
		return node;
	}

	@Override
	public ConnectionCursor getCursor() {
		return cursor;
	}
}
