package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CaseTypeController {

	private final CaseTypeRepository caseTypeRepository;

	public CaseTypeController(final CaseTypeRepository caseTypeRepository) {
		this.caseTypeRepository = caseTypeRepository;
	}

	@QueryMapping
	public List<CaseType> caseTypes() {
		return (List<CaseType>) caseTypeRepository.findAll();
	}
}
