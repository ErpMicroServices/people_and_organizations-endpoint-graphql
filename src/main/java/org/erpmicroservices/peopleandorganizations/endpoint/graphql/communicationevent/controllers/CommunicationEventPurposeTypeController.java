package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventPurposeTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventPurposeTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventPurposeTypeEdge;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventPurposeTypeRepository;
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
		final Page<CommunicationEventPurposeTypeEntity> communicationEventPurposeTypePage = communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentIdIsNull(Pageable.unpaged());//pageInfoToPageable(pageInfo));
		return getCommunicationEventPurposeTypeConnection(pageInfo, communicationEventPurposeTypePage);
	}

	@SchemaMapping
	public CommunicationEventPurposeTypeConnection children(@Argument PageInfo pageInfo, CommunicationEventPurposeTypeEntity parent) {
		final Page<CommunicationEventPurposeTypeEntity> communicationEventPurposeTypePage = communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentId(parent.getId(), Pageable.unpaged());//pageInfoToPageable(pageInfo));
		return getCommunicationEventPurposeTypeConnection(pageInfo, communicationEventPurposeTypePage);
	}

	private CommunicationEventPurposeTypeConnection getCommunicationEventPurposeTypeConnection(@Argument final PageInfo pageInfo, final Page<CommunicationEventPurposeTypeEntity> communicationEventPurposeTypePage) {
		final List<Edge<CommunicationEventPurposeTypeEntity>> communicationEventPurposeTypeEdges = communicationEventPurposeTypePage.stream()
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
