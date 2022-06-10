package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.Connection;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Party;

import java.util.List;

public class PartyConnection implements Connection<Party> {

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<Party>> getEdges() {
		return null;
	}

	/**
	 * @return {@link PageInfo} pagination data about that list of edges
	 */
	@Override
	public PageInfo getPageInfo() {
		return null;
	}
}
