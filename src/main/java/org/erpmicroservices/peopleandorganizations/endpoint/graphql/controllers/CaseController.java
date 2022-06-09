package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class CaseController {

	private final CaseRepository repository;

	public CaseController(final CaseRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public List<Case> cases() {
		return (List<Case>) repository.findAll();
	}
}
