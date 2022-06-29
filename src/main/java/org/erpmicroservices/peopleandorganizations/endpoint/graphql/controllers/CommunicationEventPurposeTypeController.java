package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CommunicationEventPurposeTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CommunicationEventPurposeTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEventPurposeType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventPurposeTypeRepository;
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
public class CommunicationEventPurposeTypeController {

	private final CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository;

	public CommunicationEventPurposeTypeController(final CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository) {
		this.communicationEventPurposeTypeRepository = communicationEventPurposeTypeRepository;
	}

	@QueryMapping
	public CommunicationEventPurposeTypeConnection communicationEventPurposeTypes(@Argument PageInfo pageInfo) {
		final Page<CommunicationEventPurposeType> communicationEventPurposeTypePage = communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentIsNull(pageInfoToPageable(pageInfo));
		return getCommunicationEventPurposeTypeConnection(pageInfo, communicationEventPurposeTypePage);
	}

	@SchemaMapping
	public CommunicationEventPurposeTypeConnection children(@Argument PageInfo pageInfo, CommunicationEventPurposeType parent) {
		final Page<CommunicationEventPurposeType> communicationEventPurposeTypePage = communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentEquals(parent, pageInfoToPageable(pageInfo));
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
