package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;

import java.util.List;

@Data
@Builder
public class CaseRoleTypeConnection implements Connection<CaseRoleTypeEntity> {

	private List<Edge<CaseRoleTypeEntity>> edges;
	private PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<CaseRoleTypeEntity>> getEdges() {
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
