package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.Facility;

@Data
@Builder
public class FacilityEdge implements Edge<Facility> {
	private Facility node;
	private ConnectionCursor cursor;

}
