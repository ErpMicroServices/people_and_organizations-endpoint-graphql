package org.erpmicroservices.peopleandorganizations.endpoint.graphql.id;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.IdTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;

import java.util.List;

@Data
@Builder
public class IdTypeConnection implements Connection<IdType> {

	private List<Edge<IdType>> edges;
	private PageInfo pageInfo;
}
