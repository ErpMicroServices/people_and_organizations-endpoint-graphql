package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;

import java.util.List;

@Data
@Builder
public class CaseConnection implements Connection<Case> {

	private List<Edge<Case>> edges;
	private PageInfo pageInfo;

}
