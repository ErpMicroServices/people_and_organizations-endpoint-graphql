package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class FacilityRoleEdge implements Edge<FacilityRole> {

	private FacilityRole node;

	private Cursor cursor;

}
