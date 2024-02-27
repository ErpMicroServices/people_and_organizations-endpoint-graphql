package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.Case;

import java.util.List;

@Data
@Builder
public class CaseConnection implements Connection<CaseNode> {

	private List<Edge<CaseNode>> edges;
	private PageInfo pageInfo;

}
