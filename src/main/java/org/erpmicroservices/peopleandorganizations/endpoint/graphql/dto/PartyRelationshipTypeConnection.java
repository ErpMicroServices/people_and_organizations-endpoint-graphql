package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.PartyRelationshipType;

import java.util.List;

@Data
@Builder
public class PartyRelationshipTypeConnection implements Connection<PartyRelationshipType> {

	private List<Edge<PartyRelationshipType>> edges;
	private PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<PartyRelationshipType>> getEdges() {
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
