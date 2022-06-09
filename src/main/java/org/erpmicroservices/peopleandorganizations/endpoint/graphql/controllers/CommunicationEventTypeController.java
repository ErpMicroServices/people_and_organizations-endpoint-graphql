package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEventType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommunicationEventTypeController {

	private final CommunicationEventTypeRepository communicationEventTypeRepository;

	public CommunicationEventTypeController(final CommunicationEventTypeRepository communicationEventTypeRepository) {
		this.communicationEventTypeRepository = communicationEventTypeRepository;
	}

	@QueryMapping
	public List<CommunicationEventType> communicationEventTypes() {
		return (List<CommunicationEventType>) communicationEventTypeRepository.findAll();
	}
}
