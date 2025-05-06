package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleEntity;

import java.util.List;

@Data
@Builder
public class CaseRoleConnection implements Connection<CaseRoleEntity> {

	private List<Edge<CaseRoleEntity>> edges;
	private PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<CaseRoleEntity>> getEdges() {
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
