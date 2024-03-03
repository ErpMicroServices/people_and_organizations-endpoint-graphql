package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class GeographicBoundaryEdge implements Edge<GeographicBoundary> {

	private GeographicBoundary node;

	private Cursor cursor;

}
