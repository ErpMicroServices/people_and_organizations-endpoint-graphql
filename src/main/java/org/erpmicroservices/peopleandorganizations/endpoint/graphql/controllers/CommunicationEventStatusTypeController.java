package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventStatusTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommunicationEventStatusTypeController {

	private final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

	public CommunicationEventStatusTypeController(CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository) {
		this.communicationEventStatusTypeRepository = communicationEventStatusTypeRepository;
	}

	@QueryMapping
	public List<CommunicationEventStatusType> communicationEventStatusTypes() {
		return (List<CommunicationEventStatusType>) communicationEventStatusTypeRepository.findAll();
	}
}
