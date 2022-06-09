package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.FacilityRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.FacilityRoleTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class FacilityRoleTypeController {

	private final FacilityRoleTypeRepository repository;

	public FacilityRoleTypeController(final FacilityRoleTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public List<FacilityRoleType> facilityRoleTypes() {
		return (List<FacilityRoleType>) repository.findAll();
	}
}
