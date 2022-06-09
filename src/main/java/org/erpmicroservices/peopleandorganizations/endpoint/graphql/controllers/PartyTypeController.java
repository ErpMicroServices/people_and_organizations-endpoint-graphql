package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.PartyType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PartyTypeController {

	private final PartyTypeRepository partyTypeRepository;

	public PartyTypeController(final PartyTypeRepository partyTypeRepository) {
		this.partyTypeRepository = partyTypeRepository;
	}

	@QueryMapping
	public List<PartyType> partyTypes() {
		return (List<PartyType>) partyTypeRepository.findAll();
	}
}
