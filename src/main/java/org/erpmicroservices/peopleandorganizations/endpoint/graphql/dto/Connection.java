package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.Edge;

import java.util.List;

public interface Connection<T> {

	/**
	 * @return a list of {@link graphql.relay.Edge}s that are really a node of data and its cursor
	 */
	List<Edge<T>> getEdges();

	/**
	 * @return {@link graphql.relay.PageInfo} pagination data about that list of edges
	 */
	PageInfo getPageInfo();

}
