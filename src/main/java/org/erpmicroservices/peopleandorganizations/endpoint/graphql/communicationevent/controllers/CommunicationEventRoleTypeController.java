package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleTypeEdge;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventRoleType;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
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
public class CommunicationEventRoleTypeController {

	private final CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository;

	public CommunicationEventRoleTypeController(final CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository) {
		this.communicationEventRoleTypeRepository = communicationEventRoleTypeRepository;
	}

	@QueryMapping
	public CommunicationEventRoleTypeConnection communicationEventRoleTypes(@Argument PageInfo pageInfo) {
		final Page<CommunicationEventRoleType> communicationEventRoleTypePage = communicationEventRoleTypeRepository.findCommunicationEventRoleTypeByParentIdIsNull(pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventRoleType>> communicationEventRoleTypeEdges = communicationEventRoleTypePage.stream()
				                                                                               .map(communicationEventRoleType -> CommunicationEventRoleTypeEdge.builder()
						                                                                                                                  .node(communicationEventRoleType)
						                                                                                                                  .cursor(Cursor.builder().value(valueOf(communicationEventRoleType.getId().hashCode())).build())
						                                                                                                                  .build())
				                                                                               .collect(Collectors.toList());
		return CommunicationEventRoleTypeConnection.builder()
				       .edges(communicationEventRoleTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public CommunicationEventRoleTypeConnection children(@Argument PageInfo pageInfo, CommunicationEventRoleType parent) {
		final Page<CommunicationEventRoleType> communicationEventRoleTypeByChildren = communicationEventRoleTypeRepository.findCommunicationEventRoleTypeByParentId(parent.getId(), pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventRoleType>> communicationEventRoleTypeEdges = communicationEventRoleTypeByChildren.stream()
				                                                                               .map(communicationEventRoleType -> CommunicationEventRoleTypeEdge.builder()
						                                                                                                                  .cursor(Cursor.builder().value(valueOf(communicationEventRoleType.getId())).build())
						                                                                                                                  .node(communicationEventRoleType)
						                                                                                                                  .build())
				                                                                               .collect(Collectors.toList());
		return CommunicationEventRoleTypeConnection.builder()
				       .edges(communicationEventRoleTypeEdges)
				       .pageInfo(pageInfo).build();
	}
}

