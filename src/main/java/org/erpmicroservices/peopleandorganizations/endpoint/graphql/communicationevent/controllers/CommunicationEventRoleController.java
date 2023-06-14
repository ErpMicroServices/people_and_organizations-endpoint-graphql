package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventRoleRepository;
import org.springframework.stereotype.Controller;


@Controller
public class CommunicationEventRoleController {

	private final CommunicationEventRoleRepository repository;

	public CommunicationEventRoleController(final CommunicationEventRoleRepository repository) {
		this.repository = repository;
	}


}
