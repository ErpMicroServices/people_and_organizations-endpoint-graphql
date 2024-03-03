package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;

@Data
@Builder
public class FacilityRoleTypeEdge implements Edge<FacilityRoleType> {

	private FacilityRoleType node;

	private Cursor cursor;

}
