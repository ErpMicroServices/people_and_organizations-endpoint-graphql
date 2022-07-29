package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.Case;
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

	private final CommunicationEventRoleRepository communicationEventRoleRepository;

	public CommunicationEventController(final CommunicationEventRepository repository, final CommunicationEventPurposeRepository communicationEventPurposeRepository, final CommunicationEventRoleRepository communicationEventRoleRepository) {
		this.communicationEventRepository = repository;
		this.communicationEventRoleRepository = communicationEventRoleRepository;
	}


	@SchemaMapping(typeName = "Case", field = "communicationEvents")
	public CommunicationEventConnection communicationEvents(@Argument PageInfo pageInfo, Case kase) {
		final List<Edge<CommunicationEvent>> communicationEventEdges = communicationEventRepository.findByCaseId(kase.getId(), pageInfoToPageable(pageInfo)).stream()
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

	@SchemaMapping(typeName = "CommunicationEvent", field = "roles")
	public CommunicationEventRoleConnection rolesForCommunicationEvent(@Argument PageInfo pageInfo, CommunicationEvent communicationEvent) {
		final List<Edge<CommunicationEventRole>> communicationEventRoleEdges = communicationEventRoleRepository.findByCommunicationEventId(communicationEvent.getId(), pageInfoToPageable(pageInfo)).stream()
				                                                                       .map(communicationEventRole -> CommunicationEventRoleEdge.builder()
						                                                                                                      .node(communicationEventRole)
						                                                                                                      .cursor(Cursor.builder().value(String.valueOf(communicationEventRole.getId().hashCode())).build())
						                                                                                                      .build())
				                                                                       .collect(Collectors.toList());
		return CommunicationEventRoleConnection.builder()
				       .edges(communicationEventRoleEdges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
