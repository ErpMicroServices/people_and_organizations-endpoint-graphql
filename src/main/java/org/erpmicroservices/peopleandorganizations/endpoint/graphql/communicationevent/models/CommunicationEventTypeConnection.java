package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Connection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;

import java.util.List;

@Data
@Builder
public class CommunicationEventTypeConnection implements Connection<CommunicationEventType> {

	private List<Edge<CommunicationEventType>> edges;
	private PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<CommunicationEventType>> getEdges() {
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
