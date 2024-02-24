package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.graphql.CommunicationEventPurposeTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.graphql.CommunicationEventPurposeTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventPurposeType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventPurposeTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

@Controller
public class CommunicationEventPurposeTypeController {

	private final CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository;

	public CommunicationEventPurposeTypeController(final CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository) {
		this.communicationEventPurposeTypeRepository = communicationEventPurposeTypeRepository;
	}

	@QueryMapping
	public CommunicationEventPurposeTypeConnection communicationEventPurposeTypes(@Argument PageInfo pageInfo) {
		final Page<CommunicationEventPurposeType> communicationEventPurposeTypePage = communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentIdIsNull(Pageable.unpaged());//pageInfoToPageable(pageInfo));
		return getCommunicationEventPurposeTypeConnection(pageInfo, communicationEventPurposeTypePage);
	}

	@SchemaMapping
	public CommunicationEventPurposeTypeConnection children(@Argument PageInfo pageInfo, CommunicationEventPurposeType parent) {
		final Page<CommunicationEventPurposeType> communicationEventPurposeTypePage = communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentId(parent.getId(), Pageable.unpaged());//pageInfoToPageable(pageInfo));
		return getCommunicationEventPurposeTypeConnection(pageInfo, communicationEventPurposeTypePage);
	}

	private CommunicationEventPurposeTypeConnection getCommunicationEventPurposeTypeConnection(@Argument final PageInfo pageInfo, final Page<CommunicationEventPurposeType> communicationEventPurposeTypePage) {
		final List<Edge<CommunicationEventPurposeType>> communicationEventPurposeTypeEdges = communicationEventPurposeTypePage.stream()
				                                                                                     .map(communicationEventPurposeType -> CommunicationEventPurposeTypeEdge.builder()
						                                                                                                                           .cursor(Cursor.builder().value(valueOf(communicationEventPurposeType.getId().hashCode())).build())
						                                                                                                                           .node(communicationEventPurposeType)
						                                                                                                                           .build())
				                                                                                     .collect(Collectors.toList());
		return CommunicationEventPurposeTypeConnection.builder()
				       .edges(communicationEventPurposeTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
