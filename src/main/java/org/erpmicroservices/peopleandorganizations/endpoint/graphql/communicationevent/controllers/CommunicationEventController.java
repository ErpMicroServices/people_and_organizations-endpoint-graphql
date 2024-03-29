package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.graphql.CommunicationEventConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.graphql.CommunicationEventEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.graphql.CommunicationEventRoleConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.graphql.CommunicationEventRoleEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventRole;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventRoleRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventStatusTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationship;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRelationshipRepository;
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

	private final ContactMechanismTypeRepository contactMechanismTypeRepository;

	private final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

	private final CommunicationEventTypeRepository communicationEventTypeRepository;

	private final PartyRelationshipRepository partyRelationshipRepository;

	public CommunicationEventController(final CommunicationEventRepository repository, final CommunicationEventRoleRepository communicationEventRoleRepository, final ContactMechanismTypeRepository contactMechanismTypeRepository, final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository, final CommunicationEventTypeRepository communicationEventTypeRepository, final PartyRelationshipRepository partyRelationshipRepository) {
		this.communicationEventRepository = repository;
		this.communicationEventRoleRepository = communicationEventRoleRepository;
		this.contactMechanismTypeRepository = contactMechanismTypeRepository;
		this.communicationEventStatusTypeRepository = communicationEventStatusTypeRepository;
		this.communicationEventTypeRepository = communicationEventTypeRepository;
		this.partyRelationshipRepository = partyRelationshipRepository;
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

	@SchemaMapping
	public ContactMechanismType contactMechanismType(CommunicationEvent communicationEvent) {
		return contactMechanismTypeRepository.findById(communicationEvent.getContactMechanismTypeId()).get();
	}

	@SchemaMapping
	public CommunicationEventStatusType communicationEventStatusType(CommunicationEvent communicationEvent) {
		return communicationEventStatusTypeRepository.findById(communicationEvent.getCommunicationEventStatusTypeId()).get();
	}

	@SchemaMapping
	public CommunicationEventType communicationEventType(CommunicationEvent communicationEvent) {
		return communicationEventTypeRepository.findById(communicationEvent.getCommunicationEventTypeId()).get();
	}

	@SchemaMapping
	public PartyRelationship partyRelationship(CommunicationEvent communicationEvent) {
		return partyRelationshipRepository.findById(communicationEvent.getPartyRelationshipId()).get();
	}
}
