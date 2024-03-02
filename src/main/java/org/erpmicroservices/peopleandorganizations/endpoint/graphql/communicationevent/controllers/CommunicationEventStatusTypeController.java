package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventStatusTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventStatusTypeEdge;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventStatusTypeRepository;
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
public class CommunicationEventStatusTypeController {

	private final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

	public CommunicationEventStatusTypeController(CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository) {
		this.communicationEventStatusTypeRepository = communicationEventStatusTypeRepository;
	}

	@QueryMapping
	public CommunicationEventStatusTypeConnection communicationEventStatusTypes(@Argument PageInfo pageInfo) {
		final Page<CommunicationEventStatusType> communicationEventStatusTypes = communicationEventStatusTypeRepository.findCommunicationEventStatusTypeByParentIdIsNull(pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventStatusType>> communicationEventStatusTypeEdges = communicationEventStatusTypes.stream().map(communicationEventStatusType -> CommunicationEventStatusTypeEdge.builder()
				                                                                                                                                                              .node(communicationEventStatusType)
				                                                                                                                                                              .cursor(Cursor.builder().value(valueOf(communicationEventStatusType.getId().hashCode())).build())
				                                                                                                                                                              .build())
				                                                                                   .collect(Collectors.toList());
		return CommunicationEventStatusTypeConnection.builder()
				       .edges(communicationEventStatusTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public CommunicationEventStatusTypeConnection children(@Argument PageInfo pageInfo, CommunicationEventStatusType parent) {
		final Page<CommunicationEventStatusType> children = communicationEventStatusTypeRepository.findCommunicationEventStatusTypeByParentId(parent.getId(), pageInfoToPageable(pageInfo));
		final List<Edge<CommunicationEventStatusType>> childrenEdges = children.stream()
				                                                               .map(child -> CommunicationEventStatusTypeEdge.builder()
						                                                                             .cursor(Cursor.builder().value(valueOf(child.getId())).build())
						                                                                             .node(child)
						                                                                             .build())
				                                                               .collect(Collectors.toList());
		return CommunicationEventStatusTypeConnection.builder()
				       .edges(childrenEdges)
				       .pageInfo(pageInfo).build();
	}

}
