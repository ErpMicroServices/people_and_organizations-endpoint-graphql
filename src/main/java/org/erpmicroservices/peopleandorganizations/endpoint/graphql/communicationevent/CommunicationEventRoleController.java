package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import org.springframework.stereotype.Controller;


@Controller
public class CommunicationEventRoleController {

	private final CommunicationEventRoleRepository repository;

	public CommunicationEventRoleController(final CommunicationEventRoleRepository repository) {
		this.repository = repository;
	}


}
