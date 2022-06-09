package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEventPurposeType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventPurposeTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommunicationEventPurposeTypeController {

	private final CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository;

	public CommunicationEventPurposeTypeController(final CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository) {
		this.communicationEventPurposeTypeRepository = communicationEventPurposeTypeRepository;
	}

	@QueryMapping
	public List<CommunicationEventPurposeType> communicationEventPurposeTypes() {
		return (List<CommunicationEventPurposeType>) communicationEventPurposeTypeRepository.findAll();
	}
}
