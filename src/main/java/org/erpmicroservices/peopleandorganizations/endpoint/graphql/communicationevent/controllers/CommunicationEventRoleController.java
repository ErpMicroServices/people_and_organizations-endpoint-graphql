package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventRoleRepository;
import org.springframework.stereotype.Controller;


@Controller
public class CommunicationEventRoleController {

	private final CommunicationEventRoleRepository repository;

	public CommunicationEventRoleController(final CommunicationEventRoleRepository repository) {
		this.repository = repository;
	}


}
