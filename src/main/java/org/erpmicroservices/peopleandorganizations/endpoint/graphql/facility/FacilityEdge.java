package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityEdge implements Edge<Facility> {
	private Facility node;
	private ConnectionCursor cursor;

}
