package org.erpmicroservices.peopleandorganizations.endpoint.graphql.id;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.IdTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class IdTypeEdge implements Edge<IdType> {

	private IdType node;

	private Cursor cursor;

}
