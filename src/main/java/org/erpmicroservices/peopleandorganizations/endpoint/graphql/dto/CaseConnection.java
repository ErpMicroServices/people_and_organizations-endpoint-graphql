package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.Connection;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;

import java.util.List;

@Data
@Builder
public class CaseConnection implements Connection<Case> {

	private List<Edge<Case>> edges;
	private PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<Case>> getEdges() {
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
