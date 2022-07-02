package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;

import java.util.List;

@Data
@Builder
public class FacilityConnection implements Connection<Facility> {
	private List<Edge<Facility>> edges;
	private PageInfo pageInfo;
}
