package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CommunicationEventConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CommunicationEventEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventPurposeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class CommunicationEventController {

	private final CommunicationEventRepository communicationEventRepository;
	private final CommunicationEventPurposeRepository communicationEventPurposeRepository;

	public CommunicationEventController(final CommunicationEventRepository repository, final CommunicationEventPurposeRepository communicationEventPurposeRepository) {
		this.communicationEventRepository = repository;
		this.communicationEventPurposeRepository = communicationEventPurposeRepository;
	}


	@SchemaMapping(typeName = "Case", field = "communicationEvents")
	public CommunicationEventConnection communicationEvents(@Argument PageInfo pageInfo, Case kase) {
		final List<Edge<CommunicationEvent>> communicationEventEdges = communicationEventRepository.findByKase_Id(kase.getId(), pageInfoToPageable(pageInfo)).stream()
				                                                               .map(communicationEvent -> CommunicationEventEdge.builder()
						                                                                                          .node(communicationEvent)
						                                                                                          .cursor(Cursor.builder().value(String.valueOf(communicationEvent.getId().hashCode())).build())
						                                                                                          .build())
				                                                               .collect(Collectors.toList());
		return CommunicationEventConnection.builder()
				       .edges(communicationEventEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

}
