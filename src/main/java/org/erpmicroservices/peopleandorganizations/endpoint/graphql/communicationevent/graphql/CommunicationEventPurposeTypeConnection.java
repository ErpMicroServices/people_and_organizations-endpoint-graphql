package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.graphql;

import graphql.relay.Connection;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventPurposeType;

import java.util.List;

@Data
@Builder
public class CommunicationEventPurposeTypeConnection implements Connection<CommunicationEventPurposeType> {

	private List<Edge<CommunicationEventPurposeType>> edges;
	private PageInfo pageInfo;

	/**
	 * @return a list of {@link Edge}s that are really a node of data and its cursor
	 */
	@Override
	public List<Edge<CommunicationEventPurposeType>> getEdges() {
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
