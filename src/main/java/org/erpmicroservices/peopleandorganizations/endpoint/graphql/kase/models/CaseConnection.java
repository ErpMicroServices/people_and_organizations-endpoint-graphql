package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;

import java.util.List;

@Data
@Builder
public class CaseConnection implements Connection<CaseEntity> {

	@Singular
	private List<Edge<CaseEntity>> edges;
	private PageInfo pageInfo;

	@Override
	public List<Edge<CaseEntity>> getEdges() { return edges;}

}
