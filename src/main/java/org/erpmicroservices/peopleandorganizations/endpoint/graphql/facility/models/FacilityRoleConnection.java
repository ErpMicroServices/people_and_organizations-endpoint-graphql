package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;

import java.util.List;

@Data
@Builder
public class FacilityRoleConnection implements Connection<FacilityRole> {

	private List<Edge<FacilityRole>> edges;
	private PageInfo pageInfo;

}
