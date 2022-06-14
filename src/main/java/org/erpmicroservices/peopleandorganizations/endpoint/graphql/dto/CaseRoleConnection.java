package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.Connection;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseRole;

import java.util.List;

@Data
@Builder
public class CaseRoleConnection implements Connection<CaseRole> {

	private List<Edge<CaseRole>> edges;
	private org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<CaseRole>> getEdges() {
		return edges;
	}

	/**
	 * @return {@link PageInfo} pagination data about that list of edges
	 */
	@Override
	public PageInfo getPageInfo() {
		return pageInfo;
	}
}
