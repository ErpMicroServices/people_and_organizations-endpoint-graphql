package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.backend.entities.Case;

import java.util.List;

@Data
@Builder
public class CaseConnection implements Connection<Case> {

	@Singular
	private List<Edge<Case>> edges;
	private PageInfo pageInfo;

	@Override
	public List<Edge<Case>> getEdges() { return edges;}

}
