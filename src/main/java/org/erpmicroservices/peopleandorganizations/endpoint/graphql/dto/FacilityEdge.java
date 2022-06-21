package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Facility;

@Data
@Builder
public class FacilityEdge implements Edge<Facility> {
	private Facility node;
	private ConnectionCursor cursor;

}
