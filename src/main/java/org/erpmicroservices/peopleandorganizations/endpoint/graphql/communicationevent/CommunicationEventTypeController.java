package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class CommunicationEventTypeController {

	private final CommunicationEventTypeRepository communicationEventTypeRepository;

	public CommunicationEventTypeController(final CommunicationEventTypeRepository communicationEventTypeRepository) {
		this.communicationEventTypeRepository = communicationEventTypeRepository;
	}

	@QueryMapping
	public CommunicationEventTypeConnection communicationEventTypes(@Argument PageInfo pageInfo) {
		final Page<CommunicationEventType> communicationEventTypePage = communicationEventTypeRepository.findCommunicationEventTypeByParentIsNull(pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventType>> communicationEventTypeEdges = communicationEventTypePage.stream()
				                                                                       .map(communicationEventType -> CommunicationEventTypeEdge.builder()
						                                                                                                      .node(communicationEventType)
						                                                                                                      .cursor(Cursor.builder().value(valueOf(communicationEventType.getId().hashCode())).build())
						                                                                                                      .build())
				                                                                       .collect(Collectors.toList());
		return CommunicationEventTypeConnection.builder()
				       .edges(communicationEventTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public CommunicationEventTypeConnection children(@Argument PageInfo pageInfo, CommunicationEventType parent) {
		final Page<CommunicationEventType> communicationEventTypeByChildren = communicationEventTypeRepository.findCommunicationEventTypeByParentEquals(parent, pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventType>> communicationEventTypeEdges = communicationEventTypeByChildren.stream()
				                                                                       .map(communicationEventType -> CommunicationEventTypeEdge.builder()
						                                                                                                      .cursor(Cursor.builder().value(valueOf(communicationEventType.getId())).build())
						                                                                                                      .node(communicationEventType)
						                                                                                                      .build())
				                                                                       .collect(Collectors.toList());
		return CommunicationEventTypeConnection.builder()
				       .edges(communicationEventTypeEdges)
				       .pageInfo(pageInfo).build();
	}
}
