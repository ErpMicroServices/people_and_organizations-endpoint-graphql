package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.FacilityType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.FacilityTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class FacilityTypeController {

	private final FacilityTypeRepository repository;

	public FacilityTypeController(final FacilityTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public List<FacilityType> facilityTypes() {
		return (List<FacilityType>) repository.findAll();
	}
}
