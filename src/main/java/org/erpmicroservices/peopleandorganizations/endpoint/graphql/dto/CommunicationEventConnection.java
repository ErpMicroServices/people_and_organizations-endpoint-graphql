package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.Connection;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEvent;

import java.util.List;

@Data
@Builder
public class CommunicationEventConnection implements Connection<CommunicationEvent> {

	private List<Edge<CommunicationEvent>> edges;
	private org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<CommunicationEvent>> getEdges() {
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
