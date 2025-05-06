package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventTypeEdge;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventTypeRepository;
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
public class CommunicationEventTypeController {

	private final CommunicationEventTypeRepository communicationEventTypeRepository;

	public CommunicationEventTypeController(final CommunicationEventTypeRepository communicationEventTypeRepository) {
		this.communicationEventTypeRepository = communicationEventTypeRepository;
	}

	@QueryMapping
	public CommunicationEventTypeConnection communicationEventTypes(@Argument PageInfo pageInfo) {
		final Page<CommunicationEventTypeEntity> communicationEventTypePage = communicationEventTypeRepository.findCommunicationEventTypeByParentIdIsNull(pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventType>> communicationEventTypeEdges = communicationEventTypePage.stream()
				.map(entity -> {
					// Convert entity to model
					CommunicationEventType model = CommunicationEventType.builder()
							.id(entity.getId())
							.description(entity.getDescription())
							.build();

					return CommunicationEventTypeEdge.builder()
							.node(model)
							.cursor(Cursor.builder().value(valueOf(entity.getId().hashCode())).build())
							.build();
				})
				.collect(Collectors.toList());
		return CommunicationEventTypeConnection.builder()
				.edges(communicationEventTypeEdges)
				.pageInfo(pageInfo)
				.build();
	}

	@SchemaMapping
	public CommunicationEventTypeConnection children(@Argument PageInfo pageInfo, CommunicationEventTypeEntity parent) {
		final Page<CommunicationEventTypeEntity> communicationEventTypeByChildren = communicationEventTypeRepository.findCommunicationEventTypeByParentId(parent.getId(), pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventType>> communicationEventTypeEdges = communicationEventTypeByChildren.stream()
				.map(entity -> {
					// Convert entity to model
					CommunicationEventType model = CommunicationEventType.builder()
							.id(entity.getId())
							.description(entity.getDescription())
							.build();

					return CommunicationEventTypeEdge.builder()
							.cursor(Cursor.builder().value(valueOf(entity.getId())).build())
							.node(model)
							.build();
				})
				.collect(Collectors.toList());
		return CommunicationEventTypeConnection.builder()
				.edges(communicationEventTypeEdges)
				.pageInfo(pageInfo)
				.build();
	}
}
