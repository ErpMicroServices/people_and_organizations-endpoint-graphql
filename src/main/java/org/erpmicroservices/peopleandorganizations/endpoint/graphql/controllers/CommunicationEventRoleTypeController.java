package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEventRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventRoleTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommunicationEventRoleTypeController {

	private final CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository;

	public CommunicationEventRoleTypeController(final CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository) {
		this.communicationEventRoleTypeRepository = communicationEventRoleTypeRepository;
	}

	@QueryMapping
	public List<CommunicationEventRoleType> communicationEventRoleTypes() {
		return (List<CommunicationEventRoleType>) communicationEventRoleTypeRepository.findAll();
	}
}
